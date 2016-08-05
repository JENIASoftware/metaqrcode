package it.jenia.metaqrcode.server.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

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
import it.jenia.metaqrcode.server.core.api.RepositoryApiService;
import it.jenia.metaqrcode.server.services.RepositoryService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
/**
 * generic implementation of RepositoryService
 * @see it.jenia.metaqrcode.server.services.RepositoryService
 * 
 * @author andreatessaro
 *
 */
public class RepositoryServiceImpl extends AbstractMetaqrcodeService implements RepositoryService {
	
	public static final String service_TITLE = "Repository service";
	public static final String service_DESC = "service to manage repository entry (XML) on METAQRCODE server";

	@Autowired
	private RepositoryApiService repositoryApiService;

	protected static final String upload_TITLE = "upload a new repository entry (XML)";
	protected static final String upload_DESC = "request for upload of a new repository entry (XML)";
	@Override
	/**
	 * @see it.jenia.metaqrcode.server.services.RepositoryService#upload(RequestRepositoryUpload request, byte[] xml)
	 */
	public ResponseRepositoryUpload upload(RequestRepositoryUpload request, byte[] xml) {
		try {
			if (log.isDebugEnabled()) log.debug("repository upload called ");
			ResponseRepositoryUpload ret = repositoryApiService.upload(request, xml);
			if (log.isDebugEnabled()) log.debug("repository upload : success");
			return ret;
		} catch (MetaqrcodeException e) {
			if (log.isInfoEnabled()) {
				log.info("Generic error on catalog upload service",e);
			}
			ResponseRepositoryUpload ret = new ResponseRepositoryUpload();
			ret.setReturnCode(e.getReturnCode());
			ret.setReason(e.getMessage());
			return ret;
		} catch (Throwable e) {
			if (log.isInfoEnabled()) {
				log.info("Generic error on catalog upload service",e);
			}
			ResponseRepositoryUpload ret = new ResponseRepositoryUpload();
			ret.setReturnCode(-1);
			ret.setReason(e.getMessage());
			return ret;
		} finally {
			// nothing to do...
		}
	}

	protected static final String update_TITLE = "update a repository entry (XML)";
	protected static final String update_DESC = "request for update of a repository entry (XML)";
	@Override
	/**
	 * @see it.jenia.metaqrcode.server.services.RepositoryService#update(RequestRepositoryUpdate request, byte[] xml)
	 */
	public ResponseRepositoryUpdate update(RequestRepositoryUpdate request, byte[] xml) {
		try {
			if (log.isDebugEnabled()) log.debug("repository update called for " + request.getId());
			ResponseRepositoryUpdate ret = repositoryApiService.update(request, xml);
			if (log.isDebugEnabled()) log.debug("repository update : success");
			return ret;
		} catch (MetaqrcodeException e) {
			if (log.isInfoEnabled()) {
				log.info("Generic error on repository update service",e);
			}
			ResponseRepositoryUpdate ret = new ResponseRepositoryUpdate();
			ret.setReturnCode(e.getReturnCode());
			ret.setReason(e.getMessage());
			return ret;
		} catch (Throwable e) {
			if (log.isInfoEnabled()) {
				log.info("Generic error on repository update service",e);
			}
			ResponseRepositoryUpdate ret = new ResponseRepositoryUpdate();
			ret.setReturnCode(-1);
			ret.setReason(e.getMessage());
			return ret;
		} finally {
			// nothing to do...
		}
	}

	protected static final String delete_TITLE = "delete a repository entry (XML)";
	protected static final String delete_DESC = "request for delete of a repository entry (XML)";
	@Override
	/**
	 * @see it.jenia.metaqrcode.server.services.RepositoryService#delete(RequestRepositoryDelete request)
	 */
	public ResponseRepositoryDelete delete(RequestRepositoryDelete request) {
		try {
			if (log.isDebugEnabled()) log.debug("repository delete called for " + request.getId());
			ResponseRepositoryDelete ret = repositoryApiService.delete(request);
			if (log.isDebugEnabled()) log.debug("repository delete : success");
			return ret;
		} catch (MetaqrcodeException e) {
			if (log.isInfoEnabled()) {
				log.info("Generic error on repository delete service",e);
			}
			ResponseRepositoryDelete ret = new ResponseRepositoryDelete();
			ret.setReturnCode(e.getReturnCode());
			ret.setReason(e.getMessage());
			return ret;
		} catch (Throwable e) {
			if (log.isInfoEnabled()) {
				log.info("Generic error on repository delete service",e);
			}
			ResponseRepositoryDelete ret = new ResponseRepositoryDelete();
			ret.setReturnCode(-1);
			ret.setReason(e.getMessage());
			return ret;
		} finally {
			// nothing to do...
		}
	}

