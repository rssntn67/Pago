package it.arsinfo.pago.dao;

import it.arsinfo.pago.entity.Anno;
import it.arsinfo.pago.entity.Consumo;
import it.arsinfo.pago.entity.Mese;
import it.arsinfo.pago.entity.Utenza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsumoDao extends JpaRepository<Consumo, Long> {

	List<Consumo> findByAnno(Anno anno);
        List<Consumo> findByUtenza(Utenza anno);
        List<Consumo> findByMese(Mese anno);
        List<Consumo> findByAnnoAndMese(Anno anno,Mese mese);
        List<Consumo> findByAnnoAndUtenza(Anno anno,Utenza utenza);
        List<Consumo> findByAnnoAndMeseAndUtenza(Anno anno,Mese mese,Utenza utenza);
}
