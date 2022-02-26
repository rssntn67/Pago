package it.arsinfo.pago.service.impl;

import it.arsinfo.pago.dao.UserInfoDao;
import it.arsinfo.pago.entity.UserInfo;
import it.arsinfo.pago.entity.UserInfo.Role;
import it.arsinfo.pago.service.api.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserInfoServiceDaoImpl implements UserInfoService {

    @Autowired
    private UserInfoDao repository;
    
	@Override
	public UserInfo save(UserInfo entity) {
		UserInfo saved;
		saved = repository.save(entity);
		return saved;
	}

	@Override
	public void delete(UserInfo entity) {
		if (entity.getUsername().equals("admin"))
			return;
		repository.delete(entity);
	}

	@Override
	public UserInfo findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public List<UserInfo> findAll() {
		return repository.findAll();
	}

	@Override
	public List<UserInfo> searchByDefault() {
		return repository.findAll();
	}

	@Override
	public UserInfo add() {
		return new UserInfo();
	}

	public List<UserInfo> searchBy(String searchText, Role role) {
        if (!StringUtils.hasLength(searchText) && role == null) {
            return findAll();
        }
        if (!StringUtils.hasLength(searchText)) {
            return repository.findByRole(role);
        }
        if (role == null ) {
            return repository.findByUsernameContainingIgnoreCase(searchText);
        }
        return repository.findByUsernameContainingIgnoreCaseAndRole(searchText, role);
	}

}
