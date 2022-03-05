package it.arsinfo.pago.dao;

import it.arsinfo.pago.entity.PagoUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagoUserDao extends JpaRepository<PagoUser, Long>{
    PagoUser findByUsernameAndProvider(String username, PagoUser.Provider provider);
    List<PagoUser> findByUsernameContainingIgnoreCase(String username);
    List<PagoUser> findByRole(PagoUser.Role role);
    List<PagoUser> findByUsernameContainingIgnoreCaseAndRole(String username, PagoUser.Role role);
}
