package it.jenia.metaqrcode.server.core.api.impl;

import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;

import it.jenia.metaqrcode.dto.catalog.CatalogEntryDto;
import it.jenia.metaqrcode.dto.catalog.RequestCatalogDelete;
import it.jenia.metaqrcode.dto.catalog.RequestCatalogDetail;
import it.jenia.metaqrcode.dto.catalog.RequestCatalogDownload;
import it.jenia.metaqrcode.dto.catalog.RequestCatalogSearch;
import it.jenia.metaqrcode.dto.catalog.RequestCatalogUpload;
import it.jenia.metaqrcode.dto.catalog.ResponseCatalogDelete;
import it.jenia.metaqrcode.dto.catalog.ResponseCatalogDetail;
import it.jenia.metaqrcode.dto.catalog.ResponseCatalogSearch;
import it.jenia.metaqrcode.dto.catalog.ResponseCatalogUpload;
import it.jenia.metaqrcode.server.commons.constants.Constants;
import it.jenia.metaqrcode.server.commons.exception.MetaqrcodeException;
import it.jenia.metaqrcode.server.core.api.CatalogApiService;
import it.jenia.metaqrcode.server.core.exception.CatalogConnectedToCatalogException;
import it.jenia.metaqrcode.server.core.exception.CatalogConnectedToRepositoryException;
import it.jenia.metaqrcode.server.core.exception.CatalogEntryNotFoundException;
import it.jenia.metaqrcode.server.core.exception.GenericCatalogException;
import it.jenia.metaqrcode.server.core.exception.InvalidRequestException;
import it.jenia.metaqrcode.server.core.exception.InvalidXmlSchemaException;
import it.jenia.metaqrcode.server.core.exception.NotAuthorizedException;
import it.jenia.metaqrcode.server.core.util.AuthenticationService;
import it.jenia.metaqrcode.server.core.util.CatalogResourceResolverFactory;
import it.jenia.metaqrcode.server.core.util.URLApi;
import it.jenia.metaqrcode.server.core.util.UserAuthVerificationService;
import it.jenia.metaqrcode.server.entities.metadata.CatalogEntry;
import it.jenia.metaqrcode.server.entities.metadata.CatalogEntryStat;
import it.jenia.metaqrcode.server.repositories.metadata.CatalogEntryRepository;
import it.jenia.metaqrcode.server.repositories.metadata.RepositoryEntryRepository;
import lombok.extern.slf4j.Slf4j;

@Service(value="metaqrcode-server-core-CatalogApiServiceImpl")
@Slf4j
public class CatalogApiServiceImpl implements CatalogApiService {
	
	@Autowired
	private CatalogEntryRepository catalogEntryRepository;
	
	@Autowired
	private RepositoryEntryRepository repositoryEntryRepository;
	
	@Autowired
	private CatalogResourceResolverFactory catalogResourceResolverFactory;

	@Autowired
	private URLApi urlApi;

	@Autowired
	private UserAuthVerificationService userAuthVerificationService;

	@Autowired
	private AuthenticationService authenticationService;

