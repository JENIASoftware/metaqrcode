package it.jenia.metaqrcode.server.core.api;

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
 * repository api service
 * 
 * @author andreatessaro
 *
 */
public interface RepositoryApiService {
	
	/**
	 * upload a new repository entry (XML)
	 * 
	 * @param request the request
	 * @param xml the repository entry (XML)
	 * @return the response
	 * @throws MetaqrcodeException
	 */
	public ResponseRepositoryUpload upload(RequestRepositoryUpload request, byte[] xml) throws MetaqrcodeException;

	/**
	 * update an existing repository entry (XML)
	 * 
	 * @param request the request
	 * @param xml new repository (XML) version
	 * @return the response
	 * @throws MetaqrcodeException
	 */
	public ResponseRepositoryUpdate update(RequestRepositoryUpdate request, byte[] xml)  throws MetaqrcodeException;

	/**
	 * delete a repository entry (XML)
	 * 
	 * @param request the request
	 * @return the response
	 * @throws MetaqrcodeException
	 */
	public ResponseRepositoryDelete delete(RequestRepositoryDelete request) throws MetaqrcodeException;
	
	/**
	 * search for a repository entry (XML)
	 * 
	 * @param request the request
	 * @return the response
	 * @throws MetaqrcodeException
	 */
	public ResponseRepositorySearch search(RequestRepositorySearch request) throws MetaqrcodeException;
	
	/**
	 * detail of a repository entry (XML)
	 * 
	 * @param request the request
	 * @return the response
	 * @throws MetaqrcodeException
	 */
	public ResponseRepositoryDetail detail(RequestRepositoryDetail request) throws MetaqrcodeException;
	
	/**
	 * download a repository entry (XML)
	 * 
	 * @param request the request
	 * @return the repository entry (XML)
	 * @throws MetaqrcodeException
	 */
	public byte[] download(RequestRepositoryDownload request) throws MetaqrcodeException;

	/**
	 * download a repository entry (XML) as json
	 * 
	 * @param request the request
	 * @return the repository entry (XML) as json
	 * @throws MetaqrcodeException
	 */
	public byte[] downloadAsJson(RequestRepositoryDownload request) throws MetaqrcodeException ;

	/**
	 * download the METAQRCODE about this repository entry (XML) 
	 * 
	 * @param request the request
	 * @return the METAQRCODE png
	 * @throws MetaqrcodeException
	 */
	public byte[] qrCode(RequestRepositoryQRCode request) throws MetaqrcodeException;

}