	protected static final String search_TITLE = "search for a repository entry (XML)";
	protected static final String search_DESC = "request for search of a repository entry (XML)";
	@Override
	/**
	 * @see it.jenia.metaqrcode.server.services.RepositoryService#search(RequestRepositorySearch request)
	 */
	public ResponseRepositorySearch search(RequestRepositorySearch request) {
		try {
			if (log.isDebugEnabled()) log.debug("repository search called for " + request.getId());
			ResponseRepositorySearch ret = repositoryApiService.search(request);
			if (log.isDebugEnabled()) log.debug("repository search : success");
			return ret;
		} catch (MetaqrcodeException e) {
			if (log.isInfoEnabled()) {
				log.info("Generic error on repository search service",e);
			}
			ResponseRepositorySearch ret = new ResponseRepositorySearch();
			ret.setReturnCode(e.getReturnCode());
			ret.setReason(e.getMessage());
			return ret;
		} catch (Throwable e) {
			if (log.isInfoEnabled()) {
				log.info("Generic error on repository search service",e);
			}
			ResponseRepositorySearch ret = new ResponseRepositorySearch();
			ret.setReturnCode(-1);
			ret.setReason(e.getMessage());
			return ret;
		} finally {
			// nothing to do...
		}
	}

	protected static final String detail_TITLE = "get detail of a repository entry (XML)";
	protected static final String detail_DESC = "request for detail of a repository entry (XML)";
	@Override
	/**
	 * @see it.jenia.metaqrcode.server.services.RepositoryService#detail(RequestRepositoryDetail request)
	 */
	public ResponseRepositoryDetail detail(RequestRepositoryDetail request) {
		try {
			if (log.isDebugEnabled()) log.debug("repository detail called for " + request.getId());
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
				log.info("Generic error on repository detail service",e);
			}
			ResponseRepositoryDetail ret = new ResponseRepositoryDetail();
			ret.setReturnCode(-1);
			ret.setReason(e.getMessage());
			return ret;
		} finally {
			// nothing to do...
		}
	}

	protected static final String download_TITLE = "download a repository entry (XML)";
	protected static final String download_DESC = "request for download of a repository entry (XML)";
	@Override
	/**
	 * @see it.jenia.metaqrcode.server.services.RepositoryService#download(RequestRepositoryDownload request)
	 */
	public byte[] download(RequestRepositoryDownload request) throws MetaqrcodeException {
		if (log.isDebugEnabled()) log.debug("repository download called for " + request.getId());
		byte[] ret = repositoryApiService.download(request);
		if (log.isDebugEnabled()) log.debug("repository download : success");
		return ret;
	}

	protected static final String downloadAsJson_TITLE = "download a repository entry (XML) as json";
	protected static final String downloadAsJson_DESC = "request for download of a repository entry (XML) as json";
	@Override
	/**
	 * @see it.jenia.metaqrcode.server.services.RepositoryService#downloadAsJson(RequestRepositoryDownload request)
	 */
	public byte[] downloadAsJson(RequestRepositoryDownload request) throws MetaqrcodeException {
		if (log.isDebugEnabled()) log.debug("repository downloadAsJson called for " + request.getId());
		byte[] ret = repositoryApiService.downloadAsJson(request);
		if (log.isDebugEnabled()) log.debug("repository downloadAsJson : success");
		return ret;
	}

	protected static final String qrCode_TITLE = "download the qrcode of a repository entry (XML)";
	protected static final String qrCode_DESC = "request for download the qrcode of a repository entry (XML)";
	@Override
	/**
	 * @see it.jenia.metaqrcode.server.services.RepositoryService#qrCode(RequestRepositoryQRCode request)
	 */
	public byte[] qrCode(RequestRepositoryQRCode request) throws MetaqrcodeException {
		if (log.isDebugEnabled()) log.debug("repository qrcode called for " + request.getId());
		byte[] ret = repositoryApiService.qrCode(request);
		if (log.isDebugEnabled()) log.debug("repository qrcode : success");
		return ret;
	}

}