	@Override
	@Transactional(value = Constants.METAQRCODE_TRANSACTION_MANAGER, propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = MetaqrcodeException.class)
	public ResponseCatalogUpload upload(RequestCatalogUpload request, byte[] xsd) throws MetaqrcodeException {
		try {
			if (log.isDebugEnabled()) log.debug("catalog upload request for " + request.getName());
			
			// TODO
			// controlli preliminari (duplicati???, basato su md5 del contenuto?)
			if (request.getName()==null || request.getName().isEmpty()) {
				if (log.isErrorEnabled()) {
					log.error("name is mandatory in upload catalog request");
				}
				throw new InvalidRequestException(3, "name is mandatory in upload catalog request");
			}
			if (request.getDescription()==null || request.getDescription().isEmpty()) {
				if (log.isErrorEnabled()) {
					log.error("description is mandatory in upload catalog request");
				}
				throw new InvalidRequestException(4, "description is mandatory in upload catalog request");
			}
			if (xsd==null || xsd.length==0) {
				if (log.isErrorEnabled()) {
					log.error("xsd can not be null");
				}
				throw new InvalidRequestException(5, "xsd can not be null");
			}
			
			// controlli a livello di utente
			if (!userAuthVerificationService.isCatalogUploadEnabled(request)) {
				if (log.isErrorEnabled()) {
					log.error("user not authorized for catalog entry upload");
				}
				throw new NotAuthorizedException(4, "user not authorized for catalog entry upload");
			}
			
			// validazione xml schema
			SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			List<CatalogEntry> catalogEntries = new ArrayList<CatalogEntry>();
			sf.setResourceResolver(catalogResourceResolverFactory.createResourceResolver(urlApi, catalogEntryRepository, catalogEntries));
			sf.newSchema(new StreamSource(new ByteArrayInputStream(xsd)));

			// persisto il nuovo catalogo
			CatalogEntry ce = new CatalogEntry();
			ce.setDescription(request.getDescription());
			ce.setName(request.getName());
			ce.setXsd(xsd);
			ce.setCatalogEntryList(catalogEntries);
			ce.setUser(authenticationService.getCurrentUser());
			
			CatalogEntryStat ces = new CatalogEntryStat();
			ces.setCatalogEntry(ce);
			ces.setNumberOfReferences(BigInteger.ZERO);
			ces.setStars(0d);
			
			ce.setCatalogEntryStat(ces);

			// insert nella tabella del catalogo
			ce = catalogEntryRepository.save(ce);

			// calcolo URL da restituire
			ResponseCatalogUpload ret = new ResponseCatalogUpload();
			ret.setId(ce.getId());
			ret.setCatalogGet(urlApi.calculateCatalogGet(ce));
			
			if (log.isDebugEnabled()) log.debug("catalog upload : success");
			return ret;
		} catch (MetaqrcodeException e) {
			throw e;
		} catch (SAXException e) {
			if (log.isInfoEnabled()) {
				log.info("invali xml schema", e);
			}
			throw new InvalidXmlSchemaException(1, "catalog upload : fail, "+e.getMessage(), e);
		} catch (Exception e) {
			log.error("catalog upload : fail, "+e.getMessage(), e);
			throw new GenericCatalogException(4, "catalog upload : fail, "+e.getMessage(), e);
		}
	}

	@Override
	@Transactional(value = Constants.METAQRCODE_TRANSACTION_MANAGER, propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = MetaqrcodeException.class)
	public ResponseCatalogSearch search(RequestCatalogSearch request) throws MetaqrcodeException {
		try {
			if (log.isDebugEnabled()) log.debug("catalog search request for " + request.getId() + " : " + request.getNameLike() + " : " + request.getDescriptionLike() + " : " + request.isOnlyMine());
			
			// controlli a livello utente
			if (!userAuthVerificationService.isCatalogSearchEnabled(request)) {
				if (log.isErrorEnabled()) {
					log.error("user not authorized for catalog search");
				}
				throw new NotAuthorizedException(3, "user not authorized for catalog search");
			}
			
			// preparazione dei parametri per la ricerca
			String nameLike = request.getNameLike()==null?"":request.getNameLike();
			if (nameLike.replaceAll("%", "").isEmpty())
				nameLike = null;
			if (nameLike!=null) {
				nameLike="%"+nameLike.replaceAll("%", "")+"%";
			}
			String descriptionLike = request.getDescriptionLike()==null?"":request.getDescriptionLike();
			if (descriptionLike.replaceAll("%", "").isEmpty())
				descriptionLike = null;
			if (descriptionLike!=null) {
				descriptionLike="%"+descriptionLike.replaceAll("%", "")+"%";
			}
			
			// search nella tabella del catalogo
			Integer pageNumber = request.getPageNumber();
			if (pageNumber==null) pageNumber = 0;
			Integer rowPerPage = request.getRowPerPage();
			if (rowPerPage==null) rowPerPage = 100;
			Pageable pageRequest = new PageRequest(pageNumber, rowPerPage);
			Page<CatalogEntry> ces = catalogEntryRepository.searchByIdAndNameAndDescriptionAndUser(request.getId(), nameLike, descriptionLike, request.isOnlyMine()?authenticationService.getCurrentUser().getId():null, pageRequest);
			
			// creo lista risposte
			ResponseCatalogSearch ret = new ResponseCatalogSearch();
			
			ret.setCurrentPageNumber(ces.getNumber());
			ret.setRowTotal(ces.getTotalElements());
			ret.setPageTotal(ces.getTotalPages());

			ret.setResult(new ArrayList<CatalogEntryDto>());
			for (CatalogEntry ce : ces) {
				CatalogEntryDto cedto = new CatalogEntryDto();
				cedto.setName(ce.getName());
				cedto.setDescription(ce.getDescription());
				cedto.setId(ce.getId());
				cedto.setCatalogGet(urlApi.calculateCatalogGet(ce));
				cedto.setNickNamePublisher(ce.getUser().getNickName());
				cedto.setNumberOfReferences(ce.getCatalogEntryStat().getNumberOfReferences());
				cedto.setStars(ce.getCatalogEntryStat().getStars());
				cedto.setCatalogEntries(new ArrayList<CatalogEntryDto>());
				for (CatalogEntry cer : ce.getCatalogEntryList()) {
					CatalogEntryDto cedtor = new CatalogEntryDto();
					cedtor.setName(cer.getName());
					cedtor.setDescription(cer.getDescription());
					cedtor.setId(cer.getId());
					cedtor.setCatalogGet(urlApi.calculateCatalogGet(cer));
					cedtor.setNickNamePublisher(cer.getUser().getNickName());
					cedtor.setNumberOfReferences(cer.getCatalogEntryStat().getNumberOfReferences());
					cedtor.setStars(cer.getCatalogEntryStat().getStars());
					cedto.getCatalogEntries().add(cedtor);
				}
				ret.getResult().add(cedto);
			}
			
			if (log.isDebugEnabled()) log.debug("catalog search : success");
			return ret;
		} catch (MetaqrcodeException e) {
			throw e;
		} catch (Exception e) {
			log.error("catalog search : fail, "+e.getMessage(), e);
			throw new GenericCatalogException(3, "catalog search : fail, "+e.getMessage(), e);
		}
	}

