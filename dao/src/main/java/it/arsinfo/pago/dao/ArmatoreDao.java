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

	List<Armatore> findByImbarcazioneContainingIgnoreCaseAndNomeContainingIgnoreCase(String imbarcazione, String nome);
	List<Armatore> findByImbarcazioneContainingIgnoreCaseAndCognomeContainingIgnoreCase(String imbarcazione, String cognome);

	List<Armatore> findByImbarcazioneContainingIgnoreCaseAndCittaContainingIgnoreCase(String imbarcazione,
																					  String citta);
	List<Armatore> findByImbarcazioneContainingIgnoreCaseAndCapContainingIgnoreCase(String imbarcazione,
																					String cap);

	List<Armatore> findByNomeContainingIgnoreCaseAndCognomeContainingIgnoreCase(String nome, String cognome);
	List<Armatore> findByNomeContainingIgnoreCaseAndCittaContainingIgnoreCase(String nome, String citta);
	List<Armatore> findByNomeContainingIgnoreCaseAndCapContainingIgnoreCase(String nome, String cap);

	List<Armatore> findByCognomeContainingIgnoreCaseAndCittaContainingIgnoreCase(String cognome, String citta);
	List<Armatore> findByCognomeContainingIgnoreCaseAndCapContainingIgnoreCase(String cognome, String cap);

	List<Armatore> findByCittaContainingIgnoreCaseAndCapContainingIgnoreCase(String citta, String cap);

	List<Armatore> findByImbarcazioneContainingIgnoreCaseAndNomeContainingIgnoreCaseAndCognomeContainingIgnoreCase(String imbarcazione, String nome,
			String cognome);

	List<Armatore> findByImbarcazioneContainingIgnoreCaseAndNomeContainingIgnoreCaseAndCittaContainingIgnoreCase(String imbarcazione, String nome,
			String citta);

	List<Armatore> findByImbarcazioneContainingIgnoreCaseAndNomeContainingIgnoreCaseAndCapContainingIgnoreCase(String imbarcazione, String nome,
			String cap);

	List<Armatore> findByImbarcazioneContainingIgnoreCaseAndCognomeContainingIgnoreCaseAndCittaContainingIgnoreCase(String imbarcazione,
			String cognome, String citta);

	List<Armatore> findByImbarcazioneContainingIgnoreCaseAndCognomeContainingIgnoreCaseAndCapContainingIgnoreCase(String imbarcazione,
			String cognome, String cap);

	List<Armatore> findByImbarcazioneContainingIgnoreCaseAndCittaContainingIgnoreCaseAndCapContainingIgnoreCase(String imbarcazione, String citta,
			String cap);

	List<Armatore> findByNomeContainingIgnoreCaseAndCognomeContainingIgnoreCaseAndCittaContainingIgnoreCase(
			String nome, String cognome, String citta);

	List<Armatore> findByNomeContainingIgnoreCaseAndCognomeContainingIgnoreCaseAndCapContainingIgnoreCase(
			String nome, String cognome, String cap);

	List<Armatore> findByNomeContainingIgnoreCaseAndCittaContainingIgnoreCaseAndCapContainingIgnoreCase(String nome,
			String citta, String cap);

	List<Armatore> findByCognomeContainingIgnoreCaseAndCittaContainingIgnoreCaseAndCapContainingIgnoreCase(
			String denominazione, String citta, String cap);

	List<Armatore> findByImbarcazioneContainingIgnoreCaseAndNomeContainingIgnoreCaseAndCognomeContainingIgnoreCaseAndCittaContainingIgnoreCase(
			String imbarcazione, String nome, String cognome, String citta);

	List<Armatore> findByImbarcazioneContainingIgnoreCaseAndNomeContainingIgnoreCaseAndCognomeContainingIgnoreCaseAndCapContainingIgnoreCase(
			String imbarcazione, String nome, String cognome, String cap);

	List<Armatore> findByImbarcazioneContainingIgnoreCaseAndNomeContainingIgnoreCaseAndCittaContainingIgnoreCaseAndCapContainingIgnoreCase(
			String imbarcazione, String nome, String citta, String cap);

	List<Armatore> findByImbarcazioneContainingIgnoreCaseAndCognomeContainingIgnoreCaseAndCittaContainingIgnoreCaseAndCapContainingIgnoreCase(
			String imbarcazione, String cognome, String citta, String cap);

	List<Armatore> findByNomeContainingIgnoreCaseAndCognomeContainingIgnoreCaseAndCittaContainingIgnoreCaseAndCapContainingIgnoreCase(
			String nome, String cognome, String citta, String cap);

	List<Armatore> findByImbarcazioneContainingIgnoreCaseAndNomeContainingIgnoreCaseAndCognomeContainingIgnoreCaseAndCittaContainingIgnoreCaseAndCapContainingIgnoreCase(
			String imbarcazione, String nome, String denominazione, String citta, String cap);
}
