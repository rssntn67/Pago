package it.arsinfo.pago.service.api;

import java.util.List;

import it.arsinfo.pago.entity.Armatore;
import org.springframework.stereotype.Service;


@Service
public interface ArmatoreService extends PagoServiceBase<Armatore> {

	List<Armatore> searchBy(
			String searchNome,
			String searchCognome,
			String searchImbarcazione,
			String searchCitta,
			String searchCap, 
			Armatore.Paese paese,
			Armatore.Provincia provincia);
}
