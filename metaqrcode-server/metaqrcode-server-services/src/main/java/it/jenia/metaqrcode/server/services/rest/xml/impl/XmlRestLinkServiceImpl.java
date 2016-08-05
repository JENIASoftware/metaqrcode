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

import it.jenia.metaqrcode.dto.link.RequestLinkCreate;
import it.jenia.metaqrcode.dto.link.RequestLinkDelete;
import it.jenia.metaqrcode.dto.link.RequestLinkDetail;
import it.jenia.metaqrcode.dto.link.RequestLinkDownload;
import it.jenia.metaqrcode.dto.link.ResponseLinkCreate;
import it.jenia.metaqrcode.dto.link.ResponseLinkDelete;
import it.jenia.metaqrcode.dto.link.ResponseLinkDetail;
import it.jenia.metaqrcode.server.commons.constants.Constants;
import it.jenia.metaqrcode.server.services.impl.LinkServiceImpl;

@Service(value = "metaqrcode-server-services-XmlRestLinkServiceImpl")
@Description(lang = Constants.DESCRIPTION_LOCALE, title = LinkServiceImpl.service_TITLE, value = LinkServiceImpl.service_DESC)
/**
 * expose metaqrcode link services as REST XML
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
public class XmlRestLinkServiceImpl extends LinkServiceImpl {

	@POST
	@Produces({ MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_XML })
	@Path("/create")
	@CrossOriginResourceSharing(allowAllOrigins = true)
	@Description(lang = Constants.DESCRIPTION_LOCALE, title = create_TITLE, value = create_DESC)
	@Secured(Constants.ROLE_USER)
	/**
	 * @see it.jenia.metaqrcode.server.services.LinkService#create(RequestLinkCreate
	 *      request)
	 */
	public ResponseLinkCreate create(RequestLinkCreate request, @Context HttpServletResponse httpServletResponse) {
		ResponseLinkCreate ret = super.create(request);
		getHttpUtils().calculateStatusCode(ret, httpServletResponse);
		return ret;
	}

	@POST
	@Produces({ MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_XML })
	@Path("/delete")
	@CrossOriginResourceSharing(allowAllOrigins = true)
	@Description(lang = Constants.DESCRIPTION_LOCALE, title = delete_TITLE, value = delete_DESC)
	@Secured(Constants.ROLE_USER)
	/**
	 * @see it.jenia.metaqrcode.server.services.LinkService#delete(RequestLinkDelete
	 *      request) {
	 */
	public ResponseLinkDelete delete(RequestLinkDelete request, @Context HttpServletResponse httpServletResponse) {
		ResponseLinkDelete ret = super.delete(request);
		getHttpUtils().calculateStatusCode(ret, httpServletResponse);
		return ret;
	}

	@POST
	@Produces({ MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_XML })
	@Path("/detail")
	@CrossOriginResourceSharing(allowAllOrigins = true)
	@Description(lang = Constants.DESCRIPTION_LOCALE, title = detail_TITLE, value = detail_DESC)
	@Secured(Constants.ROLE_USER)
	/**
	 * @see it.jenia.metaqrcode.server.services.LinkService#detail(RequestLinkDetail request)
	 */
	public ResponseLinkDetail detail(RequestLinkDetail request, @Context HttpServletResponse httpServletResponse) {
		ResponseLinkDetail ret = super.detail(request);
		getHttpUtils().calculateStatusCode(ret, httpServletResponse);
		return ret;
	}

	@POST
	@Produces({ MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_XML })
	@Path("/download")
	@CrossOriginResourceSharing(allowAllOrigins = true)
	@Description(lang = Constants.DESCRIPTION_LOCALE, title = download_TITLE, value = download_DESC)
	@Secured(Constants.ROLE_ANONYMOUS)
	/**
	 * @see it.jenia.metaqrcode.server.services.LinkService#download(RequestLinkDownload
	 *      request) {
	 */
	public byte[] download(RequestLinkDownload request, @Context HttpServletResponse httpServletResponse) {
		try {
			byte[] ret = super.download(request);
			return ret;
		} catch (Throwable e) {
			httpServletResponse.setStatus(404);
			return e.getMessage().getBytes();
		} finally {
			// nothing to do...
		}
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_XML })
	@Path("/downloadAsJson")
	@CrossOriginResourceSharing(allowAllOrigins = true)
	@Description(lang = Constants.DESCRIPTION_LOCALE, title = downloadAsJson_TITLE, value = downloadAsJson_DESC)
	@Secured(Constants.ROLE_ANONYMOUS)
	/**
	 * @see it.jenia.metaqrcode.server.services.LinkService#downloadAsJson(RequestLinkDownload request) {
	 */
	public byte[] downloadAsJson(RequestLinkDownload request, @Context HttpServletResponse httpServletResponse) {
		try {
			byte[] ret = super.downloadAsJson(request);
			return ret;
		} catch (Throwable e) {
			httpServletResponse.setStatus(404);
			return e.getMessage().getBytes();
		} finally {
			// nothing to do...
		}
	}

}
