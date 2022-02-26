package it.arsinfo.pago.service.impl;

import it.arsinfo.pago.dao.ArmatoreDao;
import it.arsinfo.pago.entity.Armatore;
import it.arsinfo.pago.service.api.ArmatoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ArmatoreServiceDaoImpl implements ArmatoreService {

    @Autowired
    private ArmatoreDao repository;

	@Override
	public Armatore save(Armatore entity) {
		return repository.save(entity);
	}

	@Override
	public void delete(Armatore entity) {
		repository.delete(entity);
	}

	@Override
	public Armatore findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public List<Armatore> findAll() {
		return repository.findAll();
	}

    @Override
    public List<Armatore> searchByDefault() {
        return repository.findAll();
    }

    @Override
    public Armatore add() {
		Armatore anagrafica = new Armatore();
        anagrafica.setNome("Nome");
        anagrafica.setCognome("Cognome");
        return anagrafica;
    }

	private List<Armatore> search(
			String nome,
			String cognome,
			String imbarcazione,
			String citta, 
			String cap) {

        if (    !StringUtils.hasLength(nome)
				&& !StringUtils.hasLength(cognome)
				&& !StringUtils.hasLength(imbarcazione)
        		&& !StringUtils.hasLength(citta)
        		&& !StringUtils.hasLength(cap)
        		) {
            return repository.findAll();
        }

        if (       !StringUtils.hasLength(cognome)
                && !StringUtils.hasLength(nome)
                && !StringUtils.hasLength(citta)
                && !StringUtils.hasLength(cap)) {
            return repository.findByImbarcazioneContainingIgnoreCase(imbarcazione);
        }

        if (       !StringUtils.hasLength(nome)
        		&& !StringUtils.hasLength(imbarcazione)
        		&& !StringUtils.hasLength(citta)
        		&& !StringUtils.hasLength(cap)) {
            return repository.findByCognomeContainingIgnoreCase(cognome);
        }
        
        if (       !StringUtils.hasLength(cognome)
        		&& !StringUtils.hasLength(imbarcazione)
        		&& !StringUtils.hasLength(citta)
        		&& !StringUtils.hasLength(cap)) {
            return repository.findByNomeContainingIgnoreCase(nome);
        }
        

        if (       !StringUtils.hasLength(cognome)
        		&& !StringUtils.hasLength(nome) 
        		&& !StringUtils.hasLength(imbarcazione)
        		&& !StringUtils.hasLength(cap)) {
            return repository.findByCittaContainingIgnoreCase(citta);
        }
        
        if (       !StringUtils.hasLength(cognome)
        		&& !StringUtils.hasLength(nome) 
        		&& !StringUtils.hasLength(imbarcazione)
        		&& !StringUtils.hasLength(citta)) {
            return repository.findByCapContainingIgnoreCase(cap);
        }

        if (    !StringUtils.hasLength(cognome)
                && !StringUtils.hasLength(citta)
                && !StringUtils.hasLength(cap)
        ) {
            return repository.findByImbarcazioneContainingIgnoreCaseAndNomeContainingIgnoreCase(imbarcazione, nome);
        }

        if (    !StringUtils.hasLength(nome)
                && !StringUtils.hasLength(citta)
                && !StringUtils.hasLength(cap)
        ) {
            return repository.findByImbarcazioneContainingIgnoreCaseAndCognomeContainingIgnoreCase(imbarcazione, cognome);
        }

        if (    !StringUtils.hasLength(nome)
                && !StringUtils.hasLength(cognome)
                && !StringUtils.hasLength(cap)
        ) {
            return repository.findByImbarcazioneContainingIgnoreCaseAndCittaContainingIgnoreCase(imbarcazione, citta);
        }

        if (    !StringUtils.hasLength(nome)
                && !StringUtils.hasLength(cognome)
                && !StringUtils.hasLength(citta)
        ) {
            return repository.findByImbarcazioneContainingIgnoreCaseAndCapContainingIgnoreCase(imbarcazione, cap);
        }

        if (       !StringUtils.hasLength(imbarcazione)
        		&& !StringUtils.hasLength(citta)
        		&& !StringUtils.hasLength(cap)) {
            return repository.findByNomeContainingIgnoreCaseAndCognomeContainingIgnoreCase(nome,cognome);
        }

        if (       !StringUtils.hasLength(imbarcazione)
                && !StringUtils.hasLength(cognome)
                && !StringUtils.hasLength(cap)) {
            return repository.findByNomeContainingIgnoreCaseAndCittaContainingIgnoreCase(nome,citta);
        }

        if (       !StringUtils.hasLength(imbarcazione)
                && !StringUtils.hasLength(cognome)
                && !StringUtils.hasLength(citta)) {
            return repository.findByNomeContainingIgnoreCaseAndCapContainingIgnoreCase(nome,cap);
        }

        if (       !StringUtils.hasLength(imbarcazione)
                && !StringUtils.hasLength(nome)
                && !StringUtils.hasLength(cap)) {
            return repository.findByCognomeContainingIgnoreCaseAndCittaContainingIgnoreCase(cognome,citta);
        }

        if (       !StringUtils.hasLength(imbarcazione)
                && !StringUtils.hasLength(nome)
                && !StringUtils.hasLength(citta)) {
            return repository.findByCognomeContainingIgnoreCaseAndCapContainingIgnoreCase(cognome,cap);
        }

        if (       !StringUtils.hasLength(imbarcazione)
                && !StringUtils.hasLength(nome)
                && !StringUtils.hasLength(cognome)) {
            return repository.findByCittaContainingIgnoreCaseAndCapContainingIgnoreCase(citta,cap);
        }

        if (       !StringUtils.hasLength(citta)
        		&& !StringUtils.hasLength(cap)
    		) {
            return repository.findByImbarcazioneContainingIgnoreCaseAndNomeContainingIgnoreCaseAndCognomeContainingIgnoreCase(imbarcazione,nome,cognome);
        }
        if (       !StringUtils.hasLength(cognome)
        		&& !StringUtils.hasLength(cap)
    		) {
            return repository.findByImbarcazioneContainingIgnoreCaseAndNomeContainingIgnoreCaseAndCittaContainingIgnoreCase(imbarcazione,nome,citta);
        }
        if (       !StringUtils.hasLength(cognome)
        		&& !StringUtils.hasLength(citta)
    		) {
            return repository.findByImbarcazioneContainingIgnoreCaseAndNomeContainingIgnoreCaseAndCapContainingIgnoreCase(imbarcazione,nome,cap);
        }
        if (       !StringUtils.hasLength(nome)
        		&& !StringUtils.hasLength(cap)
    		) {
            return repository.findByImbarcazioneContainingIgnoreCaseAndCognomeContainingIgnoreCaseAndCittaContainingIgnoreCase(imbarcazione,cognome,citta);

        }
        if (       !StringUtils.hasLength(nome)
        		&& !StringUtils.hasLength(citta)
    		) {
            return repository.findByImbarcazioneContainingIgnoreCaseAndCognomeContainingIgnoreCaseAndCapContainingIgnoreCase(imbarcazione,cognome,cap);
        }
        if (       !StringUtils.hasLength(nome)
        		&& !StringUtils.hasLength(cognome)
    		) {
            return repository.findByImbarcazioneContainingIgnoreCaseAndCittaContainingIgnoreCaseAndCapContainingIgnoreCase(imbarcazione,citta,cap);
        }

        if (       !StringUtils.hasLength(imbarcazione)
                && !StringUtils.hasLength(cap)
        ) {
            return repository.findByNomeContainingIgnoreCaseAndCognomeContainingIgnoreCaseAndCittaContainingIgnoreCase(nome,cognome,citta);
        }

        if (       !StringUtils.hasLength(imbarcazione)
                && !StringUtils.hasLength(citta)
        ) {
            return repository.findByNomeContainingIgnoreCaseAndCognomeContainingIgnoreCaseAndCapContainingIgnoreCase(nome,cognome,cap);
        }

        if (       !StringUtils.hasLength(imbarcazione)
                && !StringUtils.hasLength(cognome)
        ) {
            return repository.findByNomeContainingIgnoreCaseAndCittaContainingIgnoreCaseAndCapContainingIgnoreCase(nome,citta,cap);
        }

        if (       !StringUtils.hasLength(imbarcazione)
                && !StringUtils.hasLength(nome)
        ) {
            return repository.findByCognomeContainingIgnoreCaseAndCittaContainingIgnoreCaseAndCapContainingIgnoreCase(cognome,citta,cap);
        }

        if (       !StringUtils.hasLength(imbarcazione)) {
            return repository.findByNomeContainingIgnoreCaseAndCognomeContainingIgnoreCaseAndCittaContainingIgnoreCaseAndCapContainingIgnoreCase(nome,cognome,citta,cap);
        }

        if (       !StringUtils.hasLength(nome)) {
            return repository.findByImbarcazioneContainingIgnoreCaseAndCognomeContainingIgnoreCaseAndCittaContainingIgnoreCaseAndCapContainingIgnoreCase(imbarcazione,cognome,citta,cap);
        }
        if (       !StringUtils.hasLength(cognome)) {
            return repository.findByImbarcazioneContainingIgnoreCaseAndNomeContainingIgnoreCaseAndCittaContainingIgnoreCaseAndCapContainingIgnoreCase(imbarcazione,nome,citta,cap);
        }
        if (       !StringUtils.hasLength(citta)) {
            return repository.findByImbarcazioneContainingIgnoreCaseAndNomeContainingIgnoreCaseAndCognomeContainingIgnoreCaseAndCapContainingIgnoreCase(imbarcazione,nome,cognome,cap);
        }

        if (       !StringUtils.hasLength(cap)) {
            return repository.findByImbarcazioneContainingIgnoreCaseAndNomeContainingIgnoreCaseAndCognomeContainingIgnoreCaseAndCittaContainingIgnoreCase(imbarcazione,nome,cognome,citta);
        }

        return repository.findByImbarcazioneContainingIgnoreCaseAndNomeContainingIgnoreCaseAndCognomeContainingIgnoreCaseAndCittaContainingIgnoreCaseAndCapContainingIgnoreCase(imbarcazione,nome,cognome,citta,cap);
		
	}

	@Override
	public List<Armatore> searchBy(
			String nome,
			String cognome,
			String imbarcazione,
			String citta, 
			String cap, 
			Armatore.Paese paese,
    		Armatore.Provincia provincia
    		) {
		List<Armatore> anagrafiche = search(nome, cognome,imbarcazione, citta, cap);

		if (paese != null) {
            anagrafiche = anagrafiche.stream().filter(a -> paese == a.getPaese()).collect(Collectors.toList());
        }
        if (provincia != null) {
            anagrafiche = anagrafiche.stream().filter(a -> provincia == a.getProvincia()).collect(Collectors.toList());
        }

        return anagrafiche;
    }

	
}
