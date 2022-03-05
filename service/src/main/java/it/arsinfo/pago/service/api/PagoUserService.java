package it.arsinfo.pago.service.api;

import it.arsinfo.pago.entity.PagoUser;
import it.arsinfo.pago.entity.PagoUser.Role;

import java.util.List;

public interface PagoUserService extends PagoService<PagoUser> {

	List<PagoUser> searchBy(String searchText, Role role);

	PagoUser findByUsernameAndProvider(String admin, PagoUser.Provider local);
}