	@Override
	@Transactional(value = Constants.METAQRCODE_TRANSACTION_MANAGER, propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = MetaqrcodeException.class)
	public ResponseCatalogDetail detail(RequestCatalogDetail request) throws MetaqrcodeException {
		try {
			if (log.isDebugEnabled()) log.debug("catalog detail request for " + request.getId());
			
			CatalogEntry ce = catalogEntryRepository.findOne(request.getId());
			
			if (ce==null) {
				// se non trovato l'id richiesto...
				if (log.isInfoEnabled()) {
					log.info("catalog with id " + request.getId() + " does not exists, throwing exception");
				}
				throw new CatalogEntryNotFoundException(5,"catalog entry with id " + request.getId() + " not found");
			} 

			// controlli a livello utente
			if (!userAuthVerificationService.isCatalogDetailEnabled(ce)) {
				if (log.isErrorEnabled()) {
					log.error("user not authorized for catalog detail");
				}
				throw new NotAuthorizedException(18, "user not authorized for catalog detail");
			}

			// creo risposta
			ResponseCatalogDetail ret = new ResponseCatalogDetail();
			
			CatalogEntryDto cedto = new CatalogEntryDto();
			cedto.setName(ce.getName());
			cedto.setDescription(ce.getDescription());
			cedto.setId(ce.getId());
			cedto.setCatalogGet(urlApi.calculateCatalogGet(ce));
			cedto.setNickNamePublisher(ce.getUser().getNickName());
			cedto.setNumberOfReferences(ce.getCatalogEntryStat().getNumberOfReferences());
			cedto.setStars(ce.getCatalogEntryStat().getStars());
			cedto.setCatalogEntries(new ArrayList<CatalogEntryDto>());
			for (CatalogEntry cer : ce.getCatalogEntryList()) {
				CatalogEntryDto cedtor = new CatalogEntryDto();
				cedtor.setName(cer.getName());
				cedtor.setDescription(cer.getDescription());
				cedtor.setId(cer.getId());
				cedtor.setCatalogGet(urlApi.calculateCatalogGet(cer));
				cedtor.setNickNamePublisher(cer.getUser().getNickName());
				cedtor.setNumberOfReferences(cer.getCatalogEntryStat().getNumberOfReferences());
				cedtor.setStars(cer.getCatalogEntryStat().getStars());
				cedto.getCatalogEntries().add(cedtor);
			}
			ret.setCatalogEntry(cedto);
			
			if (log.isDebugEnabled()) log.debug("catalog detail : success");
			return ret;
		} catch (MetaqrcodeException e) {
			throw e;
		} catch (Exception e) {
			log.error("catalog detail : fail, "+e.getMessage(), e);
			throw new GenericCatalogException(6, "catalog detail : fail, "+e.getMessage(), e);
		}
	}

