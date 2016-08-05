package it.jenia.metaqrcode.server.services.rest.xml.impl;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.apache.cxf.jaxrs.model.wadl.Description;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import it.jenia.metaqrcode.dto.catalog.RequestCatalogDelete;
import it.jenia.metaqrcode.dto.catalog.RequestCatalogDetail;
import it.jenia.metaqrcode.dto.catalog.RequestCatalogDownload;
import it.jenia.metaqrcode.dto.catalog.RequestCatalogSearch;
import it.jenia.metaqrcode.dto.catalog.RequestCatalogUpload;
import it.jenia.metaqrcode.dto.catalog.ResponseCatalogDelete;
import it.jenia.metaqrcode.dto.catalog.ResponseCatalogDetail;
import it.jenia.metaqrcode.dto.catalog.ResponseCatalogSearch;
import it.jenia.metaqrcode.dto.catalog.ResponseCatalogUpload;
import it.jenia.metaqrcode.server.commons.constants.Constants;
import it.jenia.metaqrcode.server.services.impl.CatalogServiceImpl;

@Service(value="metaqrcode-server-services-XmlRestCatalogServiceImpl")
@Description (lang=Constants.DESCRIPTION_LOCALE, title=CatalogServiceImpl.service_TITLE, value=CatalogServiceImpl.service_DESC)
/**
 * expose metaqrcode catalog entry (XSD) services as REST XML
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
public class XmlRestCatalogServiceImpl extends CatalogServiceImpl {

	@POST
	@Produces({ MediaType.APPLICATION_XML})
    @Consumes("multipart/*")
	@Path("/upload")
	@CrossOriginResourceSharing(allowAllOrigins = true)
	@Description (lang=Constants.DESCRIPTION_LOCALE, title=upload_TITLE, value=upload_DESC)
	@Secured(Constants.ROLE_USER)
	/**
	 * @see it.jenia.metaqrcode.server.services.CatalogService#upload(RequestCatalogUpload request, byte[] xsd)
	 */
	public ResponseCatalogUpload upload( @Multipart(type="*/xml") RequestCatalogUpload request, @Multipart(required=true, value="xsd", type="*/xml") byte[] xsd, @Context HttpServletResponse httpServletResponse) {
		ResponseCatalogUpload ret = super.upload(request, xsd);
		getHttpUtils().calculateStatusCode(ret, httpServletResponse);
		return ret;
	}

	@POST
	@Produces({ MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_XML })
	@Path("/search")
	@CrossOriginResourceSharing(allowAllOrigins = true)
	@Description (lang=Constants.DESCRIPTION_LOCALE, title=search_TITLE, value=search_DESC)
	@Secured(Constants.ROLE_ANONYMOUS)
	/**
	 * @see it.jenia.metaqrcode.server.services.CatalogService#search(RequestCatalogSearch request)
	 */
	public ResponseCatalogSearch search(RequestCatalogSearch request, @Context HttpServletResponse httpServletResponse) {
		ResponseCatalogSearch ret = super.search(request);
		getHttpUtils().calculateStatusCode(ret, httpServletResponse);
		return ret;
	}

	@POST
	@Produces({ MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_XML })
	@Path("/detail")
	@CrossOriginResourceSharing(allowAllOrigins = true)
	@Description (lang=Constants.DESCRIPTION_LOCALE, title=detail_TITLE, value=detail_DESC)
	@Secured(Constants.ROLE_ANONYMOUS)
	/**
	 * @see it.jenia.metaqrcode.server.services.CatalogService#detail(RequestCatalogDetail request)
	 */
	public ResponseCatalogDetail detail(RequestCatalogDetail request, @Context HttpServletResponse httpServletResponse) {
		ResponseCatalogDetail ret = super.detail(request);
		getHttpUtils().calculateStatusCode(ret, httpServletResponse);
		return ret;
	}

	@POST
	@Produces({ MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_XML })
	@Path("/delete")
	@CrossOriginResourceSharing(allowAllOrigins = true)
	@Description (lang=Constants.DESCRIPTION_LOCALE, title=delete_TITLE, value=delete_DESC)
	@Secured(Constants.ROLE_USER)
	/**
	 * @see it.jenia.metaqrcode.server.services.CatalogService#delete(RequestCatalogDelete request)
	 */
	public ResponseCatalogDelete delete(RequestCatalogDelete request, @Context HttpServletResponse httpServletResponse) {
		ResponseCatalogDelete ret = super.delete(request);
		getHttpUtils().calculateStatusCode(ret, httpServletResponse);
		return ret;
	}

	@POST
	@Produces({ MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_XML })
	@Path("/download")
	@CrossOriginResourceSharing(allowAllOrigins = true)
	@Description (lang=Constants.DESCRIPTION_LOCALE, title=download_TITLE, value=download_DESC)
	@Secured(Constants.ROLE_ANONYMOUS)
	/**
	 * @see it.jenia.metaqrcode.server.services.CatalogService#download(RequestCatalogDownload request)
	 */
	public byte[] download(RequestCatalogDownload request, @Context HttpServletResponse httpServletResponse) {
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

}
