package it.arsinfo.pago.service.impl;

import it.arsinfo.pago.dao.RicaricaDao;
import it.arsinfo.pago.entity.Armatore;
import it.arsinfo.pago.entity.PagoEntity;
import it.arsinfo.pago.entity.Ricarica;
import it.arsinfo.pago.service.api.RicaricaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class RicaricaServiceDaoImpl implements RicaricaService {

    @Autowired
    private RicaricaDao repository;

	@Override
	public Ricarica save(Ricarica entity) {
		return repository.save(entity);
	}

	@Override
	public void delete(Ricarica entity) {
		repository.delete(entity);
	}

	@Override
	public Ricarica findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public List<Ricarica> findAll() {
		return repository.findAll();
	}

    @Override
    public List<Ricarica> searchByDefault() {
        return repository.findAll();
    }

    @Override
    public Ricarica add() {
        Ricarica ricarica;
        ricarica = new Ricarica();
        return ricarica;
    }

	private List<Ricarica> search(
            String importo, LocalDate dataPagamento, Armatore committente) {

        if (StringUtils.hasLength(importo)) {
            try {
                new BigDecimal(importo);
            } catch (NumberFormatException e) {
                return new ArrayList<>();
            }
        }
	    if (!StringUtils.hasLength(importo) && dataPagamento==null && committente == null) {
	        return repository.findAll();
        }
        if (!StringUtils.hasLength(importo) && dataPagamento==null ) {
            return repository.findByCommittente(committente);
        }
        if (!StringUtils.hasLength(importo) &&  committente == null) {
            return repository.findByDataPagamento(PagoEntity.getStandardDate(dataPagamento));
        }
        if (committente==null && dataPagamento == null) {
            return repository.findByImporto(new BigDecimal(importo));
        }
        if (committente == null ) {
            return repository.findByImportoAndDataPagamento(new BigDecimal(importo),PagoEntity.getStandardDate(dataPagamento));
        }
        if (dataPagamento == null) {
            return repository.findByCommittenteAndImporto(committente,new BigDecimal(importo));
        }
        if (!StringUtils.hasLength(importo) ) {
            return repository.findByCommittenteAndDataPagamento(committente,PagoEntity.getStandardDate(dataPagamento));
        }
        return repository.findByCommittenteAndImportoAndDataPagamento(committente,new BigDecimal(importo),PagoEntity.getStandardDate(dataPagamento));

	}

	@Override
	public List<Ricarica> searchBy(
            String importo, LocalDate dataPagamento, Armatore committente
     		) {

        return search(importo,dataPagamento,committente);
    }

	
}
