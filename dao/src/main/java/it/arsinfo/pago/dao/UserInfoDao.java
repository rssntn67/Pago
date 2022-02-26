package it.arsinfo.pago.dao;

import it.arsinfo.pago.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserInfoDao extends JpaRepository<UserInfo, Long>{
    UserInfo findByUsernameAndProvider(String username, UserInfo.Provider provider);
    List<UserInfo> findByUsernameContainingIgnoreCase(String username);
    List<UserInfo> findByRole(UserInfo.Role role);
    List<UserInfo> findByUsernameContainingIgnoreCaseAndRole(String username, UserInfo.Role role);
}
