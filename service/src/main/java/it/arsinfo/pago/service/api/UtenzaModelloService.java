package it.arsinfo.pago.service.api;

import it.arsinfo.pago.entity.UtenzaModello;

import java.util.List;


public interface UtenzaModelloService extends PagoServiceBase<UtenzaModello> {
	
	List<UtenzaModello> searchBy(String nome, UtenzaModello.TipoConsumo tipo);
}
