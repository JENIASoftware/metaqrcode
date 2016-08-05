package it.jenia.metaqrcode.server.core.util.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jenia.metaqrcode.dto.catalog.RequestCatalogSearch;
import it.jenia.metaqrcode.dto.catalog.RequestCatalogUpload;
import it.jenia.metaqrcode.dto.link.RequestLinkCreate;
import it.jenia.metaqrcode.dto.repository.RequestRepositorySearch;
import it.jenia.metaqrcode.dto.repository.RequestRepositoryUpload;
import it.jenia.metaqrcode.server.core.util.AuthenticationService;
import it.jenia.metaqrcode.server.core.util.UserAuthVerificationService;
import it.jenia.metaqrcode.server.entities.metadata.CatalogEntry;
import it.jenia.metaqrcode.server.entities.metadata.RepositoryEntry;
import it.jenia.metaqrcode.server.entities.metadata.RepositoryLink;
import lombok.extern.slf4j.Slf4j;

@Service(value="metaqrcode-server-core-UserAuthVerificationServiceImpl")
@Slf4j
public class UserAuthVerificationServiceImpl implements UserAuthVerificationService {

	@Autowired
	private AuthenticationService securityService;

	@Override
	public boolean isCatalogUploadEnabled(RequestCatalogUpload request) {
		if (log.isDebugEnabled()) {
			log.debug("verify auth for catalog upload");
		}
		// by now, all user can upload catalog entry
		return true;
	}

	@Override
	public boolean isCatalogSearchEnabled(RequestCatalogSearch request) {
		if (log.isDebugEnabled()) {
			log.debug("verify auth for catalog search");
		}
		// by now, all user can search for catalog entry
		return true;
	}

	@Override
	public boolean isCatalogDetailEnabled(CatalogEntry catalogEntry) {
		if (log.isDebugEnabled()) {
			log.debug("verify auth for catalog detail");
		}
		// by now, all user can search for catalog entry
		return true;
	}

	@Override
	public boolean isCatalogDeleteEnabled(CatalogEntry catalogEntry) {
		if (log.isDebugEnabled()) {
			log.debug("verify auth for catalog delete");
		}
		if (catalogEntry.getUser().getId().equals(securityService.getCurrentUser().getId())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isCatalogDownloadEnabled(CatalogEntry catalogEntry) {
		if (log.isDebugEnabled()) {
			log.debug("verify auth for catalog download");
		}
		// by now, all user can download every catalog entry
		return true;
	}

	@Override
	public boolean isCatalogVoteEnabled(CatalogEntry catalogEntry) {
		if (log.isDebugEnabled()) {
			log.debug("verify auth for catalog vote");
		}
		// by now, all user can vote catalog entry
		return true;
	}

	@Override
	public boolean isRepositoryUploadEnabled(RequestRepositoryUpload request) {
		if (log.isDebugEnabled()) {
			log.debug("verify auth for repository upload");
		}
		// by now, all user can upload repository entry
		return true;
	}

	@Override
	public boolean isRepositoryUpdateEnabled(RepositoryEntry repositoryEntry) {
		if (log.isDebugEnabled()) {
			log.debug("verify auth for repository update");
		}
		if (repositoryEntry.getUser().getId().equals(securityService.getCurrentUser().getId())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isRepositoryDeleteEnabled(RepositoryEntry repositoryEntry) {
		if (log.isDebugEnabled()) {
			log.debug("verify auth for repository delete");
		}
		if (repositoryEntry.getUser().getId().equals(securityService.getCurrentUser().getId())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isRepositorySearchEnabled(RequestRepositorySearch repositoryEntry) {
		if (log.isDebugEnabled()) {
			log.debug("verify auth for repository search");
		}
		// by now, all user can search for repository entry
		return true;
	}

	@Override
	public boolean isRepositoryDetailEnabled(RepositoryEntry repositoryEntry) {
		if (log.isDebugEnabled()) {
			log.debug("verify auth for repository detail");
		}
		if (repositoryEntry.isPersonal()) {
			if (repositoryEntry.getUser().getId().equals(securityService.getCurrentUser().getId())) {
				return true;
			} else {
				return false;
			}
		} else { 
			return true;
		}
	}

	@Override
	public boolean isRepositoryDownloadEnabled(RepositoryEntry repositoryEntry) {
		if (log.isDebugEnabled()) {
			log.debug("verify auth for repository download");
		}
		if (repositoryEntry.isPersonal()) {
			if (repositoryEntry.getUser().getId().equals(securityService.getCurrentUser().getId())) {
				return true;
			} else {
				return false;
			}
		} else { 
			return true;
		}
	}

	@Override
	public boolean isRepositoryQRCodeEnabled(RepositoryEntry repositoryEntry) {
		if (log.isDebugEnabled()) {
			log.debug("verify auth for repository qrcode download");
		}
		return true;
	}

	@Override
	public boolean isLinkCreateEnabled(RequestLinkCreate repositoryEntry) {
		if (log.isDebugEnabled()) {
			log.debug("verify auth for link create");
		}
		// by now, all user can create link entry
		return true;
	}

	@Override
	public boolean isLinkDeleteEnabled(RepositoryLink repositoryLink) {
		if (log.isDebugEnabled()) {
			log.debug("verify auth for link delete");
		}
		if (repositoryLink.getUser().getId().equals(securityService.getCurrentUser().getId())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isLinkDownloadEnabled(RepositoryLink repositoryLink) {
		if (log.isDebugEnabled()) {
			log.debug("verify auth for link download");
		}
		// by now, all user can download link entry
		return true;
	}

	@Override
	public boolean isLinkDetailEnabled(RepositoryLink repositoryLink) {
		if (log.isDebugEnabled()) {
			log.debug("verify auth for link detail");
		}
		// by now, all user can download link entry
		return true;
	}

}
