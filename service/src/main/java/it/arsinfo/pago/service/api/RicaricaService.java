package it.arsinfo.pago.service.api;

import it.arsinfo.pago.entity.Armatore;
import it.arsinfo.pago.entity.Ricarica;

import java.time.LocalDateTime;
import java.util.List;


public interface RicaricaService extends PagoService<Ricarica> {

	List<Ricarica> searchBy(String importo, LocalDateTime dataPagamento, Armatore armatore);

	List<Armatore> findArmatori();
}
