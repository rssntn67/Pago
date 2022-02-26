package it.arsinfo.pago.service.impl;

import it.arsinfo.pago.dao.ConsumoDao;
import it.arsinfo.pago.entity.Consumo;
import it.arsinfo.pago.entity.Utenza;
import it.arsinfo.pago.service.api.ConsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;


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
        Consumo consumo = new Consumo();
        return consumo;
    }

	private List<Consumo> search(
            Utenza utenza,
            Consumo.Anno anno,
            Consumo.Mese mese) {
	    if (utenza == null && anno==null && mese == null) {
	        return repository.findAll();
        }
        if (utenza == null && anno==null ) {
            return repository.findByMese(mese);
        }
        if (utenza == null &&  mese == null) {
            return repository.findByAnno(anno);
        }
        if (anno==null && mese == null) {
            return repository.findByUtenza(utenza);
        }
        if (utenza == null ) {
            return repository.findByAnnoAndMese(anno,mese);
        }
        if (anno == null) {
            return repository.findByMeseAndUtenza(mese,utenza);
        }
        if (mese == null) {
            return repository.findByAnnoAndUtenza(anno,utenza);
        }
        return repository.findByAnnoAndMeseAndUtenza(anno,mese,utenza);

	}

	@Override
	public List<Consumo> searchBy(
			Utenza utenza,
            Consumo.Anno anno,
            Consumo.Mese mese
     		) {

        return search(utenza,anno,mese);
    }

	
}
