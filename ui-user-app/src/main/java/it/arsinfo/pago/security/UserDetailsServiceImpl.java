package it.arsinfo.pago.security;

import it.arsinfo.pago.dao.PagoUserDao;
import it.arsinfo.pago.entity.PagoUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PagoUserDao userInfoDao;

    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        PagoUser user = userInfoDao.findByUsernameAndProvider(username, PagoUser.Provider.LOCAL);
        if (null == user) {
            log.info("login: '{}' not found, access is denied.", username);
            throw new UsernameNotFoundException("No user found with username: "
                    + username);
        }
        log.info("login: {}",user);
        return new User(user.getUsername(),
        		user.getPasswordHash(),
        		getAuthorityFromRole(user.getRole()));
    }

    private static Collection<SimpleGrantedAuthority> getAuthorityFromRole(PagoUser.Role role) {
        switch (role) {
            case USER:
                return Collections.singletonList(new SimpleGrantedAuthority("ROLE_User") );
            case ADMIN:
                List<SimpleGrantedAuthority> list = new ArrayList<>();
                list.add(new SimpleGrantedAuthority("ROLE_User"));
                list.add(new SimpleGrantedAuthority("ROLE_Admin"));
                return list;
            case LOCKED:
            default:
                break;
        }
        return Collections.emptyList();
    }
}