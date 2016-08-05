package it.jenia.metaqrcode.server.core.util;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.apache.cxf.common.xmlschema.InvalidXmlSchemaReferenceException;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSResourceResolver;

import it.jenia.metaqrcode.server.core.exception.InvalidCatalogURLException;
import it.jenia.metaqrcode.server.entities.metadata.CatalogEntry;
import it.jenia.metaqrcode.server.repositories.metadata.CatalogEntryRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
/**
 * resolve XSD inside catalog entry
 * 
 * @author andreatessaro
 *
 */
public class DefaultCatalogResourceResolver implements LSResourceResolver {
	
	@Getter
	@Setter
	private CatalogEntryRepository catalogEntryRepository;
	
	@Getter
	@Setter
	private List<CatalogEntry> catalogEntries;
	
	@Getter
	@Setter
	private URLApi urlApi;

	public DefaultCatalogResourceResolver(URLApi urlApi, CatalogEntryRepository catalogEntryRepository, List<CatalogEntry> catalogEntries) {
		super();
		this.catalogEntryRepository = catalogEntryRepository;
		this.catalogEntries = catalogEntries;
		this.urlApi = urlApi;
	}

	@Override
	public LSInput resolveResource(String type, String namespaceURI, String publicId, String systemId, String baseURI) {
		if (log.isDebugEnabled()) {
			log.debug("trying to resolve resource in xsl schema validation : type = " + type + ", namespaceURI = " + namespaceURI+ ", publicId = " + publicId+ ", systemId = " + systemId + ", baseURI = " + baseURI);
		}
		try {
			CatalogEntry cesystemId = null; 
			CatalogEntry cenamespaceURI = null; 
			CatalogEntry cebaseURI = null;
			if (systemId!=null) {
				cesystemId = urlApi.calculateCatalogEntry(systemId);
			}
			if (cesystemId==null && namespaceURI!=null) {
				cenamespaceURI = urlApi.calculateCatalogEntry(namespaceURI);
			}
			if (cesystemId==null && cenamespaceURI==null && baseURI!=null) {
				cebaseURI = urlApi.calculateCatalogEntry(baseURI);
			}
			CatalogEntry ce = cesystemId;
			String uri = systemId;
			if (ce==null) {
				ce = cenamespaceURI;
				uri = namespaceURI;
				if (ce==null) {
					ce = cebaseURI;
					uri = baseURI;
				}
			}
			if (ce==null) {
				log.error("catalog entry not found for systemId : " + systemId);
				return null;
			}
			if (!catalogEntries.contains(ce)) {
				catalogEntries.add(ce);
			}
			LSInput ret = new DefaultLSInput(publicId, uri, new ByteArrayInputStream(ce.getXsd()));
			if (log.isDebugEnabled()) {
				log.debug("XSD reference found for Catalog Entry : " + ce.getId() + "["+ce.getName()+", "+ce.getDescription()+"]");
			}
			return ret;
		} catch (InvalidCatalogURLException e) {
			log.error("Unable to resolve XSD : type = " + type + ", namespaceURI = " + namespaceURI+ ", publicId = " + publicId+ ", systemId = " + systemId + ", baseURI = " + baseURI);
			throw new InvalidXmlSchemaReferenceException("Unable to resolve XSD : type = " + type + ", namespaceURI = " + namespaceURI+ ", publicId = " + publicId+ ", systemId = " + systemId + ", baseURI = " + baseURI, e);
		}
	}
}
