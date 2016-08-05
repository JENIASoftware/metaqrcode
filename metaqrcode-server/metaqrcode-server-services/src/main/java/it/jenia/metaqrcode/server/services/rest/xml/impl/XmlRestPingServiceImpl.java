package it.jenia.metaqrcode.server.services.rest.xml.impl;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.model.wadl.Description;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import it.jenia.metaqrcode.dto.ping.RequestPingAnonymous;
import it.jenia.metaqrcode.dto.ping.RequestPingAuthenticated;
import it.jenia.metaqrcode.dto.ping.ResponsePing;
import it.jenia.metaqrcode.server.commons.constants.Constants;
import it.jenia.metaqrcode.server.services.impl.PingServiceImpl;

@Service(value="metaqrcode-server-services-XmlRestPingServiceImpl")
@Description (lang=Constants.DESCRIPTION_LOCALE, title=PingServiceImpl.service_TITLE, value=PingServiceImpl.service_DESC)
/**
 * expose metaqrcode ping services as REST XML
 * 
 * @author andreatessaro
 *
 */
@CrossOriginResourceSharing(
        allowOrigins = {
           "*"
        }, 
        allowCredentials = false, 
        maxAge = 1, 
        allowHeaders = {
           "accept", "content-type"
        }
)
public class XmlRestPingServiceImpl extends PingServiceImpl {

	@POST
	@Produces({ MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_XML })
	@Path("/nowAnonymous")
	@CrossOriginResourceSharing(allowAllOrigins = true)
	@Description (lang=Constants.DESCRIPTION_LOCALE, title=pingAnonymous_TITLE, value=pingAnonymous_DESC)
	@Secured(Constants.ROLE_ANONYMOUS)
	/**
	 * @see it.jenia.metaqrcode.server.services.PingService#pingAnonymous(RequestPingAnonymous request)
	 */
	public ResponsePing pingAnonymous(RequestPingAnonymous request, @Context HttpServletResponse httpServletResponse) {
		ResponsePing ret = super.pingAnonymous(request);
		getHttpUtils().calculateStatusCode(ret, httpServletResponse);
		return ret;
	}

	@POST
	@Produces({ MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_XML })
	@Path("/failAnonymous")
	@CrossOriginResourceSharing(allowAllOrigins = true)
	@Description (lang=Constants.DESCRIPTION_LOCALE, title=failAnonymous_TITLE, value=failAnonymous_DESC)
	@Secured(Constants.ROLE_ANONYMOUS)
	/**
	 * @see it.jenia.metaqrcode.server.services.PingService#failAnonymous(RequestPingAnonymous request)
	 */
	public ResponsePing failAnonymous(RequestPingAnonymous request, @Context HttpServletResponse httpServletResponse) {
		ResponsePing ret = super.failAnonymous(request);
		getHttpUtils().calculateStatusCode(ret, httpServletResponse);
		return ret;
	}

	@POST
	@Produces({ MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_XML })
	@Path("/nowAuthenticated")
	@CrossOriginResourceSharing(allowAllOrigins = true)
	@Description (lang=Constants.DESCRIPTION_LOCALE, title=pingAuthenticated_TITLE, value=pingAuthenticated_DESC)
	@Secured(Constants.ROLE_USER)
	/**
	 * @see it.jenia.metaqrcode.server.services.PingService#pingAuthenticated(RequestPingAuthenticated request)
	 */
	public ResponsePing pingAuthenticated(RequestPingAuthenticated request, @Context HttpServletResponse httpServletResponse) {
		ResponsePing ret = super.pingAuthenticated(request);
		getHttpUtils().calculateStatusCode(ret, httpServletResponse);
		return ret;
	}

	@POST
	@Produces({ MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_XML })
	@Path("/failAuthenticated")
	@CrossOriginResourceSharing(allowAllOrigins = true)
	@Description (lang=Constants.DESCRIPTION_LOCALE, title=failAuthenticated_TITLE, value=failAuthenticated_DESC)
	@Secured(Constants.ROLE_USER)
	/**
	 * @see it.jenia.metaqrcode.server.services.PingService#failAuthenticated(RequestPingAuthenticated request)
	 */
	public ResponsePing failAuthenticated(RequestPingAuthenticated request, @Context HttpServletResponse httpServletResponse) {
		ResponsePing ret = super.failAuthenticated(request);
		getHttpUtils().calculateStatusCode(ret, httpServletResponse);
		return ret;
	}

}
