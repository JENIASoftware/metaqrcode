package it.jenia.metaqrcode.server.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

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
import it.jenia.metaqrcode.server.core.api.CatalogApiService;
import it.jenia.metaqrcode.server.services.CatalogService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
/**
 * generic implementation of CatalogService
 * @see it.jenia.metaqrcode.server.services.CatalogService
 * 
 * @author andreatessaro
 *
 */
public class CatalogServiceImpl extends AbstractMetaqrcodeService implements CatalogService {
	
	public static final String service_TITLE = "Catalog entry (XSD) service";
	public static final String service_DESC = "service to manage catalog entry (XSD) on METAQRCODE server";

	@Autowired
	private CatalogApiService catalogApiService;

	protected static final String upload_TITLE = "upload a new catalog entry (XSD)";
	protected static final String upload_DESC = "Using this service you can upload a new catalog entry (XSD) on METAQRCODE server";
	@Override
	/**
	 * @see it.jenia.metaqrcode.server.services.CatalogService#upload(RequestCatalogUpload request, byte[] xsd)
	 */
	public ResponseCatalogUpload upload(RequestCatalogUpload request, byte[] xsd) {
		try {
			if (log.isDebugEnabled()) log.debug("catalog upload called for " + request.getName());
			ResponseCatalogUpload ret = catalogApiService.upload(request, xsd);
			if (log.isDebugEnabled()) log.debug("catalog upload : success");
			return ret;
		} catch (MetaqrcodeException e) {
			if (log.isInfoEnabled()) {
				log.info("Generic error on catalog upload service",e);
			}
			ResponseCatalogUpload ret = new ResponseCatalogUpload();
			ret.setReturnCode(e.getReturnCode());
			ret.setReason(e.getMessage());
			return ret;
		} catch (Throwable e) {
			if (log.isInfoEnabled()) {
				log.info("Generic error on catalog upload service",e);
			}
			ResponseCatalogUpload ret = new ResponseCatalogUpload();
			ret.setReturnCode(-1);
			ret.setReason(e.getMessage());
			return ret;
		} finally {
			// nothing to do...
		}
	}

	protected static final String search_TITLE = "Search for already uploaded catalog entry (XSD)";
	protected static final String search_DESC = "Using this service you can search for a catalog entry (XSD) on METAQRCODE server";
	@Override
	/**
	 * @see it.jenia.metaqrcode.server.services.CatalogService#search(RequestCatalogSearch request)
	 */
	public ResponseCatalogSearch search(RequestCatalogSearch request) {
		try {
			if (log.isDebugEnabled()) log.debug("catalog search called for " + request.getNameLike() + " : " + request.getDescriptionLike() + " : " + request.isOnlyMine());
			ResponseCatalogSearch ret = catalogApiService.search(request);
			if (log.isDebugEnabled()) log.debug("catalog search : success");
			return ret;
		} catch (MetaqrcodeException e) {
			if (log.isInfoEnabled()) {
				log.info("Generic error on catalog search service",e);
			}
			ResponseCatalogSearch ret = new ResponseCatalogSearch();
			ret.setReturnCode(e.getReturnCode());
			ret.setReason(e.getMessage());
			return ret;
		} catch (Throwable e) {
			if (log.isInfoEnabled()) {
				log.info("Generic error on catalog search service",e);
			}
			ResponseCatalogSearch ret = new ResponseCatalogSearch();
			ret.setReturnCode(-1);
			ret.setReason(e.getMessage());
			return ret;
		} finally {
			// nothing to do...
		}
	}

	protected static final String detail_TITLE = "get detail for already uploaded catalog entry (XSD)";
	protected static final String detail_DESC = "Using this service you can get detail for a catalog entry (XSD) on METAQRCODE server";
	@Override
	/**
	 * @see it.jenia.metaqrcode.server.services.CatalogService#detail(RequestCatalogDetail request)
	 */
	public ResponseCatalogDetail detail(RequestCatalogDetail request) {
		try {
			if (log.isDebugEnabled()) log.debug("catalog detail called for " + request.getId());
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

	protected static final String delete_TITLE = "Delete an uploaded catalog entry (XSD)";
	protected static final String delete_DESC = "Using this service you can delete a catalog entry (XSD) on METAQRCODE server";
	@Override
	/**
	 * @see it.jenia.metaqrcode.server.services.CatalogService#delete(RequestCatalogDelete request)
	 */
	public ResponseCatalogDelete delete(RequestCatalogDelete request) {
		try {
			if (log.isDebugEnabled()) log.debug("catalog delete called for " + request.getId());
			ResponseCatalogDelete ret = catalogApiService.delete(request);
			if (log.isDebugEnabled()) log.debug("catalog delete : success");
			return ret;
		} catch (MetaqrcodeException e) {
			if (log.isInfoEnabled()) {
				log.info("Generic error on catalog delete service",e);
			}
			ResponseCatalogDelete ret = new ResponseCatalogDelete();
			ret.setReturnCode(e.getReturnCode());
			ret.setReason(e.getMessage());
			return ret;
		} catch (Throwable e) {
			if (log.isInfoEnabled()) {
				log.info("Generic error on catalog delete service",e);
			}
			ResponseCatalogDelete ret = new ResponseCatalogDelete();
			ret.setReturnCode(-1);
			ret.setReason(e.getMessage());
			return ret;
		} finally {
			// nothing to do...
		}
	}

	protected static final String download_TITLE = "Download an uploaded catalog entry (XSD)";
	protected static final String download_DESC = "Using this service you can download a catalog entry (XSD) on METAQRCODE server";
	@Override
	/**
	 * @see it.jenia.metaqrcode.server.services.CatalogService#download(RequestCatalogDownload request)
	 */
	public byte[] download(RequestCatalogDownload request) throws MetaqrcodeException {
		if (log.isDebugEnabled()) log.debug("catalog download called for " + request.getId());
		byte[] ret = catalogApiService.download(request);
		if (log.isDebugEnabled()) log.debug("catalog download : success");
		return ret;
	}

}
