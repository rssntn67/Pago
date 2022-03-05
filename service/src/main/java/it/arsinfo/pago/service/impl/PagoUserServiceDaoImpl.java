package it.arsinfo.pago.service.impl;

import it.arsinfo.pago.dao.PagoUserDao;
import it.arsinfo.pago.entity.PagoUser;
import it.arsinfo.pago.entity.PagoUser.Role;
import it.arsinfo.pago.service.api.PagoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class PagoUserServiceDaoImpl implements PagoUserService {

    @Autowired
    private PagoUserDao repository;

	@Override
	public PagoUser save(PagoUser entity) {
		PagoUser saved;
		saved = repository.save(entity);
		return saved;
	}

	@Override
	public void delete(PagoUser entity) {
		if (entity.getUsername().equals("admin"))
			return;
		repository.delete(entity);
	}

	@Override
	public PagoUser findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public List<PagoUser> findAll() {
		return repository.findAll();
	}

	@Override
	public List<PagoUser> searchByDefault() {
		return repository.findAll();
	}

	@Override
	public PagoUser add() {
		return new PagoUser();
	}

	public List<PagoUser> searchBy(String searchText, Role role) {
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

	@Override
	public PagoUser findByUsernameAndProvider(String admin, PagoUser.Provider local) {
		return repository.findByUsernameAndProvider(admin,local);
	}

}
