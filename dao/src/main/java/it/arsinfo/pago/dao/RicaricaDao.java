package it.arsinfo.pago.dao;

import it.arsinfo.pago.entity.Armatore;
import it.arsinfo.pago.entity.Ricarica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface RicaricaDao extends JpaRepository<Ricarica, Long> {

    List<Ricarica> findByDataPagamentoAfter(LocalDateTime data);
    List<Ricarica> findByImporto(BigDecimal importo);
	List<Ricarica> findByCommittente(Armatore committente);
    List<Ricarica> findByCommittenteAndDataPagamentoAfter(Armatore committente, LocalDateTime data);
    List<Ricarica> findByCommittenteAndImporto(Armatore tValue, BigDecimal importo);
    List<Ricarica> findByImportoAndDataPagamentoAfter(BigDecimal importo, LocalDateTime data);
    List<Ricarica> findByCommittenteAndImportoAndDataPagamentoAfter(Armatore committente, BigDecimal importo, LocalDateTime data);

}
