package it.jenia.metaqrcode.server.core.api;

import it.jenia.metaqrcode.dto.link.RequestLinkCreate;
import it.jenia.metaqrcode.dto.link.RequestLinkDelete;
import it.jenia.metaqrcode.dto.link.RequestLinkDetail;
import it.jenia.metaqrcode.dto.link.RequestLinkDownload;
import it.jenia.metaqrcode.dto.link.ResponseLinkCreate;
import it.jenia.metaqrcode.dto.link.ResponseLinkDelete;
import it.jenia.metaqrcode.dto.link.ResponseLinkDetail;
import it.jenia.metaqrcode.server.commons.exception.MetaqrcodeException;

/**
 * link api service
 * 
 * @author andreatessaro
 *
 */
public interface LinkApiService {
	
	/**
	 * core api service to create a link between an existing barcode/qrcode and an existing metaqrcode
	 * 
	 * @param request the request
	 * @return the response
	 * @throws MetaqrcodeException
	 */
	public ResponseLinkCreate create(RequestLinkCreate request) throws MetaqrcodeException;

	/**
	 * core api service to delete a link
	 * 
	 * @param request the request
	 * @return the response
	 * @throws MetaqrcodeException
	 */
	public ResponseLinkDelete delete(RequestLinkDelete request) throws MetaqrcodeException;
	
	/**
	 * core api service to get detail of a link
	 * 
	 * @param request the request
	 * @return the response
	 * @throws MetaqrcodeException
	 */
	public ResponseLinkDetail detail(RequestLinkDetail request) throws MetaqrcodeException;
	
	/**
	 * core api service to download the repository entry bound to this link
	 * 
	 * @param request the request
	 * @return the XML
	 * @throws MetaqrcodeException
	 */
	public byte[] download(RequestLinkDownload request) throws MetaqrcodeException;

	/**
	 * core api service to download the repository entry (in json format) bound to this link
	 * 
	 * @param request the request
	 * @return the json
	 * @throws MetaqrcodeException
	 */
	public byte[] downloadAsJson(RequestLinkDownload request) throws MetaqrcodeException;

}
