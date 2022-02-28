package it.arsinfo.pago.service.api;

import it.arsinfo.pago.entity.UserInfo;
import it.arsinfo.pago.entity.UserInfo.Role;

import java.util.List;

public interface UserInfoService extends PagoService<UserInfo> {

	List<UserInfo> searchBy(String searchText, Role role);
}
