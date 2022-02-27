package it.arsinfo.pago.service.impl;

import it.arsinfo.pago.dao.ArmatoreDao;
import it.arsinfo.pago.dao.ModelloDao;
import it.arsinfo.pago.dao.UtenzaDao;
import it.arsinfo.pago.entity.Armatore;
import it.arsinfo.pago.entity.Utenza;
import it.arsinfo.pago.entity.Modello;
import it.arsinfo.pago.service.api.UtenzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;


@Service
public class UtenzaServiceDaoImpl implements UtenzaService {

    @Autowired
    private UtenzaDao repository;

	@Autowired
	private ArmatoreDao armatoreDao;

	@Autowired
	private ModelloDao modelloDao;

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
            String nome, Modello tipo
     		) {
	    if (!StringUtils.hasLength(nome) && tipo == null) {
	        return repository.findAll();
        }

	    if (tipo == null) {
	        return repository.findByIdentificativoStartsWithIgnoreCase(nome);
        }

	    if (!StringUtils.hasLength(nome)) {
	        return repository.findByModello(tipo);
        }

	    return repository.findByIdentificativoStartsWithIgnoreCaseAndModello(nome,tipo);
    }

	@Override
	public List<Armatore> findUtenza() {
		return armatoreDao.findByCreditoResiduoGreaterThan(BigDecimal.ZERO);
	}

	@Override
	public List<Modello> findModelli() {
		return modelloDao.findAll();
	}


}
