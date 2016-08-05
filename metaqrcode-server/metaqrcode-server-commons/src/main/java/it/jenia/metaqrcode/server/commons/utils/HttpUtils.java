package it.jenia.metaqrcode.server.commons.utils;

import javax.servlet.ServletResponse;

import it.jenia.metaqrcode.dto.Response;

/**
 * service for handling current user
 * 
 * @author andreatessaro
 *
 */
public interface HttpUtils {
	
	/**
	 * analize the response return code to set the correct http status code 
	 * 
	 * @param response
	 */
	public void calculateStatusCode(Response response, ServletResponse servletResponse) ;
	
}
