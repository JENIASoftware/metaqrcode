package it.jenia.metaqrcode.server.services.soap.impl;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.ws.rs.core.Context;
import javax.xml.ws.WebServiceContext;

import org.apache.cxf.annotations.WSDLDocumentation;
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
import it.jenia.metaqrcode.server.commons.exception.MetaqrcodeException;
import it.jenia.metaqrcode.server.services.impl.CatalogServiceImpl;

@Service(value="metaqrcode-server-services-SoapCatalogServiceImpl")
@WebService
@WSDLDocumentation(value=CatalogServiceImpl.service_TITLE+" : "+CatalogServiceImpl.service_DESC)
/**
 * expose metaqrcode catalog entry (XSD) services as soap WS
 * 
 * @author andreatessaro
 *
 */
public class SoapCatalogServiceImpl extends CatalogServiceImpl {

	@WSDLDocumentation(value=upload_TITLE+" : "+upload_DESC)
	/**
	 * @see it.jenia.metaqrcode.server.services.CatalogService#upload(RequestCatalogUpload request, byte[] xsd)
	 */
	public @WebResult ResponseCatalogUpload upload(@WebParam RequestCatalogUpload request, @WebParam byte[] xsd, @Context WebServiceContext wsContext) {
		return super.upload(request, xsd);
	}

	@WSDLDocumentation(value=search_TITLE+" : "+search_DESC)
	/**
	 * @see it.jenia.metaqrcode.server.services.CatalogService#search(RequestCatalogSearch request)
	 */
	public @WebResult ResponseCatalogSearch search(@WebParam RequestCatalogSearch request, @Context WebServiceContext wsContext) {
		return super.search(request);
	}

	@WSDLDocumentation(value=detail_TITLE+" : "+detail_DESC)
	/**
	 * @see it.jenia.metaqrcode.server.services.CatalogService#detail(RequestCatalogDetail request)
	 */
	public @WebResult ResponseCatalogDetail detail(@WebParam RequestCatalogDetail request, @Context WebServiceContext wsContext) {
		return super.detail(request);
	}

	@WSDLDocumentation(value=delete_TITLE+" : "+delete_DESC)
	/**
	 * @see it.jenia.metaqrcode.server.services.CatalogService#delete(RequestCatalogDelete request)
	 */
	public @WebResult ResponseCatalogDelete delete(@WebParam RequestCatalogDelete request, @Context WebServiceContext wsContext) {
		return super.delete(request);
	}

	@WSDLDocumentation(value=download_TITLE+" : "+download_DESC)
	/**
	 * @see it.jenia.metaqrcode.server.services.CatalogService#download(RequestCatalogDownload request)
	 */
	public @WebResult byte[] download(@WebParam RequestCatalogDownload request, @Context WebServiceContext wsContext) throws MetaqrcodeException {
		try {
			return super.download(request);
		} catch (MetaqrcodeException e) {
			throw e;
		} catch (Throwable e) {
			throw new MetaqrcodeException(-1,e);
		} finally {
			// nothing to do...
		}
	}

}
