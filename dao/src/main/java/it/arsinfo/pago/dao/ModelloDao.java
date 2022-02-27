package it.arsinfo.pago.dao;

import it.arsinfo.pago.entity.Modello;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelloDao extends JpaRepository<Modello, Long> {

	List<Modello> findByNomeStartsWithIgnoreCase(String nome);
	List<Modello> findByTipo(Modello.TipoConsumo tipo);
	List<Modello> findByNomeStartsWithIgnoreCaseAndTipo(String nome, Modello.TipoConsumo tipo);

}
