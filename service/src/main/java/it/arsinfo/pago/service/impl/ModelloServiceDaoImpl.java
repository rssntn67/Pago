package it.arsinfo.pago.service.impl;

import it.arsinfo.pago.dao.ModelloDao;
import it.arsinfo.pago.entity.Modello;
import it.arsinfo.pago.service.api.ModelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
public class ModelloServiceDaoImpl implements ModelloService {

    @Autowired
    private ModelloDao repository;

	@Override
	public Modello save(Modello entity) {
		return repository.save(entity);
	}

	@Override
	public void delete(Modello entity) {
		repository.delete(entity);
	}

	@Override
	public Modello findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public List<Modello> findAll() {
		return repository.findAll();
	}

    @Override
    public List<Modello> searchByDefault() {
        return repository.findAll();
    }

    @Override
    public Modello add() {
        Modello modello;
		modello = new Modello();
        return modello;
    }


	@Override
	public List<Modello> searchBy(
            String nome, Modello.TipoConsumo tipo
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
