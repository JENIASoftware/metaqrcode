package it.jenia.metaqrcode.server.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import it.jenia.metaqrcode.dto.link.RequestLinkCreate;
import it.jenia.metaqrcode.dto.link.RequestLinkDelete;
import it.jenia.metaqrcode.dto.link.RequestLinkDetail;
import it.jenia.metaqrcode.dto.link.RequestLinkDownload;
import it.jenia.metaqrcode.dto.link.ResponseLinkCreate;
import it.jenia.metaqrcode.dto.link.ResponseLinkDelete;
import it.jenia.metaqrcode.dto.link.ResponseLinkDetail;
import it.jenia.metaqrcode.server.commons.exception.MetaqrcodeException;
import it.jenia.metaqrcode.server.core.api.LinkApiService;
import it.jenia.metaqrcode.server.services.LinkService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
/**
 * generic implementation of LinkService
 * @see it.jenia.metaqrcode.server.services.LinkService
 * 
 * @author andreatessaro
 *
 */
public class LinkServiceImpl extends AbstractMetaqrcodeService implements LinkService {
	
	public static final String service_TITLE = "Link service";
	public static final String service_DESC = "service to manage links on METAQRCODE server";

	@Autowired
	private LinkApiService linkApiService;

	protected static final String create_TITLE = "Create a link";
	protected static final String create_DESC = "Create a link between an existing barcode or qrcode and an existing METAQRCODE";
	@Override
	/**
	 * @see it.jenia.metaqrcode.server.services.LinkService#create(RequestLinkCreate request)
	 */
	public ResponseLinkCreate create(RequestLinkCreate request) {
		try {
			if (log.isDebugEnabled()) log.debug("link create called ");
			ResponseLinkCreate ret = linkApiService.create(request);
			if (log.isDebugEnabled()) log.debug("link create : success");
			return ret;
		} catch (MetaqrcodeException e) {
			if (log.isInfoEnabled()) {
				log.info("Generic error on link create service",e);
			}
			ResponseLinkCreate ret = new ResponseLinkCreate();
			ret.setReturnCode(e.getReturnCode());
			ret.setReason(e.getMessage());
			return ret;
		} catch (Throwable e) {
			if (log.isInfoEnabled()) {
				log.info("Generic error on link create service",e);
			}
			ResponseLinkCreate ret = new ResponseLinkCreate();
			ret.setReturnCode(-1);
			ret.setReason(e.getMessage());
			return ret;
		} finally {
			// nothing to do...
		}
	}

	protected static final String delete_TITLE = "Delete a link";
	protected static final String delete_DESC = "Delete association between an existing barcode or qrcode and an existing METAQRCODE";
	@Override
	/**
	 * @see it.jenia.metaqrcode.server.services.LinkService#delete(RequestLinkDelete request)
	 */
	public ResponseLinkDelete delete(RequestLinkDelete request) {
		try {
			if (log.isDebugEnabled()) log.debug("link delete called for " + request.getOtherCode());
			ResponseLinkDelete ret = linkApiService.delete(request);
			if (log.isDebugEnabled()) log.debug("link delete : success");
			return ret;
		} catch (MetaqrcodeException e) {
			if (log.isInfoEnabled()) {
				log.info("Generic error on link delete service",e);
			}
			ResponseLinkDelete ret = new ResponseLinkDelete();
			ret.setReturnCode(e.getReturnCode());
			ret.setReason(e.getMessage());
			return ret;
		} catch (Throwable e) {
			if (log.isInfoEnabled()) {
				log.info("Generic error on link delete service",e);
			}
			ResponseLinkDelete ret = new ResponseLinkDelete();
			ret.setReturnCode(-1);
			ret.setReason(e.getMessage());
			return ret;
		} finally {
			// nothing to do...
		}
	}

	protected static final String detail_TITLE = "get detail of a link";
	protected static final String detail_DESC = "get detail of an association between an existing barcode or qrcode and an existing METAQRCODE";
	@Override
	/**
	 * @see it.jenia.metaqrcode.server.services.LinkService#detail(RequestLinkDetail request)
	 */
	public ResponseLinkDetail detail(RequestLinkDetail request) {
		try {
			if (log.isDebugEnabled()) log.debug("link detail called for " + request.getOtherCode());
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
				log.info("Generic error on link detail service",e);
			}
			ResponseLinkDetail ret = new ResponseLinkDetail();
			ret.setReturnCode(-1);
			ret.setReason(e.getMessage());
			return ret;
		} finally {
			// nothing to do...
		}
	}

	protected static final String download_TITLE = "Download a the repository entry (XML) associated to this link";
	protected static final String download_DESC = "Download the repository entry (XML) associated with an existing METAQRCODE";
	@Override
	/**
	 * @see it.jenia.metaqrcode.server.services.LinkService#download(RequestLinkDownload request)
	 */
	public byte[] download(RequestLinkDownload request) throws MetaqrcodeException {
		if (log.isDebugEnabled()) log.debug("link download called for " + request.getOtherCode());
		byte[] ret = linkApiService.download(request);
		if (log.isDebugEnabled()) log.debug("link download : success");
		return ret;
	}

	protected static final String downloadAsJson_TITLE = "Download a the repository entry (XML as json) associated to this link";
	protected static final String downloadAsJson_DESC = "Download the repository entry (XML as json) associated with an existing METAQRCODE";
	@Override
	/**
	 * @see it.jenia.metaqrcode.server.services.LinkService#downloadAsJson(RequestLinkDownload request)
	 */
	public byte[] downloadAsJson(RequestLinkDownload request) throws MetaqrcodeException {
		if (log.isDebugEnabled()) log.debug("link downloadAsJson called for " + request.getOtherCode());
		byte[] ret = linkApiService.downloadAsJson(request);
		if (log.isDebugEnabled()) log.debug("link downloadAsJson : success");
		return ret;
	}

}
