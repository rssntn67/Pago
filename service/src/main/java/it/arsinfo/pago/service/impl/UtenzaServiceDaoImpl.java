package it.arsinfo.pago.service.impl;

import it.arsinfo.pago.dao.UtenzaDao;
import it.arsinfo.pago.entity.Utenza;
import it.arsinfo.pago.entity.UtenzaModello;
import it.arsinfo.pago.service.api.UtenzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
public class UtenzaServiceDaoImpl implements UtenzaService {

    @Autowired
    private UtenzaDao repository;

	@Override
	public Utenza save(Utenza entity) {
		return repository.save(entity);
	}

	@Override
	public void delete(Utenza entity) {
		repository.delete(entity);
	}

	@Override
	public Utenza findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public List<Utenza> findAll() {
		return repository.findAll();
	}

    @Override
    public List<Utenza> searchByDefault() {
        return repository.findAll();
    }

    @Override
    public Utenza add() {
        Utenza utenza;
		utenza = new Utenza();
        return utenza;
    }


	@Override
	public List<Utenza> searchBy(
            String nome, UtenzaModello tipo
     		) {
	    if (!StringUtils.hasLength(nome) && tipo == null) {
	        return repository.findAll();
        }

	    if (tipo == null) {
	        return repository.findByNomeStartsWithIgnoreCase(nome);
        }

	    if (!StringUtils.hasLength(nome)) {
	        return repository.findByModello(tipo);
        }

	    return repository.findByNomeStartsWithIgnoreCaseAndModello(nome,tipo);
    }

	
}
