package it.jenia.metaqrcode.server.services;

import it.jenia.metaqrcode.dto.link.RequestLinkCreate;
import it.jenia.metaqrcode.dto.link.RequestLinkDelete;
import it.jenia.metaqrcode.dto.link.RequestLinkDetail;
import it.jenia.metaqrcode.dto.link.RequestLinkDownload;
import it.jenia.metaqrcode.dto.link.ResponseLinkCreate;
import it.jenia.metaqrcode.dto.link.ResponseLinkDelete;
import it.jenia.metaqrcode.dto.link.ResponseLinkDetail;
import it.jenia.metaqrcode.server.commons.exception.MetaqrcodeException;

/**
 * link services.
 * This service manage link entries
 * 
 * @author andreatessaro
 *
 */
public interface LinkService {

	/**
	 * create the association between a repository entry (XML) and an "other" barcode or qrcode
	 * 
	 * @param request @see it.jenia.metaqrcode.dto.link.RequestLinkCreate
	 * @return @see it.jenia.metaqrcode.dto.link.ResponseLinkCreate
	 */
	public ResponseLinkCreate create(RequestLinkCreate request) throws MetaqrcodeException;

	/**
	 * delete a repository link entry
	 * 
	 * @param request @see it.jenia.metaqrcode.dto.link.RequestLinkDelete
	 * @return @see it.jenia.metaqrcode.dto.link.ResponseLinkDelete
	 */
	public ResponseLinkDelete delete(RequestLinkDelete request) throws MetaqrcodeException;
	
	/**
	 * get detail of a repository link entry
	 * 
	 * @param request @see it.jenia.metaqrcode.dto.link.RequestLinkDetail
	 * @return @see it.jenia.metaqrcode.dto.link.ResponseLinkDetail
	 */
	public ResponseLinkDetail detail(RequestLinkDetail request) throws MetaqrcodeException;
	
	/**
	 * download a repository link entry
	 * 
	 * @param request @see it.jenia.metaqrcode.dto.link.RequestLinkDownload
	 * @return the repository entry (XML)
	 */
	public byte[] download(RequestLinkDownload request) throws MetaqrcodeException;

	/**
	 * download a repository link entry
	 * 
	 * @param request @see it.jenia.metaqrcode.dto.link.RequestLinkDownload
	 * @return the repository entry (XML as json)
	 */
	public byte[] downloadAsJson(RequestLinkDownload request) throws MetaqrcodeException;

}
