package it.jenia.metaqrcode.server.core.util.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import it.jenia.metaqrcode.server.core.exception.InvalidCatalogEntryException;
import it.jenia.metaqrcode.server.core.exception.InvalidCatalogURLException;
import it.jenia.metaqrcode.server.core.exception.InvalidLinkURLException;
import it.jenia.metaqrcode.server.core.exception.InvalidRepositoryEntryException;
import it.jenia.metaqrcode.server.core.exception.InvalidRepositoryLinkException;
import it.jenia.metaqrcode.server.core.exception.InvalidRepositoryURLException;
import it.jenia.metaqrcode.server.core.util.URLApi;
import it.jenia.metaqrcode.server.entities.metadata.CatalogEntry;
import it.jenia.metaqrcode.server.entities.metadata.RepositoryEntry;
import it.jenia.metaqrcode.server.entities.metadata.RepositoryLink;
import it.jenia.metaqrcode.server.repositories.metadata.CatalogEntryRepository;
import it.jenia.metaqrcode.server.repositories.metadata.RepositoryEntryRepository;
import it.jenia.metaqrcode.server.repositories.metadata.RepositoryLinkRepository;
import lombok.extern.slf4j.Slf4j;

@Service(value="metaqrcode-server-core-URLApiImpl")
@Slf4j
public class URLApiImpl implements URLApi {
	
	private static final String SLASH = "/";

	@Value("${metaqrcode.server.core.scheme}")
	private String scheme;

	@Value("${metaqrcode.server.core.host}")
	private String host;

	@Value("${metaqrcode.server.core.port}")
	private String port;

	@Value("${metaqrcode.server.core.contextPath}")
	private String contextPath;

	@Value("${metaqrcode.server.core.catalogGet}")
	private String catalogGet;

	@Value("${metaqrcode.server.core.repositoryGet}")
	private String repositoryGet;

	@Value("${metaqrcode.server.core.qrcodeGet}")
	private String qrcodeGet;

	@Value("${metaqrcode.server.core.linkGet}")
	private String linkGet;
	
	@Autowired
	private RepositoryLinkRepository repositoryLinkRepository;

	@Autowired
	private RepositoryEntryRepository repositoryEntryRepository;

	@Autowired
	private CatalogEntryRepository catalogEntryRepository;
	
	private String prefixCatalogGet;

	private String prefixRepositoryGet;

	private String prefixQrcodeGet;

	private String prefixLinkGet;

	@PostConstruct
	public void initPrefixes() {
		prefixCatalogGet=scheme+"://"+host+((port==null||port.isEmpty()||(scheme.equals("http") && port.equals("80"))||(scheme.equals("https") && port.equals("443")))?"":":"+port)+contextPath+catalogGet+SLASH;
		prefixRepositoryGet=scheme+"://"+host+((port==null||port.isEmpty()||(scheme.equals("http") && port.equals("80"))||(scheme.equals("https") && port.equals("443")))?"":":"+port)+contextPath+repositoryGet+SLASH;
		prefixQrcodeGet=scheme+"://"+host+((port==null||port.isEmpty()||(scheme.equals("http") && port.equals("80"))||(scheme.equals("https") && port.equals("443")))?"":":"+port)+contextPath+qrcodeGet+SLASH;
		prefixLinkGet=scheme+"://"+host+((port==null||port.isEmpty()||(scheme.equals("http") && port.equals("80"))||(scheme.equals("https") && port.equals("443")))?"":":"+port)+contextPath+linkGet+SLASH;
	}

	@Override
	public String calculateCatalogGet(CatalogEntry catalogEntry) throws InvalidCatalogEntryException {
		if (catalogEntry==null || catalogEntry.getId()==null) {
			log.error("Unable to calculate catalogGet for empty or unsaved catalogEntry");
			throw new InvalidCatalogEntryException(1, "Unable to calculate catalogGet for empty or unsaved catalogEntry");
		}
		if (log.isDebugEnabled()) {
			log.debug("calculating URL for catalog entry " + catalogEntry.getId());
		}
		String ret = prefixCatalogGet+catalogEntry.getId();
		if (log.isDebugEnabled()) {
			log.debug("catalog entry url calculated : " + ret);
		}
		return ret;
	}
	
	@Override
	public String calculateRepositoryGet(RepositoryEntry repositoryEntry) throws InvalidRepositoryEntryException {
		if (repositoryEntry==null || repositoryEntry.getId()==null) {
			log.error("Unable to calculate repositoryGet for empty or unsaved catalogEntry");
			throw new InvalidRepositoryEntryException(2, "Unable to calculate RepositoryGet for empty or unsaved repositoryEntry");
		}
		if (log.isDebugEnabled()) {
			log.debug("calculating URL for repository entry " + repositoryEntry.getId());
		}
		String ret = prefixRepositoryGet+repositoryEntry.getId();
		if (log.isDebugEnabled()) {
			log.debug("repository entry url calculated : " + ret);
		}
		return ret;
	}
	
	@Override
	public String calculateQrcodeGet(RepositoryEntry repositoryEntry) throws InvalidRepositoryEntryException {
		if (repositoryEntry==null || repositoryEntry.getId()==null) {
			log.error("Unable to calculate qrcodeGet for empty or unsaved catalogEntry");
			throw new InvalidRepositoryEntryException(1, "Unable to calculate QrcodeGet for empty or unsaved repositoryEntry");
		}
		if (log.isDebugEnabled()) {
			log.debug("calculating URL for repository entry qrcode " + repositoryEntry.getId());
		}
		String ret = prefixQrcodeGet+repositoryEntry.getId();
		if (log.isDebugEnabled()) {
			log.debug("repository entry qrcode url calculated : " + ret);
		}
		return ret;
	}
	
