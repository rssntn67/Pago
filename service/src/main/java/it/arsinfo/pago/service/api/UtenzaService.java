package it.arsinfo.pago.service.api;

import java.util.List;

import it.arsinfo.pago.entity.Utenza;
import it.arsinfo.pago.entity.UtenzaModello;

public interface UtenzaService extends PagoServiceBase<Utenza> {
	
	List<Utenza> searchBy(String nome, UtenzaModello tipo);
}
