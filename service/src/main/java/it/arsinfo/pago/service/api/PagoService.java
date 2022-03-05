package it.arsinfo.pago.service.api;

import java.util.List;

import it.arsinfo.pago.entity.Pago;


public interface PagoService<S extends Pago> {
	
	S save(S entity);
	void delete(S entity);
	S findById(Long id);
	List<S> findAll();
	List<S> searchByDefault();
	S add();

}