	@Override
	public String calculateLinkGet(RepositoryLink repositoryLink) throws InvalidRepositoryLinkException {
		if (repositoryLink==null || repositoryLink.getId()==null) {
			log.error("Unable to calculate linkGet for empty or unsaved linkEntry");
			throw new InvalidRepositoryLinkException(1, "Unable to calculate linkGet for empty or unsaved linkEntry");
		}
		if (log.isDebugEnabled()) {
			log.debug("calculating URL for repository link " + repositoryLink.getId());
		}
		String ret = prefixLinkGet+Base64.encodeBase64URLSafeString(repositoryLink.getOtherCode().getBytes());
		if (log.isDebugEnabled()) {
			log.debug("repository link url calculated : " + ret);
		}
		return ret;
	}

	@Override
	public CatalogEntry calculateCatalogEntry(String catalogGetURL) throws InvalidCatalogURLException {
		if (catalogGetURL==null || catalogGetURL.isEmpty()) {
			log.error("Unable to calculate catalogEntry for empty catalogURL : " + catalogGetURL);
			throw new InvalidCatalogURLException(3, "Unable to calculate catalogEntry for empty catalogURL : " + catalogGetURL);
		}
		if (log.isDebugEnabled()) {
			log.debug("calculating catalog entry for URL " + catalogGetURL);
		}
		CatalogEntry ret = null;
		try {
			ret = catalogEntryRepository.findOne(calculateId(catalogGetURL, prefixCatalogGet));
		} catch (Exception e) {
			log.error("Unable to calculate catalogEntry for empty catalogURL : " + catalogGetURL);
			throw new InvalidCatalogURLException(4, "Unable to calculate catalogEntry for empty catalogURL : " + catalogGetURL);
		}
		if (ret!=null) {
			if (log.isDebugEnabled()) {
				log.debug("catalog entry calculated for url : " + ret.getId());
			}
		} else {
			if (log.isDebugEnabled()) {
				log.debug("catalog entry notfound for url : " + catalogGetURL);
			}
		}
		return ret;
	}
	
	@Override
	public List<CatalogEntry> calculateCatalogEntry(List<String> catalogGetURLs) throws InvalidCatalogURLException {
		if (catalogGetURLs==null) return new ArrayList<CatalogEntry>();
		if (log.isDebugEnabled()) {
			log.debug("calculating catalog entry for URL " + catalogGetURLs);
		}
		List<BigInteger> toSearch = new ArrayList<BigInteger>();
		for (String catalogGetURL : catalogGetURLs) {
			if (catalogGetURL==null || catalogGetURL.isEmpty()) {
				log.error("Unable to calculate catalogEntry for empty catalogURL");
				throw new InvalidCatalogURLException(2, "Unable to calculate catalogEntry for empty catalogURL");
			}
			toSearch.add(calculateId(catalogGetURL, prefixCatalogGet));
		}
		List<CatalogEntry> ret  = (List<CatalogEntry>)catalogEntryRepository.findAll(toSearch);
		if (log.isDebugEnabled()) {
			log.debug("catalog entry calculated for urls : " + ret);
		}
		return ret;
	}
	
	@Override
	public RepositoryEntry calculateRepositoryEntry(String repositoryGetURL) throws InvalidRepositoryURLException {
		if (repositoryGetURL==null || repositoryGetURL.isEmpty()) {
			log.error("Unable to calculate repositoryEntry for empty repositoryURL");
			throw new InvalidRepositoryURLException(1, "Unable to calculate repositoryEntry for empty repositoryURL");
		}
		if (log.isDebugEnabled()) {
			log.debug("calculating repository entry for URL " + repositoryGetURL);
		}
		RepositoryEntry ret  = repositoryEntryRepository.findOne(calculateId(repositoryGetURL, prefixRepositoryGet));
		if (ret!=null) {
			if (log.isDebugEnabled()) {
				log.debug("repository entry calculated for url : " + ret.getId());
			}
		} else {
			if (log.isDebugEnabled()) {
				log.debug("repository entry not found for url : " + repositoryGetURL);
			}
		}
		return ret;
	}
	
	@Override
	public RepositoryLink calculateRepositoryLink(String linkGetURL) throws InvalidLinkURLException {
		if (linkGetURL==null || linkGetURL.isEmpty()) {
			log.error("Unable to calculate repositoryLink for empty linkURL");
			throw new InvalidLinkURLException(1, "Unable to calculate repositoryLink for empty linkURL");
		}
		if (log.isDebugEnabled()) {
			log.debug("calculating repository Link for URL " + linkGetURL);
		}
		RepositoryLink ret  = repositoryLinkRepository.findOne(calculateId(linkGetURL, prefixLinkGet));
		if (ret!=null) {
			if (log.isDebugEnabled()) {
				log.debug("repository link calculated for url : " + ret.getId());
			}
		} else {
			if (log.isDebugEnabled()) {
				log.debug("repository link not found for url : " + linkGetURL);
			}
		}
		return ret;
	}
	
	private BigInteger calculateId(String url, String lastPathPart) {
		if (log.isDebugEnabled()) {
			log.debug("calculating Id for URL " + url);
		}
		String idStr = url.substring(url.indexOf(lastPathPart) + lastPathPart.length());
		if (log.isDebugEnabled()) {
			log.debug("id calculated " + idStr + ", parsing");
		}
		return new BigInteger(idStr);
	}

}
