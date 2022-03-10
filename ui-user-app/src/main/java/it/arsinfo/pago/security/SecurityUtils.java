package it.arsinfo.pago.security;

import it.arsinfo.pago.dao.PagoUserDao;
import it.arsinfo.pago.entity.PagoUser;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;

/**
 * SecurityUtils takes care of all such static operations that have to do with
 * security and querying rights from different beans of the UI.
 *
 */
public class
SecurityUtils {

    private static final String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}$";

	private SecurityUtils() {
		// Util methods only
	}

	/**
	 * Gets the user name of the currently signed in user.
	 *
	 * @return the user name of the current user or <code>null</code> if the
	 *         user has not signed in
	 */
	public static String getUsername() {
		//SecurityContext context = SecurityContextHolder.getContext();
		//UserDetails userDetails = (UserDetails) context.getAuthentication().getPrincipal();
		//return userDetails.getUsername();
		return "admin";
	}

	/**
	 * Gets the user object for the current user.
	 *
	 * @return the user object
	 */
	public static PagoUser getCurrentUser(PagoUserDao userInfoDao) {
		return userInfoDao.findByUsernameAndProvider(SecurityUtils.getUsername(), PagoUser.Provider.LOCAL);
	}

	public static boolean verify(String password) {
	    return password.matches(pattern);
	}
}
