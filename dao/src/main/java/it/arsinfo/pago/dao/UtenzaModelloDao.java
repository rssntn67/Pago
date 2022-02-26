package it.arsinfo.pago.dao;

import it.arsinfo.pago.entity.UtenzaModello;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UtenzaModelloDao extends JpaRepository<UtenzaModello, Long> {

	List<UtenzaModello> findByNomeStartsWithIgnoreCase(String nome);
	List<UtenzaModello> findByTipo(UtenzaModello.TipoConsumo tipo);
	List<UtenzaModello> findByNomeStartsWithIgnoreCaseAndTipo(String nome,UtenzaModello.TipoConsumo tipo);

}
