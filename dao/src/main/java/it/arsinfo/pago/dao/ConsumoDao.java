package it.arsinfo.pago.dao;

import it.arsinfo.pago.entity.Consumo;
import it.arsinfo.pago.entity.Utenza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsumoDao extends JpaRepository<Consumo, Long> {

        List<Consumo> findByUtenza(Utenza anno);

}
