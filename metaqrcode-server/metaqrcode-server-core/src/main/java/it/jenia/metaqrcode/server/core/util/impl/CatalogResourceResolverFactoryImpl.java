package it.jenia.metaqrcode.server.core.util.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSResourceResolver;

import it.jenia.metaqrcode.server.core.util.CatalogResourceResolverFactory;
import it.jenia.metaqrcode.server.core.util.DefaultCatalogResourceResolver;
import it.jenia.metaqrcode.server.core.util.URLApi;
import it.jenia.metaqrcode.server.entities.metadata.CatalogEntry;
import it.jenia.metaqrcode.server.repositories.metadata.CatalogEntryRepository;
import lombok.extern.slf4j.Slf4j;

@Service(value="metaqrcode-server-core-CatalogResourceResolverFactoryImpl")
@Slf4j
public class CatalogResourceResolverFactoryImpl implements CatalogResourceResolverFactory {
	
	@Override
	public LSResourceResolver createResourceResolver(URLApi urlApi, CatalogEntryRepository catalogEntryRepository, List<CatalogEntry> catalogEntries) {
		if (log.isDebugEnabled()) {
			log.debug("returning default catalog resource resolver");
		}
		return new DefaultCatalogResourceResolver(urlApi, catalogEntryRepository, catalogEntries);
	}
	
}
