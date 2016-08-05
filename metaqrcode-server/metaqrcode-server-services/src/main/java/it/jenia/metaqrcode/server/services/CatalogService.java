package it.jenia.metaqrcode.server.services;

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
 * catalog entry (XSD) services.
 * This service manage catalog entry (XSD) 
 * 
 * @author andreatessaro
 *
 */
public interface CatalogService {

	/**
	 * upload a catalog entry (XSD)
	 * @param request @see it.jenia.metaqrcode.dto.catalog.RequestCatalogUpload
	 * @param xsd the catalog entry (XSD) to be uloaded
	 * @return @see it.jenia.metaqrcode.dto.catalog.ResponseCatalogUpload
	 */
	public ResponseCatalogUpload upload(RequestCatalogUpload request, byte[] xsd) throws MetaqrcodeException;

	/**
	 * search for a catalog entry (XSD)
	 * @param request @see it.jenia.metaqrcode.dto.catalog.RequestCatalogSearch
	 * @return @see it.jenia.metaqrcode.dto.catalog.ResponseCatalogSearch
	 */
	public ResponseCatalogSearch search(RequestCatalogSearch request) throws MetaqrcodeException;

	/**
	 * get detail of a catalog entry (XSD)
	 * @param request @see it.jenia.metaqrcode.dto.catalog.RequestCatalogDetail
	 * @return @see it.jenia.metaqrcode.dto.catalog.ResponseCatalogDetail
	 */
	public ResponseCatalogDetail detail(RequestCatalogDetail request) throws MetaqrcodeException;

	/**
	 * delete a catalog entry (XSD)
	 * @param request @see it.jenia.metaqrcode.dto.catalog.RequestCatalogDelete
	 * @return @see it.jenia.metaqrcode.dto.catalog.ResponseCatalogDelete
	 */
	public ResponseCatalogDelete delete(RequestCatalogDelete request) throws MetaqrcodeException;

	/**
	 * download a catalog entry (XSD)
	 * @param request @see it.jenia.metaqrcode.dto.catalog.RequestCatalogDownload
	 * @return the catalog entry (XSD) content
	 */
	public byte[] download(RequestCatalogDownload request) throws MetaqrcodeException;

}
