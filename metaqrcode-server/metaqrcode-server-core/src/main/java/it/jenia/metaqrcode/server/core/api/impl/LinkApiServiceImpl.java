package it.jenia.metaqrcode.server.core.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.jenia.metaqrcode.dto.link.RequestLinkCreate;
import it.jenia.metaqrcode.dto.link.RequestLinkDelete;
import it.jenia.metaqrcode.dto.link.RequestLinkDetail;
import it.jenia.metaqrcode.dto.link.RequestLinkDownload;
import it.jenia.metaqrcode.dto.link.ResponseLinkCreate;
import it.jenia.metaqrcode.dto.link.ResponseLinkDelete;
import it.jenia.metaqrcode.dto.link.ResponseLinkDetail;
import it.jenia.metaqrcode.dto.repository.RequestRepositoryDetail;
import it.jenia.metaqrcode.dto.repository.RequestRepositoryDownload;
import it.jenia.metaqrcode.server.commons.constants.Constants;
import it.jenia.metaqrcode.server.commons.exception.MetaqrcodeException;
import it.jenia.metaqrcode.server.core.api.LinkApiService;
import it.jenia.metaqrcode.server.core.api.RepositoryApiService;
import it.jenia.metaqrcode.server.core.exception.GenericLinkException;
import it.jenia.metaqrcode.server.core.exception.InvalidRequestException;
import it.jenia.metaqrcode.server.core.exception.NotAuthorizedException;
import it.jenia.metaqrcode.server.core.exception.RepositoryEntryNotFoundException;
import it.jenia.metaqrcode.server.core.util.AuthenticationService;
import it.jenia.metaqrcode.server.core.util.URLApi;
import it.jenia.metaqrcode.server.core.util.UserAuthVerificationService;
import it.jenia.metaqrcode.server.entities.metadata.RepositoryEntry;
import it.jenia.metaqrcode.server.entities.metadata.RepositoryLink;
import it.jenia.metaqrcode.server.repositories.metadata.RepositoryEntryRepository;
import it.jenia.metaqrcode.server.repositories.metadata.RepositoryLinkRepository;
import lombok.extern.slf4j.Slf4j;

@Service(value="metaqrcode-server-core-LinkApiServiceImpl")
@Slf4j
public class LinkApiServiceImpl implements LinkApiService {
	
	@Autowired
	private RepositoryLinkRepository repositoryLinkRepository;

	@Autowired
	private RepositoryEntryRepository repositoryEntryRepository;

	@Autowired
	private RepositoryApiService repositoryApiService;

	@Autowired
	private UserAuthVerificationService userAuthVerificationService;
	
	@Autowired
	private URLApi urlApi;

	@Autowired
	private AuthenticationService authenticationService;

	@Override
	@Transactional(value = Constants.METAQRCODE_TRANSACTION_MANAGER, propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = MetaqrcodeException.class)
	public ResponseLinkCreate create(RequestLinkCreate request) throws MetaqrcodeException {
		try {
			if (log.isDebugEnabled()) log.debug("link create request ");
			
			// TODO
			// controlli preliminari (duplicati???, basato su md5 del contenuto?)
			if (repositoryLinkRepository.searchByOtherCode(request.getOtherCode())!=null) {
				if (log.isErrorEnabled()) {
					log.error("there is already a link referring this alternative code : " + request.getOtherCode());
				}
				throw new InvalidRequestException(49, "there is already a link referring this alternative code : " + request.getOtherCode());
			}

			// controlli a livello di utente
			if (!userAuthVerificationService.isLinkCreateEnabled(request)) {
				throw new NotAuthorizedException(5, "user not authorized for link create");
			}
			
			RepositoryLink rl = new RepositoryLink();
			rl.setOtherCode(request.getOtherCode());
			RepositoryEntry re = repositoryEntryRepository.findOne(request.getRepositoryId());
			if (re==null) {
				// se non trovato l'id richiesto...
				if (log.isInfoEnabled()) {
					log.info("repository entry with id " + request.getRepositoryId() + " does not exists, throwing exception");
				}
				throw new RepositoryEntryNotFoundException(1, "repository entry with id " + request.getRepositoryId() + " not found");
			}
			rl.setRepositoryEntry(re);
			rl.setUser(authenticationService.getCurrentUser());
			repositoryLinkRepository.save(rl);
			
			// calcolo URL da restituire
			ResponseLinkCreate ret = new ResponseLinkCreate();
			ret.setId(rl.getId());
			ret.setLinkGet(urlApi.calculateLinkGet(rl));
			
			if (log.isDebugEnabled()) log.debug("link create : success");
			return ret;
		} catch (MetaqrcodeException e) {
			throw e;
		} catch (DataIntegrityViolationException e) {
			if (log.isInfoEnabled()) {
				log.info("link insert constraint violation, could be that otherCode already exists", e);
			}
			throw new InvalidRequestException(6, "link insert constraint violation, could be that otherCode already exists", e);
		} catch (Exception e) {
			log.error("link create : fail, "+e.getMessage(), e);
			throw new GenericLinkException(1, "link create : fail, "+e.getMessage(), e);
		}
		
	}

