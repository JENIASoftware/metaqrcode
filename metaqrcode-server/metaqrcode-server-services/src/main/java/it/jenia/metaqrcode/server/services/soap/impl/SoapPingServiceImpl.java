package it.jenia.metaqrcode.server.services.soap.impl;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.ws.rs.core.Context;
import javax.xml.ws.WebServiceContext;

import org.apache.cxf.annotations.WSDLDocumentation;
import org.springframework.stereotype.Service;

import it.jenia.metaqrcode.dto.ping.RequestPingAnonymous;
import it.jenia.metaqrcode.dto.ping.RequestPingAuthenticated;
import it.jenia.metaqrcode.dto.ping.ResponsePing;
import it.jenia.metaqrcode.server.services.impl.PingServiceImpl;

@Service(value="metaqrcode-server-services-SoapPingServiceImpl")
@WebService
@WSDLDocumentation(value=PingServiceImpl.service_TITLE+" : "+PingServiceImpl.service_DESC)
/**
 * expose metaqrcode ping services as soap WS
 * 
 * @author andreatessaro
 *
 */
public class SoapPingServiceImpl extends PingServiceImpl {

	@WSDLDocumentation(value=pingAnonymous_TITLE+" : "+pingAnonymous_DESC)
	/**
	 * @see it.jenia.metaqrcode.server.services.PingService#pingAnonymous(RequestPingAnonymous request)
	 */
	public @WebResult ResponsePing pingAnonymous(@WebParam RequestPingAnonymous request, @Context WebServiceContext wsContext) {
		return super.pingAnonymous(request);
	}

	@WSDLDocumentation(value=failAnonymous_TITLE+" : "+failAnonymous_DESC)
	/**
	 * @see it.jenia.metaqrcode.server.services.PingService#failAnonymous(RequestPingAnonymous request)
	 */
	public @WebResult ResponsePing failAnonymous(@WebParam RequestPingAnonymous request, @Context WebServiceContext wsContext) {
		return super.failAnonymous(request);
	}

	@WSDLDocumentation(value=pingAuthenticated_TITLE+" : "+pingAuthenticated_DESC)
	/**
	 * @see it.jenia.metaqrcode.server.services.PingService#pingAuthenticated(RequestPingAuthenticated request)
	 */
	public @WebResult ResponsePing pingAuthenticated(@WebParam RequestPingAuthenticated request, @Context WebServiceContext wsContext) {
		return super.pingAuthenticated(request);
	}

	@WSDLDocumentation(value=failAuthenticated_TITLE+" : "+failAuthenticated_DESC)
	/**
	 * @see it.jenia.metaqrcode.server.services.PingService#failAuthenticated(RequestPingAuthenticated request)
	 */
	public @WebResult ResponsePing failAuthenticated(@WebParam RequestPingAuthenticated request, @Context WebServiceContext wsContext) {
		return super.failAuthenticated(request);
	}

}
