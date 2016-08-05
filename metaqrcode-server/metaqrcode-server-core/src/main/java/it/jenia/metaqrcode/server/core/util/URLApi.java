package it.jenia.metaqrcode.server.core.util;

import java.util.List;

import it.jenia.metaqrcode.server.core.exception.InvalidCatalogEntryException;
import it.jenia.metaqrcode.server.core.exception.InvalidCatalogURLException;
import it.jenia.metaqrcode.server.core.exception.InvalidLinkURLException;
import it.jenia.metaqrcode.server.core.exception.InvalidRepositoryEntryException;
import it.jenia.metaqrcode.server.core.exception.InvalidRepositoryLinkException;
import it.jenia.metaqrcode.server.core.exception.InvalidRepositoryURLException;
import it.jenia.metaqrcode.server.entities.metadata.CatalogEntry;
import it.jenia.metaqrcode.server.entities.metadata.RepositoryEntry;
import it.jenia.metaqrcode.server.entities.metadata.RepositoryLink;

/**
 * utility class for calculating various URL
 * 
 * @author andreatessaro
 *
 */
public interface URLApi {
	
	/**
	 * calcolate the catalog get URL
	 * 
	 * @param catalogEntry
	 * @return
	 * @throws InvalidCatalogEntryException
	 */
	public String calculateCatalogGet(CatalogEntry catalogEntry) throws InvalidCatalogEntryException;

	/**
	 * calculate the repository get URL
	 * 
	 * @param repositoryEntry
	 * @return
	 * @throws InvalidRepositoryEntryException
	 */
	public String calculateRepositoryGet(RepositoryEntry repositoryEntry) throws InvalidRepositoryEntryException;

	/**
	 * calculate the qrcode get URL
	 * 
	 * @param repositoryEntry
	 * @return
	 * @throws InvalidRepositoryEntryException
	 */
	public String calculateQrcodeGet(RepositoryEntry repositoryEntry) throws InvalidRepositoryEntryException;

	/**
	 * calculate the link get URL
	 * 
	 * @param repositoryLink
	 * @return
	 * @throws InvalidRepositoryLinkException
	 */
	public String calculateLinkGet(RepositoryLink repositoryLink) throws InvalidRepositoryLinkException;

	/**
	 * from the given URL return the corresponding catalog entry
	 * 
	 * @param catalogGetURL
	 * @return
	 * @throws InvalidCatalogURLException
	 */
	public CatalogEntry calculateCatalogEntry(String catalogGetURL) throws InvalidCatalogURLException;

	/**
	 * from an URL list, return the corresponding catalog entry list
	 * 
	 * @param catalogGetURL
	 * @return
	 * @throws InvalidCatalogURLException
	 */
	public List<CatalogEntry> calculateCatalogEntry(List<String> catalogGetURL) throws InvalidCatalogURLException;

	/**
	 * from the given URL return the corresponding repository entry
	 * 
	 * @param repositoryGetURL
	 * @return
	 * @throws InvalidRepositoryURLException
	 */
	public RepositoryEntry calculateRepositoryEntry(String repositoryGetURL)  throws InvalidRepositoryURLException;

	/**
	 * from the given link get URL return the corresponding repositoryLink
	 * 
	 * @param linkGetURL
	 * @return
	 * @throws InvalidLinkURLException
	 */
	public RepositoryLink calculateRepositoryLink(String linkGetURL)  throws InvalidLinkURLException;

}
