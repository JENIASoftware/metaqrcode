package it.jenia.metaqrcode.server.core.util;

import java.util.List;

import org.w3c.dom.ls.LSResourceResolver;

import it.jenia.metaqrcode.server.entities.metadata.CatalogEntry;
import it.jenia.metaqrcode.server.repositories.metadata.CatalogEntryRepository;

/**
 * catalog resolver, user for catalg entry (XML) validation
 * 
 * @author andreatessaro
 *
 */
public interface CatalogResourceResolverFactory {
	
	/**
	 * return the XSD resolver
	 * 
	 * @param urlApi
	 * @param catalogEntryRepository
	 * @param catalogEntries
	 * @return
	 */
	public LSResourceResolver createResourceResolver(URLApi urlApi, CatalogEntryRepository catalogEntryRepository, List<CatalogEntry> catalogEntries);

}
