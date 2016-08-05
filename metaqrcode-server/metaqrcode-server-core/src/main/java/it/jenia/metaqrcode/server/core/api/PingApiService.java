package it.jenia.metaqrcode.server.core.api;

import it.jenia.metaqrcode.dto.ping.RequestPingAnonymous;
import it.jenia.metaqrcode.dto.ping.RequestPingAuthenticated;
import it.jenia.metaqrcode.dto.ping.ResponsePing;
import it.jenia.metaqrcode.server.commons.exception.MetaqrcodeException;

/**
 * ping service (used only for developing and testing
 * 
 * @author andreatessaro
 *
 */
public interface PingApiService {
	
	/**
	 * make a ping to metaqrcode server
	 * you do not need to have a session token to run this method
	 * 
	 * @param request
	 * @return
	 */
	public ResponsePing pingAnonymous(RequestPingAnonymous request) throws MetaqrcodeException;

	/**
	 * make a ping to metaqrcode server
	 * you do need to have a session token to run this method
	 * 
	 * @param request
	 * @return
	 */
	public ResponsePing pingAuthenticated(RequestPingAuthenticated request) throws MetaqrcodeException;

}
