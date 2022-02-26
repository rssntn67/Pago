package it.arsinfo.pago.service.impl;

import it.arsinfo.pago.dao.UtenzaModelloDao;
import it.arsinfo.pago.entity.UtenzaModello;
import it.arsinfo.pago.service.api.UtenzaModelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
public class UtenzaModelloServiceDaoImpl implements UtenzaModelloService {

    @Autowired
    private UtenzaModelloDao repository;

	@Override
	public UtenzaModello save(UtenzaModello entity) {
		return repository.save(entity);
	}

	@Override
	public void delete(UtenzaModello entity) {
		repository.delete(entity);
	}

	@Override
	public UtenzaModello findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public List<UtenzaModello> findAll() {
		return repository.findAll();
	}

    @Override
    public List<UtenzaModello> searchByDefault() {
        return repository.findAll();
    }

    @Override
    public UtenzaModello add() {
        UtenzaModello ricarica;
        ricarica = new UtenzaModello();
        return ricarica;
    }


	@Override
	public List<UtenzaModello> searchBy(
            String nome, UtenzaModello.TipoConsumo tipo
     		) {
	    if (!StringUtils.hasLength(nome) && tipo == null) {
	        return repository.findAll();
        }

	    if (tipo == null) {
	        return repository.findByNomeStartsWithIgnoreCase(nome);
        }

	    if (!StringUtils.hasLength(nome)) {
	        return repository.findByTipo(tipo);
        }

	    return repository.findByNomeStartsWithIgnoreCaseAndTipo(nome,tipo);
    }

	
}
