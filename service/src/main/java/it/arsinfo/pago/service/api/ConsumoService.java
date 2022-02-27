package it.arsinfo.pago.service.api;

import java.util.List;

import it.arsinfo.pago.entity.Consumo;
import it.arsinfo.pago.entity.Utenza;

public interface ConsumoService extends PagoServiceBase<Consumo> {
	List<Consumo> searchBy(Utenza p);

}
