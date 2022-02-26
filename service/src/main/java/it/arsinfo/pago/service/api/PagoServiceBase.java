package it.arsinfo.pago.service.api;

import java.util.List;

import it.arsinfo.pago.entity.PagoEntity;


public interface PagoServiceBase<S extends PagoEntity> {
	
	S save(S entity) throws Exception;
	void delete(S entity) throws Exception ;
	S findById(Long id);
	List<S> findAll();
	List<S> searchByDefault();
	S add();

}
