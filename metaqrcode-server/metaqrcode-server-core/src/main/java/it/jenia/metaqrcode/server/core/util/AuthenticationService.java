package it.jenia.metaqrcode.server.core.util;

import it.jenia.metaqrcode.server.entities.authentication.User;

/**
 * service for handling current user
 * 
 * @author andreatessaro
 *
 */
public interface AuthenticationService {
	
	/**
	 * return current spring user
	 * 
	 * @return current user
	 */
	public User getCurrentUser();
	
	/**
	 * return the user corresponfing to given email
	 * 
	 * @return current user
	 */
	public User getUserByEmail(String email);

}
