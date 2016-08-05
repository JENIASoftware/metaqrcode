package it.jenia.metaqrcode.server.services.soap.impl;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.ws.rs.core.Context;
import javax.xml.ws.WebServiceContext;

import org.apache.cxf.annotations.WSDLDocumentation;
import org.springframework.stereotype.Service;

import it.jenia.metaqrcode.dto.link.RequestLinkCreate;
import it.jenia.metaqrcode.dto.link.RequestLinkDelete;
import it.jenia.metaqrcode.dto.link.RequestLinkDetail;
import it.jenia.metaqrcode.dto.link.RequestLinkDownload;
import it.jenia.metaqrcode.dto.link.ResponseLinkCreate;
import it.jenia.metaqrcode.dto.link.ResponseLinkDelete;
import it.jenia.metaqrcode.dto.link.ResponseLinkDetail;
import it.jenia.metaqrcode.server.commons.exception.MetaqrcodeException;
import it.jenia.metaqrcode.server.services.impl.LinkServiceImpl;

@Service(value="metaqrcode-server-services-SoapLinkServiceImpl")
@WebService
@WSDLDocumentation(value=LinkServiceImpl.service_TITLE+" : "+LinkServiceImpl.service_DESC)
/**
 * expose metaqrcode link services as soap WS
 * 
 * @author andreatessaro
 *
 */
public class SoapLinkServiceImpl extends LinkServiceImpl {

	@WSDLDocumentation(value=create_TITLE+" : "+create_DESC)
	/**
	 * @see it.jenia.metaqrcode.server.services.LinkService#create(RequestLinkCreate request)
	 */
	public @WebResult ResponseLinkCreate create(@WebParam RequestLinkCreate request, @Context WebServiceContext wsContext) {
		return super.create(request);
	}

	@WSDLDocumentation(value=delete_TITLE+" : "+delete_DESC)
	/**
	 * @see it.jenia.metaqrcode.server.services.LinkService#delete(RequestLinkDelete request) {
	 */
	public @WebResult ResponseLinkDelete delete(@WebParam RequestLinkDelete request, @Context WebServiceContext wsContext) {
		return super.delete(request);
	}

	@WSDLDocumentation(value=detail_TITLE+" : "+detail_DESC)
	/**
	 * @see it.jenia.metaqrcode.server.services.LinkService#detail(RequestLinkDetail request) {
	 */
	public @WebResult ResponseLinkDetail detail(@WebParam RequestLinkDetail request, @Context WebServiceContext wsContext) {
		return super.detail(request);
	}

	@WSDLDocumentation(value=download_TITLE+" : "+download_DESC)
	/**
	 * @see it.jenia.metaqrcode.server.services.LinkService#download(RequestLinkDownload request) {
	 */
	public @WebResult byte[] download(@WebParam RequestLinkDownload request, @Context WebServiceContext wsContext) throws MetaqrcodeException {
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

	@WSDLDocumentation(value=downloadAsJson_TITLE+" : "+downloadAsJson_DESC)
	/**
	 * @see it.jenia.metaqrcode.server.services.LinkService#downloadAsJson(RequestLinkDownload request) {
	 */
	public @WebResult byte[] downloadAsJson(@WebParam RequestLinkDownload request, @Context WebServiceContext wsContext) throws MetaqrcodeException {
		try {
			return super.downloadAsJson(request);
		} catch (MetaqrcodeException e) {
			throw e;
		} catch (Throwable e) {
			throw new MetaqrcodeException(-1,e);
		} finally {
			// nothing to do...
		}
	}

}
