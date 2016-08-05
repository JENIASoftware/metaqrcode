package it.jenia.metaqrcode.server.services;

import it.jenia.metaqrcode.dto.ping.RequestPingAnonymous;
import it.jenia.metaqrcode.dto.ping.RequestPingAuthenticated;
import it.jenia.metaqrcode.dto.ping.ResponsePing;
import it.jenia.metaqrcode.server.commons.exception.MetaqrcodeException;

/**
 * ping services.
 * This service manage ping requests
 * 
 * @author andreatessaro
 *
 */
public interface PingService {

	/**
	 * make a ping in anonymous way. You do not need to be loggen in to do this ping
	 * 
	 * @param request the request
	 * @return the response
	 */
	public ResponsePing pingAnonymous(RequestPingAnonymous request) throws MetaqrcodeException;

	/**
	 * make a ping in anonymous way. This request will fail
	 * 
	 * @param request the request
	 * @return the response
	 */
	public ResponsePing failAnonymous(RequestPingAnonymous request) throws MetaqrcodeException;

	/**
	 * make a ping in authenticated way. You need to be logged in to make this request
	 * 
	 * @param request the request
	 * @return the response
	 */
	public ResponsePing pingAuthenticated(RequestPingAuthenticated request) throws MetaqrcodeException;

	/**
	 * make a ping in authenticated way. You need to be logged in to make this request. This request will fail
	 * 
	 * @param request the request
	 * @return the response
	 */
	public ResponsePing failAuthenticated(RequestPingAuthenticated request) throws MetaqrcodeException;

}
