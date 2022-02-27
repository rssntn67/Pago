package it.arsinfo.pago.service.impl;

import it.arsinfo.pago.dao.ConsumoDao;
import it.arsinfo.pago.entity.Consumo;
import it.arsinfo.pago.entity.Utenza;
import it.arsinfo.pago.service.api.ConsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ConsumoServiceDaoImpl implements ConsumoService {

    @Autowired
    private ConsumoDao repository;

	@Override
	public Consumo save(Consumo entity) {
		return repository.save(entity);
	}

	@Override
	public void delete(Consumo entity) {
		repository.delete(entity);
	}

	@Override
	public Consumo findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public List<Consumo> findAll() {
		return repository.findAll();
	}

    @Override
    public List<Consumo> searchByDefault() {
        return repository.findAll();
    }

    @Override
    public Consumo add() {
        Consumo consumo;
        consumo = new Consumo();
        return consumo;
    }

	private List<Consumo> search(
            Utenza utenza) {
        if (utenza == null ) {
            return repository.findAll();
        }
        return repository.findByUtenza(utenza);

	}

	@Override
	public List<Consumo> searchBy(
			Utenza utenza
     		) {
        return search(utenza);
    }

	
}
