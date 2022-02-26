package it.arsinfo.pago.dao;

import it.arsinfo.pago.entity.Consumo;
import it.arsinfo.pago.entity.Utenza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsumoDao extends JpaRepository<Consumo, Long> {

	    List<Consumo> findByAnno(Consumo.Anno anno);
        List<Consumo> findByUtenza(Utenza anno);
        List<Consumo> findByMese(Consumo.Mese anno);
        List<Consumo> findByAnnoAndMese(Consumo.Anno anno, Consumo.Mese mese);
        List<Consumo> findByAnnoAndUtenza(Consumo.Anno anno, Utenza utenza);
        List<Consumo> findByMeseAndUtenza(Consumo.Mese mese, Utenza utenza);
        List<Consumo> findByAnnoAndMeseAndUtenza(Consumo.Anno anno, Consumo.Mese mese, Utenza utenza);
}