	@Override
	@Transactional(value = Constants.METAQRCODE_TRANSACTION_MANAGER, propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = MetaqrcodeException.class)
	public ResponseLinkDelete delete(RequestLinkDelete request) throws MetaqrcodeException {
		try {
			if (log.isDebugEnabled()) log.debug("link delete request for " + request.getOtherCode());
			
			// controlli preliminari (il documento esiste?)
			if (request.getOtherCode()==null) {
				if (log.isErrorEnabled()) {
					log.error("otherCode is mandatory in delete link request");
				}
				throw new InvalidRequestException(7, "id is mandatory in delete link request");
			}

			// get puntuale in tabella
			RepositoryLink rl = repositoryLinkRepository.searchByOtherCode(request.getOtherCode());
			if (rl==null) {
				// se non trovato l'id richiesto...
				if (log.isInfoEnabled()) {
					log.info("link with otherCode " + request.getOtherCode() + " does not exists, throwing exception");
				}
				throw new RepositoryEntryNotFoundException(2, "link entry with otherCode " + request.getOtherCode() + " not found");
			} else {
				// controlli a livello di utente
				if (!userAuthVerificationService.isLinkDeleteEnabled(rl)) {
					throw new NotAuthorizedException(6, "user not authorized for deletion of this link");
				}
				// se esiste la riga di catalogo --> delete
				repositoryLinkRepository.delete(rl);
			}
			ResponseLinkDelete ret = new ResponseLinkDelete();
			
			if (log.isDebugEnabled()) log.debug("link delete : success");
			return ret;
		} catch (MetaqrcodeException e) {
			throw e;
		} catch (Exception e) {
			log.error("link delete : fail, "+e.getMessage(), e);
			throw new GenericLinkException(2, "link delete : fail, "+e.getMessage(), e);
		}
	}
	
	@Override
	@Transactional(value = Constants.METAQRCODE_TRANSACTION_MANAGER, propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = MetaqrcodeException.class)
	public ResponseLinkDetail detail(RequestLinkDetail request) throws MetaqrcodeException {
		try {
			if (log.isDebugEnabled()) log.debug("link detail request for " + request.getOtherCode());
			
			// controlli preliminari (il documento esiste?)
			if (request.getOtherCode()==null) {
				if (log.isErrorEnabled()) {
					log.error("other code is mandatory in detail link request");
				}
				throw new InvalidRequestException(57, "other code is mandatory in detail link request");
			}
			
			ResponseLinkDetail ret = new ResponseLinkDetail();

			// get puntuale in tabella
			RepositoryLink re = repositoryLinkRepository.searchByOtherCode(request.getOtherCode());
			if (re==null) {
				// se non trovato l'id richiesto...
				if (log.isInfoEnabled()) {
					log.info("link with code " + request.getOtherCode() + " does not exists, throwing exception");
				}
				throw new RepositoryEntryNotFoundException(13, "link entry with code " + request.getOtherCode() + " not found");
			} else {
				// controlli a livello di utente
				if (!userAuthVerificationService.isLinkDetailEnabled(re)) {
					throw new NotAuthorizedException(20, "user not authorized for get detail of this link");
				}
				RequestRepositoryDetail rrd = new RequestRepositoryDetail();
				rrd.setId(re.getRepositoryEntry().getId());
				ret.setRepositoryEntry(repositoryApiService.detail(rrd).getRepositoryEntry());
			}
			
			if (log.isDebugEnabled()) log.debug("link detail : success");
			return ret;
		} catch (MetaqrcodeException e) {
			throw e;
		} catch (Exception e) {
			log.error("link detail : fail, "+e.getMessage(), e);
			throw new GenericLinkException(5, "link detail : fail, "+e.getMessage(), e);
		}
	}

