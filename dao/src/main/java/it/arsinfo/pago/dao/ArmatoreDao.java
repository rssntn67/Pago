package it.arsinfo.pago.dao;

import it.arsinfo.pago.entity.Armatore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArmatoreDao extends JpaRepository<Armatore, Long> {
	
	List<Armatore> findByCognomeContainingIgnoreCase(String cognome);

	List<Armatore> findByNomeContainingIgnoreCase(String nome);

	List<Armatore> findByImbarcazioneContainingIgnoreCase(String imbarcazione);
	List<Armatore> findByCapContainingIgnoreCase(String cap);
	
	List<Armatore> findByCittaContainingIgnoreCase(String citta);

	List<Armatore> findByImbarcazioneAndCognomeContainingIgnoreCase(String imbarcazione, String cognome);
	
	List<Armatore> findByNomeContainingIgnoreCaseAndCapContainingIgnoreCase(String nome, String cap);

	List<Armatore> findByImbarcazioneContainingIgnoreCaseAndCittaContainingIgnoreCase(String denominazione,
			String citta);

	List<Armatore> findByImbarcazioneContainingIgnoreCaseAndCapContainingIgnoreCase(String denominazione,
			String cap);

	List<Armatore> findByCittaContainingIgnoreCaseAndCapContainingIgnoreCase(String citta, String cap);

	List<Armatore> findByImbarcazioneAndNomeContainingIgnoreCaseAndDenominazioneIgnoreCase(String imbarcazione, String nome,
			String denominazione);

	List<Armatore> findByImbarcazioneAndNomeContainingIgnoreCaseAndCittaIgnoreCase(String imbarcazione, String nome,
			String citta);

	List<Armatore> findByImbarcazioneAndNomeContainingIgnoreCaseAndCapIgnoreCase(String imbarcazione, String nome,
			String cap);

	List<Armatore> findByImbarcazioneAndDenominazioneContainingIgnoreCaseAndCittaIgnoreCase(String imbarcazione,
			String denominazione, String citta);

	List<Armatore> findByImbarcazioneAndDenominazioneContainingIgnoreCaseAndCapIgnoreCase(String imbarcazione,
			String denominazione, String cap);

	List<Armatore> findByImbarcazioneAndCittaContainingIgnoreCaseAndCapIgnoreCase(String imbarcazione, String citta,
			String cap);

	List<Armatore> findByNomeContainingIgnoreCaseAndDenominazioneContainingIgnoreCaseAndCittaContainingIgnoreCase(
			String nome, String denominazione, String citta);

	List<Armatore> findByNomeContainingIgnoreCaseAndDenominazioneContainingIgnoreCaseAndCapContainingIgnoreCase(
			String nome, String denominazione, String cap);

	List<Armatore> findByNomeContainingIgnoreCaseAndCittaContainingIgnoreCaseAndCapContainingIgnoreCase(String nome,
			String citta, String cap);

	List<Armatore> findByDenominazioneContainingIgnoreCaseAndCittaContainingIgnoreCaseAndCapContainingIgnoreCase(
			String denominazione, String citta, String cap);

	List<Armatore> findByImbarcazioneAndNomeContainingIgnoreCaseAndDenominazioneContainingIgnoreCaseAndCapIgnoreCase(
			String imbarcazione, String nome, String denominazione, String cap);

	List<Armatore> findByImbarcazioneAndNomeContainingIgnoreCaseAndCittaContainingIgnoreCaseAndCapIgnoreCase(
			String imbarcazione, String nome, String citta, String cap);

	List<Armatore> findByImbarcazioneAndDenominazioneContainingIgnoreCaseAndCittaContainingIgnoreCaseAndCapIgnoreCase(
			String imbarcazione, String denominazione, String citta, String cap);

	List<Armatore> findByNomeContainingIgnoreCaseAndDenominazioneContainingIgnoreCaseAndCittaContainingIgnoreCaseAndCapContainingIgnoreCase(
			String nome, String denominazione, String citta, String cap);

	List<Armatore> findByImbarcazioneAndNomeContainingIgnoreCaseAndDenominazioneContainingIgnoreCaseAndCittaContainingIgnoreCaseAndCapIgnoreCase(
			String imbarcazione, String nome, String denominazione, String citta, String cap);
}
