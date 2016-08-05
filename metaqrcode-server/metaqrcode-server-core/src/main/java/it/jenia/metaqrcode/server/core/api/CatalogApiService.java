package it.jenia.metaqrcode.server.core.api;

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

/**
 * catalog API service
 * 
 * @author andreatessaro
 *
 */
public interface CatalogApiService {
	
	/**
	 * core service to upload a catalog entry (XSD)
	 * 
	 * @param request the request
	 * @param xsd the XSD
	 * @return the response
	 * @throws MetaqrcodeException
	 */
	public ResponseCatalogUpload upload(RequestCatalogUpload request, byte[] xsd) throws MetaqrcodeException;

	/**
	 * core service to search for a catalog entry (XSD)
	 * 
	 * @param request the request
	 * @return the response
	 * @throws MetaqrcodeException
	 */
	public ResponseCatalogSearch search(RequestCatalogSearch request) throws MetaqrcodeException;

	/**
	 * core service to get detail for a catalog entry (XSD)
	 * 
	 * @param request the request
	 * @return the response
	 * @throws MetaqrcodeException
	 */
	public ResponseCatalogDetail detail(RequestCatalogDetail request) throws MetaqrcodeException;

	/**
	 * core service to delete a cagalog entry (XSD)
	 * 
	 * @param request the request
	 * @return the response
	 * @throws MetaqrcodeException
	 */
	public ResponseCatalogDelete delete(RequestCatalogDelete request)  throws MetaqrcodeException;

	/**
	 * core service to download a catalog entry (XSD)
	 * 
	 * @param request the request
	 * @return the catalog entry (XSD)
	 * @throws MetaqrcodeException
	 */
	public byte[] download(RequestCatalogDownload request) throws MetaqrcodeException;

}
