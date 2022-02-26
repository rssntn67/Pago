package it.arsinfo.pago.dao;

import it.arsinfo.pago.entity.Armatore;
import it.arsinfo.pago.entity.Ricarica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface RicaricaDao extends JpaRepository<Ricarica, Long> {

    List<Ricarica> findByDataPagamento(Date data);
    List<Ricarica> findByImporto(BigDecimal importo);
	List<Ricarica> findByCommittente(Armatore tValue);
    List<Ricarica> findByCommittenteAndDataPagamento(Armatore tValue, Date data);
    List<Ricarica> findByCommittenteAndImporto(Armatore tValue, BigDecimal importo);
    List<Ricarica> findByImportoAndDataPagamento(BigDecimal importo, Date data);
    List<Ricarica> findByCommittenteAndImportoAndDataPagamento(Armatore tValue, BigDecimal importo, Date data);

}
