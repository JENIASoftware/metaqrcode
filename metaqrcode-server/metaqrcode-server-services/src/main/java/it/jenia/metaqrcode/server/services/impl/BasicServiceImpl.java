package it.jenia.metaqrcode.server.services.impl;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;

import it.jenia.metaqrcode.dto.catalog.RequestCatalogDetail;
import it.jenia.metaqrcode.dto.catalog.RequestCatalogDownload;
import it.jenia.metaqrcode.dto.catalog.ResponseCatalogDetail;
import it.jenia.metaqrcode.dto.link.RequestLinkDetail;
import it.jenia.metaqrcode.dto.link.RequestLinkDownload;
import it.jenia.metaqrcode.dto.link.ResponseLinkDetail;
import it.jenia.metaqrcode.dto.repository.RequestRepositoryDetail;
import it.jenia.metaqrcode.dto.repository.RequestRepositoryDownload;
import it.jenia.metaqrcode.dto.repository.RequestRepositoryQRCode;
import it.jenia.metaqrcode.dto.repository.ResponseRepositoryDetail;
import it.jenia.metaqrcode.server.commons.exception.MetaqrcodeException;
import it.jenia.metaqrcode.server.core.api.CatalogApiService;
import it.jenia.metaqrcode.server.core.api.LinkApiService;
import it.jenia.metaqrcode.server.core.api.RepositoryApiService;
import it.jenia.metaqrcode.server.core.exception.CatalogEntryNotFoundException;
import it.jenia.metaqrcode.server.core.exception.RepositoryEntryNotFoundException;
import it.jenia.metaqrcode.server.core.exception.RepositoryLinkNotFoundException;
import it.jenia.metaqrcode.server.services.BasicService;
import it.jenia.metaqrcode.server.services.exception.GenericException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
/**
 * generic implementation of BasicService
 * @see it.jenia.metaqrcode.server.services.BasicService
 * 
 * @author andreatessaro
 *
 */
public class BasicServiceImpl extends AbstractMetaqrcodeService implements BasicService {
	
	public static final String service_TITLE = "basic service";
	public static final String service_DESC = "basic service for getting resources (in http get)";

	@Autowired
	private CatalogApiService catalogApiService;

	@Autowired
	private RepositoryApiService repositoryApiService;

	@Autowired
	private LinkApiService linkApiService;

	protected static final String getCatalog_TITLE = "get catalog entry content.";
	protected static final String getCatalog_DESC = "Will return a valid already uploaded XSD";
	@Override
	/**
	 * @see it.jenia.metaqrcode.server.services.BasicService#getCatalog(BigInteger id)
	 */
	public byte[] getCatalog(BigInteger id) throws CatalogEntryNotFoundException, GenericException, MetaqrcodeException {
		if (log.isDebugEnabled()) log.debug("catalog get called for " + id);
		RequestCatalogDownload request = new RequestCatalogDownload();
		request.setId(id);
		byte[] ret = catalogApiService.download(request);
		if (log.isDebugEnabled()) log.debug("catalog get : success");
		return ret;
	}

	protected static final String getCatalogDetail_TITLE = "get catalog entry detail.";
	protected static final String getCatalogDetail_DESC = "Will return detail of a valid already uploaded XSD";
	@Override
	/**
	 * @see it.jenia.metaqrcode.server.services.BasicService#getCatalogDetail(BigInteger id)
	 */
	public ResponseCatalogDetail getCatalogDetail(BigInteger id) {
		try {
			if (log.isDebugEnabled()) log.debug("catalog detail called for " + id);
			RequestCatalogDetail request = new RequestCatalogDetail();
			request.setId(id);
			ResponseCatalogDetail ret = catalogApiService.detail(request);
			if (log.isDebugEnabled()) log.debug("catalog detail : success");
			return ret;
		} catch (MetaqrcodeException e) {
			if (log.isInfoEnabled()) {
				log.info("Generic error on catalog detail service",e);
			}
			ResponseCatalogDetail ret = new ResponseCatalogDetail();
			ret.setReturnCode(e.getReturnCode());
			ret.setReason(e.getMessage());
			return ret;
		} catch (Throwable e) {
			if (log.isInfoEnabled()) {
				log.info("Generic error on catalog Detail service",e);
			}
			ResponseCatalogDetail ret = new ResponseCatalogDetail();
			ret.setReturnCode(-1);
			ret.setReason(e.getMessage());
			return ret;
		} finally {
			// nothing to do...
		}
	}

	protected static final String getRepository_TITLE = "get repository entry (XML) content.";
	protected static final String getRepository_DESC = "Will return a valid already uploaded XML repository entry";
	@Override
	/**
	 * @see it.jenia.metaqrcode.server.services.BasicService#getRepository(BigInteger id)
	 */
	public byte[] getRepository(BigInteger id) throws RepositoryEntryNotFoundException, GenericException, MetaqrcodeException {
		if (log.isDebugEnabled()) log.debug("repository get called for " + id);
		RequestRepositoryDownload request = new RequestRepositoryDownload();
		request.setId(id);
		byte[] ret = repositoryApiService.download(request);
		if (log.isDebugEnabled()) log.debug("repository get : success");
		return ret;
	}

	protected static final String getRepositoryAsJson_TITLE = "get repository entry (XML) content as json.";
	protected static final String getRepositoryAsJson_DESC = "Will return a valid already uploaded XML repository entry in json format";
	@Override
	/**
	 * @see it.jenia.metaqrcode.server.services.BasicService#getRepositoryAsJson(BigInteger id)
	 */
	public byte[] getRepositoryAsJson(BigInteger id) throws RepositoryEntryNotFoundException, GenericException, MetaqrcodeException {
		if (log.isDebugEnabled()) log.debug("repository get as json called for " + id);
		RequestRepositoryDownload request = new RequestRepositoryDownload();
		request.setId(id);
		byte[] ret = repositoryApiService.downloadAsJson(request);
		if (log.isDebugEnabled()) log.debug("repository get as json : success");
		return ret;
	}

