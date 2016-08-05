package it.jenia.metaqrcode.server.services.soap.impl;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.ws.rs.core.Context;
import javax.xml.ws.WebServiceContext;

import org.apache.cxf.annotations.WSDLDocumentation;
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
import it.jenia.metaqrcode.server.commons.exception.MetaqrcodeException;
import it.jenia.metaqrcode.server.services.impl.RepositoryServiceImpl;

@Service(value="metaqrcode-server-services-SoapRepositoryServiceImpl")
@WebService
@WSDLDocumentation(value=RepositoryServiceImpl.service_TITLE+" : "+RepositoryServiceImpl.service_DESC)
/**
 * expose metaqrcode repository entry (XML) services as soap WS
 * 
 * @author andreatessaro
 *
 */
public class SoapRepositoryServiceImpl extends RepositoryServiceImpl {

	@WSDLDocumentation(value=upload_TITLE+" : "+upload_DESC)
	/**
	 * @see it.jenia.metaqrcode.server.services.RepositoryService#upload(RequestRepositoryUpload request, byte[] xml)
	 */
	public @WebResult ResponseRepositoryUpload upload(@WebParam RequestRepositoryUpload request, @WebParam byte[] xml, @Context WebServiceContext wsContext) {
		return super.upload(request, xml);
	}

	@WSDLDocumentation(value=update_TITLE+" : "+update_DESC)
	/**
	 * @see it.jenia.metaqrcode.server.services.RepositoryService#update(RequestRepositoryUpdate request, byte[] xml)
	 */
	public @WebResult ResponseRepositoryUpdate update(@WebParam RequestRepositoryUpdate request, @WebParam byte[] xml, @Context WebServiceContext wsContext) {
		return super.update(request, xml);
	}

	@WSDLDocumentation(value=delete_TITLE+" : "+delete_DESC)
	/**
	 * @see it.jenia.metaqrcode.server.services.RepositoryService#delete(RequestRepositoryDelete request)
	 */
	public @WebResult ResponseRepositoryDelete delete(@WebParam RequestRepositoryDelete request, @Context WebServiceContext wsContext) {
		return super.delete(request);
	}

	@WSDLDocumentation(value=search_TITLE+" : "+search_DESC)
	/**
	 * @see it.jenia.metaqrcode.server.services.RepositoryService#search(RequestRepositorySearch request)
	 */
	public @WebResult ResponseRepositorySearch search(@WebParam RequestRepositorySearch request, @Context WebServiceContext wsContext) {
		return super.search(request);
	}

	@WSDLDocumentation(value=detail_TITLE+" : "+detail_DESC)
	/**
	 * @see it.jenia.metaqrcode.server.services.RepositoryService#detail(RequestRepositoryDetail request)
	 */
	public @WebResult ResponseRepositoryDetail detail(@WebParam RequestRepositoryDetail request, @Context WebServiceContext wsContext) {
		return super.detail(request);
	}

	@WSDLDocumentation(value=download_TITLE+" : "+download_DESC)
	/**
	 * @see it.jenia.metaqrcode.server.services.RepositoryService#download(RequestRepositoryDownload request)
	 */
	public @WebResult byte[] download(@WebParam RequestRepositoryDownload request, @Context WebServiceContext wsContext) throws MetaqrcodeException {
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
	 * @see it.jenia.metaqrcode.server.services.RepositoryService#downloadAsJson(RequestRepositoryDownload request)
	 */
	public @WebResult byte[] downloadAsJson(@WebParam RequestRepositoryDownload request, @Context WebServiceContext wsContext) throws MetaqrcodeException {
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

	@WSDLDocumentation(value=qrCode_TITLE+" : "+qrCode_DESC)
	/**
	 * @see it.jenia.metaqrcode.server.services.RepositoryService#qrCode(RequestRepositoryQRCode request)
	 */
	public @WebResult byte[] qrCode(@WebParam RequestRepositoryQRCode request, @Context WebServiceContext wsContext) throws MetaqrcodeException {
		try {
			return super.qrCode(request);
		} catch (MetaqrcodeException e) {
			throw e;
		} catch (Throwable e) {
			throw new MetaqrcodeException(-1,e);
		} finally {
			// nothing to do...
		}
	}

}
