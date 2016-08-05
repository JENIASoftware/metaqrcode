package it.jenia.metaqrcode.server.services.rest.basic.impl;

import java.math.BigInteger;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.codec.binary.Base64;
import org.apache.cxf.jaxrs.model.wadl.Description;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import it.jenia.metaqrcode.dto.catalog.ResponseCatalogDetail;
import it.jenia.metaqrcode.dto.link.ResponseLinkDetail;
import it.jenia.metaqrcode.dto.repository.ResponseRepositoryDetail;
import it.jenia.metaqrcode.server.commons.constants.Constants;
import it.jenia.metaqrcode.server.core.exception.CatalogEntryNotFoundException;
import it.jenia.metaqrcode.server.services.exception.GenericException;
import it.jenia.metaqrcode.server.services.impl.BasicServiceImpl;

@Service(value="metaqrcode-server-services-BasicRestServiceImpl")
@Description (lang=Constants.DESCRIPTION_LOCALE, title=BasicRestServiceImpl.service_TITLE, value=BasicRestServiceImpl.service_DESC)
/**
 * expose metaqrcode basic services as simple http get
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
public class BasicRestServiceImpl extends BasicServiceImpl {

	@GET
	@Produces({ MediaType.APPLICATION_XML})
	@Path("/c/{id}")
	@CrossOriginResourceSharing(allowAllOrigins = true)
	@Description (lang=Constants.DESCRIPTION_LOCALE, title=getCatalog_TITLE, value=getCatalog_DESC)
	@Secured(Constants.ROLE_ANONYMOUS)
	/**
	 * @see it.jenia.metaqrcode.server.services.BasicService#getCatalog(BigInteger id)
	 */
	public byte[] getCatalog(@PathParam(value="id") BigInteger id, @Context HttpServletResponse httpServletResponse) throws CatalogEntryNotFoundException, GenericException {
		try {
			return super.getCatalog(id);
		} catch (Throwable e) {
			httpServletResponse.setStatus(404);
			return e.getMessage().getBytes();
		}
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("/c/{id}/detail")
	@CrossOriginResourceSharing(allowAllOrigins = true)
	@Description (lang=Constants.DESCRIPTION_LOCALE, title=getCatalogDetail_TITLE, value=getCatalogDetail_DESC)
	@Secured(Constants.ROLE_ANONYMOUS)
	/**
	 * @see it.jenia.metaqrcode.server.services.BasicService#getCatalogDetail(BigInteger id)
	 */
	public ResponseCatalogDetail getCatalogDetail(@PathParam(value="id") BigInteger id, @Context HttpServletResponse httpServletResponse) throws CatalogEntryNotFoundException, GenericException {
		ResponseCatalogDetail ret = super.getCatalogDetail(id);
		getHttpUtils().calculateStatusCode(ret, httpServletResponse);
		return ret;
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML})
	@Path("/r/{id}")
	@CrossOriginResourceSharing(allowAllOrigins = true)
	@Description (lang=Constants.DESCRIPTION_LOCALE, title=getRepository_TITLE, value=getRepository_DESC)
	@Secured(Constants.ROLE_ANONYMOUS)
	/**
	 * @see it.jenia.metaqrcode.server.services.BasicService#getRepository(BigInteger id)
	 */
	public byte[] getRepository(@PathParam(value="id") BigInteger id, @Context HttpServletResponse httpServletResponse) {
		try {
			return super.getRepository(id);
		} catch (Throwable e) {
			httpServletResponse.setStatus(404);
			return e.getMessage().getBytes();
		}
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("/r/{id}/json")
	@CrossOriginResourceSharing(allowAllOrigins = true)
	@Description (lang=Constants.DESCRIPTION_LOCALE, title=getRepositoryAsJson_TITLE, value=getRepositoryAsJson_DESC)
	@Secured(Constants.ROLE_ANONYMOUS)
	/**
	 * @see it.jenia.metaqrcode.server.services.BasicService#getRepositoryAsJson(BigInteger id)
	 */
	public byte[] getRepositoryAsJson(@PathParam(value="id") BigInteger id, @Context HttpServletResponse httpServletResponse) {
		try {
			return super.getRepositoryAsJson(id);
		} catch (Throwable e) {
			httpServletResponse.setStatus(404);
			return e.getMessage().getBytes();
		}
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("/r/{id}/detail")
	@CrossOriginResourceSharing(allowAllOrigins = true)
	@Description (lang=Constants.DESCRIPTION_LOCALE, title=getRepositoryDetail_TITLE, value=getRepositoryDetail_DESC)
	@Secured(Constants.ROLE_ANONYMOUS)
	/**
	 * @see it.jenia.metaqrcode.server.services.BasicService#getRepositoryDetail(BigInteger id)
	 */
	public ResponseRepositoryDetail getRepositoryDetail(@PathParam(value="id") BigInteger id, @Context HttpServletResponse httpServletResponse) {
		ResponseRepositoryDetail ret = super.getRepositoryDetail(id);
		getHttpUtils().calculateStatusCode(ret, httpServletResponse);
		return ret;
	}

	@GET
	@Produces("image/png")
	@Path("/qr/{id}")
	@CrossOriginResourceSharing(allowAllOrigins = true)
	@Description (lang=Constants.DESCRIPTION_LOCALE, title=getQRCode_TITLE, value=getQRCode_DESC)
	@Secured(Constants.ROLE_ANONYMOUS)
	/**
	 * @see it.jenia.metaqrcode.server.services.BasicService#getQRCode(BigInteger id)
	 */
	public byte[] getQRCode(@PathParam(value="id") BigInteger id, @Context HttpServletResponse httpServletResponse) {
		try {
			return super.getQRCode(id);
		} catch (Throwable e) {
			httpServletResponse.setStatus(404);
			return e.getMessage().getBytes();
		}
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML})
	@Path("/l/{oc}")
	@CrossOriginResourceSharing(allowAllOrigins = true)
	@Description (lang=Constants.DESCRIPTION_LOCALE, title=getLink_TITLE, value=getLink_DESC)
	@Secured(Constants.ROLE_ANONYMOUS)
	/**
	 * @see it.jenia.metaqrcode.server.services.BasicService#getLink(String otherCode)
	 */
	public byte[] getLink(@PathParam(value="oc") String otherCode, @Context HttpServletResponse httpServletResponse) {
		try {
			return super.getLink(new String(Base64.decodeBase64(otherCode)));
		} catch (Throwable e) {
			httpServletResponse.setStatus(404);
			return e.getMessage().getBytes();
		}
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("/l/{oc}/json")
	@CrossOriginResourceSharing(allowAllOrigins = true)
	@Description (lang=Constants.DESCRIPTION_LOCALE, title=getLinkAsJson_TITLE, value=getLinkAsJson_DESC)
	@Secured(Constants.ROLE_ANONYMOUS)
	/**
	 * @see it.jenia.metaqrcode.server.services.BasicService#getLinkAsJson(String otherCode)
	 */
	public byte[] getLinkAsJson(@PathParam(value="oc") String otherCode, @Context HttpServletResponse httpServletResponse) {
		try {
			return super.getLinkAsJson(new String(Base64.decodeBase64(otherCode)));
		} catch (Throwable e) {
			httpServletResponse.setStatus(404);
			return e.getMessage().getBytes();
		}
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("/l/{oc}/detail")
	@CrossOriginResourceSharing(allowAllOrigins = true)
	@Description (lang=Constants.DESCRIPTION_LOCALE, title=getLinkDetail_TITLE, value=getLinkDetail_DESC)
	@Secured(Constants.ROLE_ANONYMOUS)
	/**
	 * @see it.jenia.metaqrcode.server.services.BasicService#getLinkDetail(String otherCode)
	 */
	public ResponseLinkDetail getLinkDetail(@PathParam(value="oc") String otherCode, @Context HttpServletResponse httpServletResponse) {
		ResponseLinkDetail ret = super.getLinkDetail(otherCode);
		getHttpUtils().calculateStatusCode(ret, httpServletResponse);
		return ret;
	}

}