	protected static final String getRepositoryDetail_TITLE = "get repository entry (XML) detail.";
	protected static final String getRepositoryDetail_DESC = "Will return a valid already uploaded XML repository detail";
	@Override
	/**
	 * @see it.jenia.metaqrcode.server.services.BasicService#getRepositoryDetail(BigInteger id)
	 */
	public ResponseRepositoryDetail getRepositoryDetail(BigInteger id) {
		try {
			if (log.isDebugEnabled()) log.debug("repository detail called for " + id);
			RequestRepositoryDetail request = new RequestRepositoryDetail();
			request.setId(id);
			ResponseRepositoryDetail ret = repositoryApiService.detail(request);
			if (log.isDebugEnabled()) log.debug("repository detail : success");
			return ret;
		} catch (MetaqrcodeException e) {
			if (log.isInfoEnabled()) {
				log.info("Generic error on repository detail service",e);
			}
			ResponseRepositoryDetail ret = new ResponseRepositoryDetail();
			ret.setReturnCode(e.getReturnCode());
			ret.setReason(e.getMessage());
			return ret;
		} catch (Throwable e) {
			if (log.isInfoEnabled()) {
				log.info("Generic error on repository Detail service",e);
			}
			ResponseRepositoryDetail ret = new ResponseRepositoryDetail();
			ret.setReturnCode(-1);
			ret.setReason(e.getMessage());
			return ret;
		} finally {
			// nothing to do...
		}
	}

	protected static final String getQRCode_TITLE = "get QRCode image for a valid repository entry.";
	protected static final String getQRCode_DESC = "Will return the PNG image corresponding to the XML repository entry";
	@Override
	/**
	 * @see it.jenia.metaqrcode.server.services.BasicService#getQRCode(BigInteger id)
	 */
	public byte[] getQRCode(BigInteger id) throws RepositoryEntryNotFoundException, GenericException, MetaqrcodeException {
		if (log.isDebugEnabled()) log.debug("qrcode get called for " + id);
		RequestRepositoryQRCode request = new RequestRepositoryQRCode();
		request.setId(id);
		byte[] ret = repositoryApiService.qrCode(request);
		if (log.isDebugEnabled()) log.debug("qrcode get : success");
		return ret;
	}

	protected static final String getLink_TITLE = "get the repository entry of a given link code.";
	protected static final String getLink_DESC = "Will return a valid already uploaded XML repository entry";
	@Override
	/**
	 * @see it.jenia.metaqrcode.server.services.BasicService#getLink(String otherCode)
	 */
	public byte[] getLink(String otherCode) throws RepositoryLinkNotFoundException, GenericException, MetaqrcodeException {
		if (log.isDebugEnabled()) log.debug("link get called for " + otherCode);
		RequestLinkDownload request = new RequestLinkDownload();
		request.setOtherCode(otherCode);;
		byte[] ret = linkApiService.download(request);
		if (log.isDebugEnabled()) log.debug("link get : success");
		return ret;
	}

	protected static final String getLinkAsJson_TITLE = "get the repository entry of a given link code in json format.";
	protected static final String getLinkAsJson_DESC = "Will return a valid already uploaded XML repository entry in json format";
	@Override
	/**
	 * @see it.jenia.metaqrcode.server.services.BasicService#getLink(String otherCode)
	 */
	public byte[] getLinkAsJson(String otherCode) throws RepositoryLinkNotFoundException, GenericException, MetaqrcodeException {
		if (log.isDebugEnabled()) log.debug("linkAsJson get called for " + otherCode);
		RequestLinkDownload request = new RequestLinkDownload();
		request.setOtherCode(otherCode);;
		byte[] ret = linkApiService.downloadAsJson(request);
		if (log.isDebugEnabled()) log.debug("linkAsJson get : success");
		return ret;
	}

	protected static final String getLinkDetail_TITLE = "get detail of the repository entry of a given link code in json format.";
	protected static final String getLinkDetail_DESC = "Will return a valid already uploaded XML repository entry detail";
	@Override
	/**
	 * @see it.jenia.metaqrcode.server.services.BasicService#getLinkDetail(String otherCode)
	 */
	public ResponseLinkDetail getLinkDetail(String otherCode) {
		try {
			if (log.isDebugEnabled()) log.debug("link detail called for " + otherCode);
			RequestLinkDetail request = new RequestLinkDetail();
			request.setOtherCode(otherCode);
			ResponseLinkDetail ret = linkApiService.detail(request);
			if (log.isDebugEnabled()) log.debug("link detail : success");
			return ret;
		} catch (MetaqrcodeException e) {
			if (log.isInfoEnabled()) {
				log.info("Generic error on link detail service",e);
			}
			ResponseLinkDetail ret = new ResponseLinkDetail();
			ret.setReturnCode(e.getReturnCode());
			ret.setReason(e.getMessage());
			return ret;
		} catch (Throwable e) {
			if (log.isInfoEnabled()) {
				log.info("Generic error on link Detail service",e);
			}
			ResponseLinkDetail ret = new ResponseLinkDetail();
			ret.setReturnCode(-1);
			ret.setReason(e.getMessage());
			return ret;
		} finally {
			// nothing to do...
		}
	}

}
