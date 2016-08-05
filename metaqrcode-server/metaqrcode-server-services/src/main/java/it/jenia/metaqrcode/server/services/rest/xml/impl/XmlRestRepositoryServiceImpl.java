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

import it.jenia.metaqrcode.dto.repository.RequestRepositoryDelete;
import it.jenia.metaqrcode.dto.repository.RequestRepositoryDetail;
import it.jenia.metaqrcode.dto.repository.RequestRepositoryDownload;
import it.jenia.metaqrcode.dto.repository.RequestRepositoryQRCode;
import it.jenia.metaqrcode.dto.repository.RequestRepositorySearch;
import it.jenia.metaqrcode.dto.repository.RequestRepositoryUpdate;
import it.jenia.metaqrcode.dto.repository.RequestRepositoryUpload;
import it.jenia.metaqrcode.dto.repository.ResponseRepositoryDelete;
import it.jenia.metaqrcode.dto.repository.ResponseRepositoryDetail;
import it.jenia.metaqrcode.dto.repository.ResponseRepositorySearch;
import it.jenia.metaqrcode.dto.repository.ResponseRepositoryUpdate;
import it.jenia.metaqrcode.dto.repository.ResponseRepositoryUpload;
import it.jenia.metaqrcode.server.commons.constants.Constants;
import it.jenia.metaqrcode.server.services.impl.RepositoryServiceImpl;

@Service(value="metaqrcode-server-services-XmlRestRepositoryServiceImpl")
@Description (lang=Constants.DESCRIPTION_LOCALE, title=RepositoryServiceImpl.service_TITLE, value=RepositoryServiceImpl.service_DESC)
/**
 * expose metaqrcode repository entry (XML) services as REST XML
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
public class XmlRestRepositoryServiceImpl extends RepositoryServiceImpl {

	@POST
	@Produces({ MediaType.APPLICATION_XML})
    @Consumes("multipart/*")
	@Path("/upload")
	@CrossOriginResourceSharing(allowAllOrigins = true)
	@Description (lang=Constants.DESCRIPTION_LOCALE, title=upload_TITLE, value=upload_DESC)
	@Secured(Constants.ROLE_USER)
	/**
	 * @see it.jenia.metaqrcode.server.services.RepositoryService#upload(RequestRepositoryUpload request, byte[] xml)
	 */
	public ResponseRepositoryUpload upload( @Multipart(type="*/xml") RequestRepositoryUpload request, @Multipart(required=true, value="xml", type="*/xml") byte[] xml, @Context HttpServletResponse httpServletResponse) {
		ResponseRepositoryUpload ret = super.upload(request, xml);
		getHttpUtils().calculateStatusCode(ret, httpServletResponse);
		return ret;
	}

	@POST
	@Produces({ MediaType.APPLICATION_XML})
    @Consumes("multipart/*")
	@Path("/update")
	@CrossOriginResourceSharing(allowAllOrigins = true)
	@Description (lang=Constants.DESCRIPTION_LOCALE, title=update_TITLE, value=update_DESC)
	@Secured(Constants.ROLE_USER)
	/**
	 * @see it.jenia.metaqrcode.server.services.RepositoryService#update(RequestRepositoryUpdate request, byte[] xml)
	 */
	public ResponseRepositoryUpdate update(@Multipart(type="*/xml") RequestRepositoryUpdate request, @Multipart(required=true, value="xml", type="*/xml") byte[] xml, @Context HttpServletResponse httpServletResponse) {
		ResponseRepositoryUpdate ret = super.update(request, xml);
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
	 * @see it.jenia.metaqrcode.server.services.RepositoryService#delete(RequestRepositoryDelete request)
	 */
	public ResponseRepositoryDelete delete(RequestRepositoryDelete request, @Context HttpServletResponse httpServletResponse) {
		ResponseRepositoryDelete ret = super.delete(request);
		getHttpUtils().calculateStatusCode(ret, httpServletResponse);
		return ret;
	}

	@POST
	@Produces({ MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_XML })
	@Path("/search")
	@CrossOriginResourceSharing(allowAllOrigins = true)
	@Description (lang=Constants.DESCRIPTION_LOCALE, title=search_TITLE, value=search_DESC)
	@Secured(Constants.ROLE_USER)
	/**
	 * @see it.jenia.metaqrcode.server.services.RepositoryService#search(RequestRepositorySearch request)
	 */
	public ResponseRepositorySearch search(RequestRepositorySearch request, @Context HttpServletResponse httpServletResponse) {
		ResponseRepositorySearch ret = super.search(request);
		getHttpUtils().calculateStatusCode(ret, httpServletResponse);
		return ret;
	}

	@POST
	@Produces({ MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_XML })
	@Path("/detail")
	@CrossOriginResourceSharing(allowAllOrigins = true)
	@Description (lang=Constants.DESCRIPTION_LOCALE, title=detail_TITLE, value=detail_DESC)
	@Secured(Constants.ROLE_USER)
	/**
	 * @see it.jenia.metaqrcode.server.services.RepositoryService#detail(RequestRepositoryDetail request)
	 */
	public ResponseRepositoryDetail detail(RequestRepositoryDetail request, @Context HttpServletResponse httpServletResponse) {
		ResponseRepositoryDetail ret = super.detail(request);
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
	 * @see it.jenia.metaqrcode.server.services.RepositoryService#download(RequestRepositoryDownload request)
	 */
	public byte[] download(RequestRepositoryDownload request, @Context HttpServletResponse httpServletResponse) {
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
	@Produces({ MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_XML })
	@Path("/downloadAsJson")
	@CrossOriginResourceSharing(allowAllOrigins = true)
	@Description (lang=Constants.DESCRIPTION_LOCALE, title=downloadAsJson_TITLE, value=downloadAsJson_DESC)
	@Secured(Constants.ROLE_ANONYMOUS)
	/**
	 * @see it.jenia.metaqrcode.server.services.RepositoryService#downloadAsJson(RequestRepositoryDownload request)
	 */
	public byte[] downloadAsJson(RequestRepositoryDownload request, @Context HttpServletResponse httpServletResponse) {
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

	@POST
	@Produces("image/png")
	@Consumes({ MediaType.APPLICATION_XML })
	@Path("/qrcode")
	@CrossOriginResourceSharing(allowAllOrigins = true)
	@Description (lang=Constants.DESCRIPTION_LOCALE, title=qrCode_TITLE, value=qrCode_DESC)
	@Secured(Constants.ROLE_ANONYMOUS)
	/**
	 * @see it.jenia.metaqrcode.server.services.RepositoryService#qrCode(RequestRepositoryQRCode request)
	 */
	public byte[] qrCode(RequestRepositoryQRCode request, @Context HttpServletResponse httpServletResponse) {
		try {
			byte[] ret = super.qrCode(request);
			return ret;
		} catch (Throwable e) {
			httpServletResponse.setStatus(404);
			return e.getMessage().getBytes();
		} finally {
			// nothing to do...
		}
	}

}
