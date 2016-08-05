package it.jenia.metaqrcode.server.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import it.jenia.metaqrcode.dto.ping.RequestPingAnonymous;
import it.jenia.metaqrcode.dto.ping.RequestPingAuthenticated;
import it.jenia.metaqrcode.dto.ping.ResponsePing;
import it.jenia.metaqrcode.server.commons.exception.MetaqrcodeException;
import it.jenia.metaqrcode.server.core.api.PingApiService;
import it.jenia.metaqrcode.server.services.PingService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
/**
 * generic implementation of PingService
 * @see it.jenia.metaqrcode.server.services.PingService
 * 
 * @author andreatessaro
 *
 */
public class PingServiceImpl extends AbstractMetaqrcodeService implements PingService {
	
	public static final String service_TITLE = "PING service";
	public static final String service_DESC = "service to manage ping on METAQRCODE server";

	@Autowired
	private PingApiService pingApiService;

	protected static final String pingAnonymous_TITLE = "do an anonimous ping";
	protected static final String pingAnonymous_DESC = "tipically used as an hello world or to see if metaqrcode server is up and running";
	@Override
	/**
	 * @see it.jenia.metaqrcode.server.services.PingService#pingAnonymous(RequestPingAnonymous request)
	 */
	public ResponsePing pingAnonymous(RequestPingAnonymous request) {
		try {
			if (log.isDebugEnabled()) log.debug("ping called from " + request.getFrom());
			ResponsePing ret = pingApiService.pingAnonymous(request);
			if (log.isDebugEnabled()) log.debug("ping : success");
			return ret;
		} catch (MetaqrcodeException e) {
			if (log.isErrorEnabled()) {
				log.error("Generic error on ping service",e);
			}
			ResponsePing ret = new ResponsePing();
			ret.setReturnCode(e.getReturnCode());
			ret.setReason(e.getMessage());
			return ret;
		} catch (Throwable e) {
			if (log.isErrorEnabled()) {
				log.error("Generic error on ping service",e);
			}
			ResponsePing ret = new ResponsePing();
			ret.setReturnCode(-1);
			ret.setReason(e.getMessage());
			return ret;
		} finally {
			// nothing to do...
		}
	}

	protected static final String failAnonymous_TITLE = "do an anonimous ping; the service will fail";
	protected static final String failAnonymous_DESC = "tipically used to see how to manage error conditions (in dev mode)";
	@Override
	/**
	 * @see it.jenia.metaqrcode.server.services.PingService#failAnonymous(RequestPingAnonymous request)
	 */
	public ResponsePing failAnonymous(RequestPingAnonymous request) {
		try {
			if (log.isDebugEnabled()) log.debug("ping called from " + request.getFrom() + " will fail");
			throw new MetaqrcodeException(-1,"ping request fail as requested");
		} catch (MetaqrcodeException e) {
			if (log.isErrorEnabled()) {
				log.error("Generic error on fail service",e);
			}
			ResponsePing ret = new ResponsePing();
			ret.setReturnCode(e.getReturnCode());
			ret.setReason(e.getMessage());
			return ret;
		} catch (Throwable e) {
			if (log.isErrorEnabled()) {
				log.error("Generic error on fail service",e);
			}
			ResponsePing ret = new ResponsePing();
			ret.setReturnCode(-1);
			ret.setReason(e.getMessage());
			return ret;
		} finally {
			// nothing to do...
		}
	}

	protected static final String pingAuthenticated_TITLE = "do an authenticated ping; the service will fail";
	protected static final String pingAuthenticated_DESC = "tipically used as an hello world or to see if metaqrcode server is up and running";
	@Override
	/**
	 * @see it.jenia.metaqrcode.server.services.PingService#pingAuthenticated(RequestPingAuthenticated request)
	 */
	public ResponsePing pingAuthenticated(RequestPingAuthenticated request) {
		try {
			if (log.isDebugEnabled()) log.debug("ping called from " + request.getFrom());
			ResponsePing ret = pingApiService.pingAuthenticated(request);
			if (log.isDebugEnabled()) log.debug("ping : success");
			return ret;
		} catch (MetaqrcodeException e) {
			if (log.isErrorEnabled()) {
				log.error("Generic error on ping service",e);
			}
			ResponsePing ret = new ResponsePing();
			ret.setReturnCode(e.getReturnCode());
			ret.setReason(e.getMessage());
			return ret;
		} catch (Throwable e) {
			if (log.isErrorEnabled()) {
				log.error("Generic error on ping service",e);
			}
			ResponsePing ret = new ResponsePing();
			ret.setReturnCode(-1);
			ret.setReason(e.getMessage());
			return ret;
		} finally {
			// nothing to do...
		}
	}

	protected static final String failAuthenticated_TITLE = "do an authenticated ping; the service will fail";
	protected static final String failAuthenticated_DESC = "tipically used to see how to manage error conditions (in dev mode)";
	@Override
	/**
	 * @see it.jenia.metaqrcode.server.services.PingService#failAuthenticated(RequestPingAuthenticated request)
	 */
	public ResponsePing failAuthenticated(RequestPingAuthenticated request) {
		try {
			if (log.isDebugEnabled()) log.debug("ping called from " + request.getFrom() + " will fail");
			throw new MetaqrcodeException(-1,"ping request fail as requested");
		} catch (MetaqrcodeException e) {
			if (log.isErrorEnabled()) {
				log.error("Generic error on fail service",e);
			}
			ResponsePing ret = new ResponsePing();
			ret.setReturnCode(e.getReturnCode());
			ret.setReason(e.getMessage());
			return ret;
		} catch (Throwable e) {
			if (log.isErrorEnabled()) {
				log.error("Generic error on fail service",e);
			}
			ResponsePing ret = new ResponsePing();
			ret.setReturnCode(-1);
			ret.setReason(e.getMessage());
			return ret;
		} finally {
			// nothing to do...
		}
	}

}
