package it.arsinfo.pago.service.api;

import it.arsinfo.pago.entity.Armatore;
import it.arsinfo.pago.entity.Ricarica;

import java.time.LocalDate;
import java.util.List;


public interface RicaricaService extends PagoServiceBase<Ricarica> {

	List<Ricarica> searchBy(String importo, LocalDate dataPagamento, Armatore armatore);

}
