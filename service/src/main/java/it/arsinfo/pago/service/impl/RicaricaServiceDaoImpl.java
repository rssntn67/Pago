package it.arsinfo.pago.service.impl;

import it.arsinfo.pago.dao.ArmatoreDao;
import it.arsinfo.pago.dao.RicaricaDao;
import it.arsinfo.pago.entity.Armatore;
import it.arsinfo.pago.entity.Ricarica;
import it.arsinfo.pago.service.api.RicaricaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class RicaricaServiceDaoImpl implements RicaricaService {

    @Autowired
    private RicaricaDao repository;

    @Autowired
    private ArmatoreDao armatoreDao;

	@Override
	public Ricarica save(Ricarica entity) {
		if (entity.getId() == null) {
		    Ricarica saved=repository.save(entity);
		    Armatore committente=saved.getCommittente();
		    committente.setCreditoResiduo(committente.getCreditoResiduo().add(saved.getImporto()));
		    armatoreDao.save(committente);
            return saved;
        }
		return entity;
	}

	@Override
	public void delete(Ricarica entity) {

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
            String importo, LocalDateTime dataPagamento, Armatore committente) {

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
            return repository.findByDataPagamentoAfter(dataPagamento);
        }
        if (committente==null && dataPagamento == null) {
            return repository.findByImporto(new BigDecimal(importo));
        }
        if (committente == null ) {
            return repository.findByImportoAndDataPagamentoAfter(new BigDecimal(importo),dataPagamento);
        }
        if (dataPagamento == null) {
            return repository.findByCommittenteAndImporto(committente,new BigDecimal(importo));
        }
        if (!StringUtils.hasLength(importo) ) {
            return repository.findByCommittenteAndDataPagamentoAfter(committente,dataPagamento);
        }
        return repository.findByCommittenteAndImportoAndDataPagamentoAfter(committente,new BigDecimal(importo),dataPagamento);

	}

	@Override
	public List<Ricarica> searchBy(
            String importo, LocalDateTime dataPagamento, Armatore committente
     		) {

        return search(importo,dataPagamento,committente);
    }

    @Override
    public List<Armatore> findArmatori() {
        return armatoreDao.findAll();
    }


}
