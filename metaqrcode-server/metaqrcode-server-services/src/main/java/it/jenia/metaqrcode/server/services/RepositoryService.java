package it.jenia.metaqrcode.server.services;

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

/**
 * repository entry (XML) services.
 * This service manage repository entry (XML) 
 * 
 * @author andreatessaro
 *
 */
public interface RepositoryService {

	/**
	 * upload a new repository entry (XML) into metaqrcode repository
	 * 
	 * @param request the request
	 * @param xml catalog entry (XML) to be uploaded
	 * @return the response
	 */
	public ResponseRepositoryUpload upload(RequestRepositoryUpload request, byte[] xml) throws MetaqrcodeException;

	/**
	 * upload a new version of a repository entry (XML) into metaqrcode repository
	 * 
	 * @param request the request
	 * @param xml catalog entry (XML) to be uploaded
	 * @return the response
	 */
	public ResponseRepositoryUpdate update(RequestRepositoryUpdate request, byte[] xml) throws MetaqrcodeException;

	/**
	 * delete a previously uploaded repository entry
	 * 
	 * @param request the request
	 * @return the response
	 */
	public ResponseRepositoryDelete delete(RequestRepositoryDelete request) throws MetaqrcodeException;

	/**
	 * search for a previously uploaded repository entry (XML)
	 * 
	 * @param request the request
	 * @return the response
	 */
	public ResponseRepositorySearch search(RequestRepositorySearch request) throws MetaqrcodeException;

	/**
	 * get detail of a previously uploaded repository entry (XML)
	 * 
	 * @param request the request
	 * @return the response
	 */
	public ResponseRepositoryDetail detail(RequestRepositoryDetail request) throws MetaqrcodeException;

	/**
	 * download the content of a repository entry (XML)
	 * 
	 * @param request the request
	 * @return the repository entry (XML)
	 */
	public byte[] download(RequestRepositoryDownload request) throws MetaqrcodeException;

	/**
	 * download the content of a repository entry (XML) automatically translated in JSON
	 * 
	 * @param request the request
	 * @return the repository entry (XML) translated in JSON
	 */
	public byte[] downloadAsJson(RequestRepositoryDownload request) throws MetaqrcodeException;

	/**
	 * download the image of the qrcode for the given repository entry (XML)
	 * 
	 * @param request the request
	 * @return the qrcode image
	 */
	public byte[] qrCode(RequestRepositoryQRCode request) throws MetaqrcodeException;

}