	@Override
	@Transactional(value = Constants.METAQRCODE_TRANSACTION_MANAGER, propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = MetaqrcodeException.class)
	public byte[] download(RequestLinkDownload request) throws MetaqrcodeException {
		try {
			if (log.isDebugEnabled()) log.debug("link download request for " + request.getOtherCode());
			
			// controlli preliminari (il documento esiste?)
			if (request.getOtherCode()==null) {
				if (log.isErrorEnabled()) {
					log.error("other code is mandatory in download link request");
				}
				throw new InvalidRequestException(8, "other code is mandatory in download link request");
			}
			
			byte[] ret;

			// get puntuale in tabella
			RepositoryLink re = repositoryLinkRepository.searchByOtherCode(request.getOtherCode());
			if (re==null) {
				// se non trovato l'id richiesto...
				if (log.isInfoEnabled()) {
					log.info("link with code " + request.getOtherCode() + " does not exists, throwing exception");
				}
				throw new RepositoryEntryNotFoundException(3, "link entry with code " + request.getOtherCode() + " not found");
			} else {
				// controlli a livello di utente
				if (!userAuthVerificationService.isLinkDownloadEnabled(re)) {
					throw new NotAuthorizedException(7, "user not authorized for download of this link");
				}
				RequestRepositoryDownload rrd = new RequestRepositoryDownload();
				rrd.setId(re.getRepositoryEntry().getId());
				ret=repositoryApiService.download(rrd);
			}
			
			if (log.isDebugEnabled()) log.debug("link download : success");
			return ret;
		} catch (MetaqrcodeException e) {
			throw e;
		} catch (Exception e) {
			log.error("link delete : fail, "+e.getMessage(), e);
			throw new GenericLinkException(3, "link download : fail, "+e.getMessage(), e);
		}
	}

	@Override
	@Transactional(value = Constants.METAQRCODE_TRANSACTION_MANAGER, propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = MetaqrcodeException.class)
	public byte[] downloadAsJson(RequestLinkDownload request) throws MetaqrcodeException {
		try {
			if (log.isDebugEnabled()) log.debug("link download request for " + request.getOtherCode());
			
			// controlli preliminari (il documento esiste?)
			if (request.getOtherCode()==null) {
				if (log.isErrorEnabled()) {
					log.error("other code is mandatory in download link request");
				}
				throw new InvalidRequestException(56, "other code is mandatory in download link request");
			}
			
			byte[] ret;

			// get puntuale in tabella
			RepositoryLink re = repositoryLinkRepository.searchByOtherCode(request.getOtherCode());
			if (re==null) {
				// se non trovato l'id richiesto...
				if (log.isInfoEnabled()) {
					log.info("link with code " + request.getOtherCode() + " does not exists, throwing exception");
				}
				throw new RepositoryEntryNotFoundException(12, "link entry with code " + request.getOtherCode() + " not found");
			} else {
				// controlli a livello di utente
				if (!userAuthVerificationService.isLinkDownloadEnabled(re)) {
					throw new NotAuthorizedException(19, "user not authorized for download of this link");
				}
				RequestRepositoryDownload rrd = new RequestRepositoryDownload();
				rrd.setId(re.getRepositoryEntry().getId());
				ret=repositoryApiService.downloadAsJson(rrd);
			}
			
			if (log.isDebugEnabled()) log.debug("link download : success");
			return ret;
		} catch (MetaqrcodeException e) {
			throw e;
		} catch (Exception e) {
			log.error("link delete : fail, "+e.getMessage(), e);
			throw new GenericLinkException(4, "link download : fail, "+e.getMessage(), e);
		}
	}

}
