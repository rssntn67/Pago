package it.arsinfo.pago.service.api;

import java.util.List;

import it.arsinfo.pago.entity.Armatore;
import it.arsinfo.pago.entity.Utenza;
import it.arsinfo.pago.entity.Modello;

public interface UtenzaService extends PagoServiceBase<Utenza> {
	
	List<Utenza> searchBy(String nome, Modello tipo);

	List<Armatore> findUtenza();

	List<Modello> findModelli();
}
