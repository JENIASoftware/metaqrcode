package it.jenia.metaqrcode.server.services;

import java.math.BigInteger;

import it.jenia.metaqrcode.dto.catalog.ResponseCatalogDetail;
import it.jenia.metaqrcode.dto.link.ResponseLinkDetail;
import it.jenia.metaqrcode.dto.repository.ResponseRepositoryDetail;
import it.jenia.metaqrcode.server.commons.exception.MetaqrcodeException;
import it.jenia.metaqrcode.server.core.exception.CatalogEntryNotFoundException;
import it.jenia.metaqrcode.server.core.exception.RepositoryEntryNotFoundException;
import it.jenia.metaqrcode.server.core.exception.RepositoryLinkNotFoundException;
import it.jenia.metaqrcode.server.services.exception.GenericException;

/**
 * basic metaqrcode services, exposed via simple http get
 * 
 * @author andreatessaro
 *
 */
public interface BasicService {

	/**
	 * get the content of the catalog entry (XSD) for the given catalog entry (XSD) id
	 * @param id the id of the catalog entry (XSD)
	 * @return the catalog entry (XSD) content
	 * @throws CatalogEntryNotFoundException
	 * @throws GenericException
	 */
	public byte[] getCatalog(BigInteger id) throws CatalogEntryNotFoundException, GenericException, MetaqrcodeException;

	/**
	 * get detail of the catalog entry (XSD) for the given catalog entry (XSD) id
	 * @param id the id of the catalog entry (XSD)
	 * @return the catalog entry (XSD) datail
	 * @throws CatalogEntryNotFoundException
	 * @throws GenericException
	 */
	public ResponseCatalogDetail getCatalogDetail(BigInteger id);

	/**
	 * get the content of the repository entry (XML) for the given repository entry (XML) id
	 * @param id the id of the repository entry (XML)
	 * @return the repository entry (XML) content
	 * @throws RepositoryEntryNotFoundException
	 * @throws GenericException
	 */
	public byte[] getRepository(BigInteger id) throws RepositoryEntryNotFoundException, GenericException, MetaqrcodeException;
	
	/**
	 * get the content of the repository entry (XML) as json for the given repository entry (XML) id
	 * @param id the id of the repository entry (XML)
	 * @return the repository entry (XML) json content
	 * @throws RepositoryEntryNotFoundException
	 * @throws GenericException
	 */
	public byte[] getRepositoryAsJson(BigInteger id) throws RepositoryEntryNotFoundException, GenericException, MetaqrcodeException;
	
	/**
	 * get detail of the repository entry (XML) for the given repository entry (XML) id
	 * @param id the id of the repository entry (XML)
	 * @return the repository entry (XML) detail
	 * @throws RepositoryEntryNotFoundException
	 * @throws GenericException
	 */
	public ResponseRepositoryDetail getRepositoryDetail(BigInteger id);
	
	/**
	 * get the qrcode image (PNG) for the given repository entry (XML) id
	 * @param id the id of the repository entry (XML)
	 * @return the qrcode image (PNG)
	 * @throws RepositoryEntryNotFoundException
	 * @throws GenericException
	 */
	public byte[] getQRCode(BigInteger id) throws RepositoryEntryNotFoundException, GenericException, MetaqrcodeException;
	
	/**
	 * get the repository entry (XML) for the given link id
	 * @param otherCode the "other" barcode or qrcode
	 * @return the repository entry (XML) content
	 * @throws RepositoryLinkNotFoundException
	 * @throws GenericException
	 */
	public byte[] getLink(String otherCode) throws RepositoryLinkNotFoundException, GenericException, MetaqrcodeException;

	/**
	 * get the repository entry (XML) for the given link id
	 * @param otherCode the "other" barcode or qrcode
	 * @return the repository entry (XML) content
	 * @throws RepositoryLinkNotFoundException
	 * @throws GenericException
	 */
	public byte[] getLinkAsJson(String otherCode) throws RepositoryLinkNotFoundException, GenericException, MetaqrcodeException;

	/**
	 * get detail of the repository entry (XML) for the given link id
	 * @param otherCode the "other" barcode or qrcode
	 * @return the repository entry (XML) detail
	 * @throws RepositoryLinkNotFoundException
	 * @throws GenericException
	 */
	public ResponseLinkDetail getLinkDetail(String otherCode);

}
