package it.jenia.metaqrcode.server.core.util;

import it.jenia.metaqrcode.dto.catalog.RequestCatalogSearch;
import it.jenia.metaqrcode.dto.catalog.RequestCatalogUpload;
import it.jenia.metaqrcode.dto.link.RequestLinkCreate;
import it.jenia.metaqrcode.dto.repository.RequestRepositorySearch;
import it.jenia.metaqrcode.dto.repository.RequestRepositoryUpload;
import it.jenia.metaqrcode.server.entities.metadata.CatalogEntry;
import it.jenia.metaqrcode.server.entities.metadata.RepositoryEntry;
import it.jenia.metaqrcode.server.entities.metadata.RepositoryLink;

/**
 * utility class to verify user authorization about particular operations
 * 
 * @author andreatessaro
 *
 */
public interface UserAuthVerificationService {
	
// -- catalog
	
	/**
	 * verify if a user has the capability to upload a catalog entry
	 * 
	 * @param request
	 * @return
	 */
	public boolean isCatalogUploadEnabled(RequestCatalogUpload request);

	/**
	 * verify if a user has the capability to search for a catalog entry
	 * 
	 * @param request
	 * @return
	 */
	public boolean isCatalogSearchEnabled(RequestCatalogSearch request);

	/**
	 * verify if a user has the capability to get detail of a catalog entry
	 * 
	 * @param request
	 * @return
	 */
	public boolean isCatalogDetailEnabled(CatalogEntry catalogEntry);

	/**
	 * verify if a user has the capability to delete a catalog entry
	 * 
	 * @param request
	 * @return
	 */
	public boolean isCatalogDeleteEnabled(CatalogEntry catalogEntry);
	
	/**
	 * verify if a user has the capability to vote a catalog entry
	 * 
	 * @param request
	 * @return
	 */
	public boolean isCatalogVoteEnabled(CatalogEntry catalogEntry);
	
	/**
	 * verify if a user has the capability to download a catalog entry
	 * 
	 * @param request
	 * @return
	 */
	public boolean isCatalogDownloadEnabled(CatalogEntry catalogEntry);

// -- repository
	
	/**
	 * verify if a user has the capability to upload a repository entry
	 * 
	 * @param request
	 * @return
	 */
	public boolean isRepositoryUploadEnabled(RequestRepositoryUpload request);

	/**
	 * verify if a user has the capability to update a repository entry
	 * 
	 * @param request
	 * @return
	 */
	public boolean isRepositoryUpdateEnabled(RepositoryEntry repositoryEntry);

	/**
	 * verify if a user has the capability to delete a repository entry
	 * 
	 * @param request
	 * @return
	 */
	public boolean isRepositoryDeleteEnabled(RepositoryEntry repositoryEntry);
	
	/**
	 * verify if a user has the capability to search for a repository entry
	 * 
	 * @param request
	 * @return
	 */
	public boolean isRepositorySearchEnabled(RequestRepositorySearch repositoryEntry);
	
	/**
	 * verify if a user has the capability to get detail of a repository entry
	 * 
	 * @param request
	 * @return
	 */
	public boolean isRepositoryDetailEnabled(RepositoryEntry repositoryEntry);
	
	/**
	 * verify if a user has the capability to download a repository entry
	 * 
	 * @param request
	 * @return
	 */
	public boolean isRepositoryDownloadEnabled(RepositoryEntry repositoryEntry);

	/**
	 * verify if a user has the capability to download a repository entry qrcode
	 * 
	 * @param request
	 * @return
	 */
	public boolean isRepositoryQRCodeEnabled(RepositoryEntry repositoryEntry);

// -- link 

	/**
	 * verify if a user has the capability to create a repository link
	 * 
	 * @param request
	 * @return
	 */
	public boolean isLinkCreateEnabled(RequestLinkCreate repositoryEntry);
	
	/**
	 * verify if a user has the capability to delete a repository link
	 * 
	 * @param request
	 * @return
	 */
	public boolean isLinkDeleteEnabled(RepositoryLink repositoryLink);
	
	/**
	 * verify if a user has the capability to download a repository link
	 * 
	 * @param request
	 * @return
	 */
	public boolean isLinkDownloadEnabled(RepositoryLink repositoryLink);
	
	/**
	 * verify if a user has the capability to download a repository link
	 * 
	 * @param request
	 * @return
	 */
	public boolean isLinkDetailEnabled(RepositoryLink repositoryLink);
	
}
