package it.arsinfo.pago.service.api;

import it.arsinfo.pago.entity.Modello;

import java.util.List;


public interface ModelloService extends PagoService<Modello> {
	
	List<Modello> searchBy(String nome, Modello.TipoConsumo tipo);
}