	@Override
	@Transactional(value = Constants.METAQRCODE_TRANSACTION_MANAGER, propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = MetaqrcodeException.class)
	public ResponseCatalogDelete delete(RequestCatalogDelete request) throws MetaqrcodeException {
		try {
			if (log.isDebugEnabled()) log.debug("catalog delete request for " + request.getId());

			// controlli preliminari (il documento esiste?)
			if (request.getId()==null) {
				if (log.isErrorEnabled()) {
					log.error("id is mandatory in delete catalog request");
				}
				throw new InvalidRequestException(1, "id is mandatory in delete catalog request");
			}

			// get puntuale in tabella
			CatalogEntry ce = catalogEntryRepository.findOne(request.getId());
			if (ce==null) {
				// se non trovato l'id richiesto...
				if (log.isErrorEnabled()) {
					log.error("catalog with id " + request.getId() + " does not exists, throwing exception");
				}
				throw new CatalogEntryNotFoundException(1,"catalog entry with id " + request.getId() + " not found");
			} 
			
			// controlli a livello di utente
			if (!userAuthVerificationService.isCatalogDeleteEnabled(ce)) {
				if (log.isErrorEnabled()) {
					log.error("user not authorized for deletion of this catalog entry");
				}
				throw new NotAuthorizedException(15, "user not authorized for deletion of this catalog entry");
			}
			// controlli preliminari (è puntato da qualche altro xsd?)
			CatalogEntry referringcatalogEntry = catalogEntryRepository.firstReferringCatalogEntryForCatalogEntry(ce);
			if (referringcatalogEntry!=null) {
				if (log.isErrorEnabled()) {
					log.error("catalog with id " + request.getId() + " is connected to at least 1 catalog : " + referringcatalogEntry.getName() + " [ id : "+referringcatalogEntry.getId()+"]");
				}
				throw new CatalogConnectedToCatalogException(1,"catalog with id " + request.getId() + " is connected to at least 1 xsd catalog : " + referringcatalogEntry.getName() + " [ id : "+referringcatalogEntry.getId()+"]");
			}
			// controlli preliminari (è puntato da qualche xml?)
			Long countReferringRe = repositoryEntryRepository.countRepositoryEntryForCatalogEntry(ce);
			if (!countReferringRe.equals(0L)) {
				if (log.isErrorEnabled()) {
					log.error("catalog with id " + request.getId() + " is connected to at least 1 repository");
				}
				throw new CatalogConnectedToRepositoryException(1,"catalog with id " + request.getId() + " is connected to at least 1 xml repository");
			}
			// se esiste la riga di catalogo --> delete
			catalogEntryRepository.delete(ce);

			ResponseCatalogDelete ret = new ResponseCatalogDelete();
			
			if (log.isDebugEnabled()) log.debug("catalog delete : success");
			return ret;
		} catch (MetaqrcodeException e) {
			throw e;
		} catch (Exception e) {
			log.error("catalog delete : fail, "+e.getMessage(), e);
			throw new GenericCatalogException(1,"catalog delete : fail, "+e.getMessage(), e);
		}
	}

	@Override
	@Transactional(value = Constants.METAQRCODE_TRANSACTION_MANAGER, propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = MetaqrcodeException.class)
	public byte[] download(RequestCatalogDownload request) throws MetaqrcodeException {
		try {
			if (log.isDebugEnabled()) log.debug("catalog download request for " + request.getId());
			
			// controlli preliminari
			if (request.getId()==null) {
				if (log.isErrorEnabled()) {
					log.error("id is mandatory in download catalog request");
				}
				throw new InvalidRequestException(2,"id is mandatory in download catalog request");
			}

			// get puntuale in tabella
			CatalogEntry ce = catalogEntryRepository.findOne(request.getId());
			if (ce==null) {
				// se non trovato l'id richiesto...
				if (log.isErrorEnabled()) {
					log.error("catalog with id " + request.getId() + " does not exists, throwing exception");
				}
				throw new CatalogEntryNotFoundException(2,"catalog entry with id " + request.getId() + " not found");
			}

			// controlli a livello di utente
			if (!userAuthVerificationService.isCatalogDownloadEnabled(ce)) {
				if (log.isErrorEnabled()) {
					log.error("user not authorized for download of this catalog entry");
				}
				throw new NotAuthorizedException(2, "user not authorized for download of this catalog entry");
			}

			if (log.isDebugEnabled()) log.debug("catalog download : success");
			return ce.getXsd();
		} catch (CatalogEntryNotFoundException e) {
			if (log.isInfoEnabled()) {
				log.info("catalog get : fail, "+e.getMessage(), e);
			}
			throw e;
		} catch (MetaqrcodeException e) {
			throw e;
		} catch (Exception e) {
			log.error("catalog get : fail, "+e.getMessage(), e);
			throw new GenericCatalogException(2, "catalog get : fail, "+e.getMessage(), e);
		}
	}

}
