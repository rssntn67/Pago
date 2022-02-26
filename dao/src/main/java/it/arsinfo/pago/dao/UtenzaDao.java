package it.arsinfo.pago.dao;

import it.arsinfo.pago.entity.Utenza;
import it.arsinfo.pago.entity.UtenzaModello;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UtenzaDao extends JpaRepository<Utenza, Long> {

	List<Utenza> findByNomeStartsWithIgnoreCase(String nome);
	List<Utenza> findByModello(UtenzaModello tipo);
	List<Utenza> findByNomeStartsWithIgnoreCaseAndModello(String nome,UtenzaModello tipo);
	List<Utenza> findByActive(boolean isActive);
}
