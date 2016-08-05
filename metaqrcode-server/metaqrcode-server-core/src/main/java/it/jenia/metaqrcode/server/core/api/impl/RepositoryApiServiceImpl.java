package it.jenia.metaqrcode.server.core.api.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.xml.XMLConstants;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;
import org.xml.sax.SAXException;

import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import de.odysseus.staxon.json.JsonXMLConfig;
import de.odysseus.staxon.json.JsonXMLConfigBuilder;
import de.odysseus.staxon.json.JsonXMLOutputFactory;
import it.jenia.metaqrcode.dto.catalog.CatalogEntryDto;
import it.jenia.metaqrcode.dto.link.RepositoryLinkDto;
import it.jenia.metaqrcode.dto.repository.RepositoryEntryDto;
import it.jenia.metaqrcode.dto.repository.RequestRepositoryDelete;
import it.jenia.metaqrcode.dto.repository.RequestRepositoryDetail;
import it.jenia.metaqrcode.dto.repository.RequestRepositoryDownload;
import it.jenia.metaqrcode.dto.repository.RequestRepositoryQRCode;
import it.jenia.metaqrcode.dto.repository.RequestRepositorySearch;
import it.jenia.metaqrcode.dto.repository.RequestRepositoryUpdate;
import it.jenia.metaqrcode.dto.repository.RequestRepositoryUpload;
import it.jenia.metaqrcode.dto.repository.ResponseRepositoryDelete;
import it.jenia.metaqrcode.dto.repository.ResponseRepositoryDetail;
import it.jenia.metaqrcode.dto.repository.ResponseRepositorySearch;
import it.jenia.metaqrcode.dto.repository.ResponseRepositoryUpdate;
import it.jenia.metaqrcode.dto.repository.ResponseRepositoryUpload;
import it.jenia.metaqrcode.server.commons.constants.Constants;
import it.jenia.metaqrcode.server.commons.exception.MetaqrcodeException;
import it.jenia.metaqrcode.server.core.api.RepositoryApiService;
import it.jenia.metaqrcode.server.core.exception.GenericRepositoryException;
import it.jenia.metaqrcode.server.core.exception.InvalidCatalogURLException;
import it.jenia.metaqrcode.server.core.exception.InvalidRequestException;
import it.jenia.metaqrcode.server.core.exception.InvalidXmlException;
import it.jenia.metaqrcode.server.core.exception.NotAuthorizedException;
import it.jenia.metaqrcode.server.core.exception.RepositoryEntryNotFoundException;
import it.jenia.metaqrcode.server.core.util.AuthenticationService;
import it.jenia.metaqrcode.server.core.util.CatalogResourceResolverFactory;
import it.jenia.metaqrcode.server.core.util.URLApi;
import it.jenia.metaqrcode.server.core.util.UserAuthVerificationService;
import it.jenia.metaqrcode.server.entities.metadata.CatalogEntry;
import it.jenia.metaqrcode.server.entities.metadata.RepositoryEntry;
import it.jenia.metaqrcode.server.entities.metadata.RepositoryLink;
import it.jenia.metaqrcode.server.repositories.metadata.CatalogEntryRepository;
import it.jenia.metaqrcode.server.repositories.metadata.CatalogEntryStatRepository;
import it.jenia.metaqrcode.server.repositories.metadata.RepositoryEntryRepository;
import it.jenia.metaqrcode.server.repositories.metadata.RepositoryLinkRepository;
import lombok.extern.slf4j.Slf4j;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

@Service(value="metaqrcode-server-core-RepositorytApiServiceImpl")
@Slf4j
public class RepositoryApiServiceImpl implements RepositoryApiService {
	
	@Autowired
	private RepositoryEntryRepository repositoryEntryRepository;

	@Autowired
	private RepositoryLinkRepository repositoryLinkRepository;

	@Autowired
	private CatalogEntryRepository catalogEntryRepository;

	@Autowired
	private CatalogResourceResolverFactory catalogResourceResolverFactory;

	@Autowired
	private URLApi urlApi;

	@Autowired
	private UserAuthVerificationService userAuthVerificationService;
	
	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private CatalogEntryStatRepository catalogEntryStatRepository;
	
	@Value("${metaqrcode.server.core.qrcode-background}")
	private String qrcodeBackground;
	
	@Value("${metaqrcode.server.core.qrcode-font}")
	private String qrcodeFont;
	
	private byte[] qrcodeBackgroundBytes;
	
	@PostConstruct
	public void initBackground() {
		try {
			FileInputStream f = new FileInputStream(qrcodeBackground);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			StreamUtils.copy(f, baos);
			qrcodeBackgroundBytes=baos.toByteArray();
		} catch (Throwable t) {
			if (log.isErrorEnabled()) log.error("ATTENTION! Unable to load metaqrcode background, metaqrcode.server.core.qrcode-background : " + qrcodeBackground);
		}
	}
	
	@PostConstruct
	public void initFont() {
		try {
			FileInputStream f = new FileInputStream(qrcodeFont);
			Font font = Font.createFont(Font.TRUETYPE_FONT, f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
		} catch (Throwable t) {
			if (log.isErrorEnabled()) log.error("ATTENTION! Unable to load metaqrcode font, metaqrcode.server.core.qrcode-font : " + qrcodeFont);
		}
	}
	
	@Override
	@Transactional(value = Constants.METAQRCODE_TRANSACTION_MANAGER, propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = MetaqrcodeException.class)
	public ResponseRepositoryUpload upload(RequestRepositoryUpload request, byte[] xml) throws MetaqrcodeException {
		try {
			if (log.isDebugEnabled()) log.debug("repository upload request ");
			
			// TODO
			// controlli preliminari (duplicati???, basato su md5 del contenuto?)
			if (xml==null || xml.length==0) {
				if (log.isErrorEnabled()) {
					log.error("xml can not be null");
				}
				throw new InvalidRequestException(44, "xml can not be null");
			}
			
			// controlli a livello di utente
			if (!userAuthVerificationService.isRepositoryUploadEnabled(request)) {
				if (log.isInfoEnabled()) {
					log.info("user not authorized for repository entry upload");
				}
				throw new NotAuthorizedException(13, "user not authorized for repository entry upload");
			}
			
			// validazione xml
			// creare collezione unica degli xsd di riferimento per questa repositoryEntry
			// (lettura degli schema contenuti nell'xml ed associazione della repository entry correlate agli schema contenuti nel xml)
			List<CatalogEntry> catalogEntries = validateXml(request.getDefaultCatalog(), xml);
			
			for (CatalogEntry catalogEntry : catalogEntries) {
				catalogEntry.getCatalogEntryStat().setNumberOfReferences(catalogEntry.getCatalogEntryStat().getNumberOfReferences().add(BigInteger.ONE));
				catalogEntryStatRepository.save(catalogEntry.getCatalogEntryStat());
			}
			
			// persisto il nuovo catalogo
			RepositoryEntry re = new RepositoryEntry();
			re.setXml(xml);
			re.setCatalogEntryList(catalogEntries);
			re.setCorrelationId(request.getCorrelationId());
			re.setPersonal(request.isPersonal());
			re.setUser(authenticationService.getCurrentUser());
			// insert nella tabella del catalogo
			re = repositoryEntryRepository.save(re);
			
			// calcolo URL da restituire
			ResponseRepositoryUpload ret = new ResponseRepositoryUpload();
			ret.setId(re.getId());
			ret.setRepositoryGet(urlApi.calculateRepositoryGet(re));
			ret.setQrcodeGet(urlApi.calculateQrcodeGet(re));
			
			if (log.isDebugEnabled()) log.debug("repository upload : success");
			return ret;
		} catch (MetaqrcodeException e) {
			throw e;
		} catch (SAXException e) {
			if (log.isInfoEnabled()) {
				log.info("invali xml document", e);
			}
			throw new InvalidXmlException(3, "repository upload : fail, "+e.getMessage(), e);
		} catch (IOException e) {
			if (log.isInfoEnabled()) {
				log.info("invali xml document", e);
			}
			throw new InvalidXmlException(4, "repository upload : fail, "+e.getMessage(), e);
		} catch (Exception e) {
			log.error("repository upload : fail, "+e.getMessage(), e);
			throw new GenericRepositoryException(9, "repository upload : fail, "+e.getMessage(), e);
		}
	}

	@Override
	@Transactional(value = Constants.METAQRCODE_TRANSACTION_MANAGER, propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = MetaqrcodeException.class)
	public ResponseRepositoryUpdate update(RequestRepositoryUpdate request, byte[] xml) throws MetaqrcodeException {
		try {
			if (log.isDebugEnabled()) log.debug("repository update request ");
			
			// TODO
			// controlli preliminari (duplicati???, basato su md5 del contenuto?)
			if (xml==null || xml.length==0) {
				if (log.isErrorEnabled()) {
					log.error("xml can not be null");
				}
				throw new InvalidRequestException(42, "xml can not be null");
			}
			if (request.getId()==null) {
				if (log.isErrorEnabled()) {
					log.error("id is mandatory in update repository request");
				}
				throw new InvalidRequestException(43, "id is mandatory in update repository request");
			}
			
			// get puntuale in tabella
			RepositoryEntry re = repositoryEntryRepository.findOne(request.getId());
			if (re==null) {
				// se non trovato l'id richiesto...
				if (log.isInfoEnabled()) {
					log.info("repository with id " + request.getId() + " does not exists, throwing exception");
				}
				throw new RepositoryEntryNotFoundException(8, "repository entry with id " + request.getId() + " not found");
			}

			// controlli a livello di utente
			if (!userAuthVerificationService.isRepositoryUpdateEnabled(re)) {
				if (log.isInfoEnabled()) {
					log.info("user not authorized for repository entry update");
				}
				throw new NotAuthorizedException(12, "user not authorized for repository entry update");
			}
			
			// validazione xml
			// creare collezione unica degli xsd di riferimento per questa repositoryEntry
			// (lettura degli schema contenuti nell'xml ed associazione della repository entry correlate agli schema contenuti nel xml)
			List<CatalogEntry> catalogEntries = validateXml(request.getDefaultCatalog(), xml);
			
			// persisto il nuovo catalogo
			re.setXml(xml);
			re.setCatalogEntryList(catalogEntries);
			re.setPersonal(request.isPersonal());
			// insert nella tabella del catalogo
			re = repositoryEntryRepository.save(re);
			
			// calcolo URL da restituire
			ResponseRepositoryUpdate ret = new ResponseRepositoryUpdate();
			
			if (log.isDebugEnabled()) log.debug("repository update : success");
			return ret;
		} catch (MetaqrcodeException e) {
			throw e;
		} catch (SAXException e) {
			if (log.isInfoEnabled()) {
				log.info("invali xml document", e);
			}
			throw new InvalidXmlException(1, "repository update : fail, "+e.getMessage(), e);
		} catch (IOException e) {
			if (log.isInfoEnabled()) {
				log.info("invali xml document", e);
			}
			throw new InvalidXmlException(2, "repository update : fail, "+e.getMessage(), e);
		} catch (Exception e) {
			log.error("repository upload : fail, "+e.getMessage(), e);
			throw new GenericRepositoryException(8, "repository update : fail, "+e.getMessage(), e);
		}
	}

	private List<CatalogEntry> validateXml(String defaultCatalog, byte[] xml) throws SAXException, IOException, MetaqrcodeException {
		// gli xsd possono essere passati in request e/o possono essere presenti in xml
		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		List<CatalogEntry> catalogEntries = new ArrayList<CatalogEntry>();
		Schema schema = null;
		if (defaultCatalog!=null && !defaultCatalog.isEmpty()) {
			CatalogEntry ce = null;
			try {
				ce = urlApi.calculateCatalogEntry(defaultCatalog);
			} catch (InvalidCatalogURLException e) {
				log.error("Unable to calculate catalogEntry for default catalogURL : " + defaultCatalog);
				throw new InvalidCatalogURLException(5, "Unable to calculate catalogEntry for default catalogURL : " + defaultCatalog);
			} catch (Exception e) {
				if (log.isInfoEnabled()) {
					log.info("Invalid default catalog : " + defaultCatalog, e);
				}
				throw new InvalidCatalogURLException(1, "Invalid default catalog : " + defaultCatalog,e);
			}
			schema = sf.newSchema(new StreamSource(new ByteArrayInputStream(ce.getXsd())));
			catalogEntries.add(ce);
		} else {
			schema = sf.newSchema();
		}
		Validator validator = schema.newValidator();
		validator.setResourceResolver(catalogResourceResolverFactory.createResourceResolver(urlApi, catalogEntryRepository, catalogEntries));
		validator.validate(new StreamSource(new ByteArrayInputStream(xml)));
		
		// a questo punto catalogEntries dovrebbe avere tutto
		if (catalogEntries.isEmpty()) {
			if (log.isInfoEnabled()) {
				log.info("you have to specify at least one catalog entry this document is referring to");
			}
			throw new InvalidXmlException(5, "you have to specify at least one catalog entry this document is referring to");
		}
		return catalogEntries;
	}
	
	@Override
	@Transactional(value = Constants.METAQRCODE_TRANSACTION_MANAGER, propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = MetaqrcodeException.class)
	public ResponseRepositoryDelete delete(RequestRepositoryDelete request) throws MetaqrcodeException {
		try {
			if (log.isDebugEnabled()) log.debug("repository delete request for " + request.getId());
			
			// controlli preliminari (il documento esiste?)
			if (request.getId()==null) {
				if (log.isErrorEnabled()) {
					log.error("id is mandatory in delete repository request");
				}
				throw new InvalidRequestException(39, "id is mandatory in delete repository request");
			}

			// get puntuale in tabella
			RepositoryEntry re = repositoryEntryRepository.findOne(request.getId());
			if (re==null) {
				// se non trovato l'id richiesto...
				if (log.isErrorEnabled()) {
					log.error("repository with id " + request.getId() + " does not exists, throwing exception");
				}
				throw new RepositoryEntryNotFoundException(4, "repository entry with id " + request.getId() + " not found");
			} 
			// controlli a livello di utente
			if (!userAuthVerificationService.isRepositoryDeleteEnabled(re)) {
				if (log.isErrorEnabled()) {
					log.error("user not authorized for deletion of this repository entry");
				}
				throw new NotAuthorizedException(8, "user not authorized for deletion of this repository entry");
			}
			// controlli di congruenza
			List<RepositoryLink> links = repositoryLinkRepository.searchByRepositoryEntry(re);
			if (links!=null && !links.isEmpty()) {
				// se l'id richiesto ha links... KO
				if (log.isErrorEnabled()) {
					log.error("this repository has links, you have to delete links before delete repository entry");
				}
				throw new RepositoryEntryNotFoundException(5, "this repository has links, you have to delete links before delete repository entry");
			}
			// se esiste la riga di repository --> delete
			repositoryEntryRepository.delete(re);

			ResponseRepositoryDelete ret = new ResponseRepositoryDelete();
			
			if (log.isDebugEnabled()) log.debug("repository delete : success");
			return ret;
		} catch (MetaqrcodeException e) {
			throw e;
		} catch (Exception e) {
			log.error("repository delete : fail, "+e.getMessage(), e);
			throw new GenericRepositoryException(4, "repository delete : fail, "+e.getMessage(), e);
		}
	}

	@Override
	@Transactional(value = Constants.METAQRCODE_TRANSACTION_MANAGER, propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = MetaqrcodeException.class)
	public ResponseRepositorySearch search(RequestRepositorySearch request) throws MetaqrcodeException {
		try {
			if (log.isDebugEnabled()) log.debug("reopsitory search request for " + request.getId() + " : " + request.getCorrelationIdLike());
			
			// controlli a livello utente
			if (!userAuthVerificationService.isRepositorySearchEnabled(request)) {
				if (log.isErrorEnabled()) {
					log.error("user not authorized for catalog search");
				}
				throw new NotAuthorizedException(11, "user not authorized for catalog search");
			}
			
			// preparazione dei parametri per la ricerca
			String correlationIdLike = request.getCorrelationIdLike()==null?"":request.getCorrelationIdLike();
			if (correlationIdLike.replaceAll("%", "").isEmpty())
				correlationIdLike = null;
			if (correlationIdLike!=null) {
				correlationIdLike="%"+correlationIdLike.replaceAll("%", "")+"%";
			}
			
			// search nella tabella repository
			Integer pageNumber = request.getPageNumber();
			if (pageNumber==null) pageNumber = 0;
			Integer rowPerPage = request.getRowPerPage();
			if (rowPerPage==null) rowPerPage = 100;
			Pageable pageRequest = new PageRequest(pageNumber, rowPerPage);
			Page<RepositoryEntry> res = repositoryEntryRepository.searchByCatalogIdAndIdAndCorrelationIdAndUser(request.getId(), request.getCatalogEntryId(), correlationIdLike, (authenticationService.getCurrentUser()!=null)?authenticationService.getCurrentUser().getId():null, pageRequest);
			
			// creo lista risposte
			ResponseRepositorySearch ret = new ResponseRepositorySearch();
			ret.setCurrentPageNumber(res.getNumber());
			ret.setRowTotal(res.getTotalElements());
			ret.setPageTotal(res.getTotalPages());
			
			ret.setResult(new ArrayList<RepositoryEntryDto>());
			for (RepositoryEntry re : res) {
				RepositoryEntryDto redto = new RepositoryEntryDto();
				redto.setCorrelationId(re.getCorrelationId());
				redto.setId(re.getId());
				redto.setQrcodeGet(urlApi.calculateQrcodeGet(re));
				redto.setRepositoryGet(urlApi.calculateRepositoryGet(re));
				redto.setCatalogEntries(new ArrayList<CatalogEntryDto>());
				for (CatalogEntry ce : re.getCatalogEntryList()) {
					CatalogEntryDto cedto = new CatalogEntryDto();
					cedto.setName(ce.getName());
					cedto.setDescription(ce.getDescription());
					cedto.setId(ce.getId());
					cedto.setCatalogGet(urlApi.calculateCatalogGet(ce));
					cedto.setNickNamePublisher(ce.getUser().getNickName());
					cedto.setNumberOfReferences(ce.getCatalogEntryStat().getNumberOfReferences());
					cedto.setStars(ce.getCatalogEntryStat().getStars());
					redto.getCatalogEntries().add(cedto);
				}
				redto.setRepositoryLInks(new ArrayList<RepositoryLinkDto>());
				for (RepositoryLink rl : re.getRepositoryLinkList()) {
					RepositoryLinkDto rldto = new RepositoryLinkDto();
					rldto.setId(rl.getId());
					rldto.setLinkGet(urlApi.calculateLinkGet(rl));
					rldto.setOtherCode(rl.getOtherCode());
					redto.getRepositoryLInks().add(rldto);
				}
				ret.getResult().add(redto);
			}
			
			if (log.isDebugEnabled()) log.debug("repository search : success");
			return ret;
		} catch (MetaqrcodeException e) {
			throw e;
		} catch (Exception e) {
			log.error("repository search : fail, "+e.getMessage(), e);
			throw new GenericRepositoryException(7, "repository search : fail, "+e.getMessage(), e);
		}
	}

	@Override
	@Transactional(value = Constants.METAQRCODE_TRANSACTION_MANAGER, propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = MetaqrcodeException.class)
	public ResponseRepositoryDetail detail(RequestRepositoryDetail request) throws MetaqrcodeException {
		try {
			if (log.isDebugEnabled()) log.debug("reopsitory detail request for " + request.getId());
			
			RepositoryEntry re = repositoryEntryRepository.findOne(request.getId());
			
			// creo risposta
			ResponseRepositoryDetail ret = new ResponseRepositoryDetail();

			if (re==null) {
				// se non trovato l'id richiesto...
				if (log.isErrorEnabled()) {
					log.error("repository with id " + request.getId() + " does not exists, throwing exception");
				}
				throw new RepositoryEntryNotFoundException(14, "repository entry with id " + request.getId() + " not found");
			} 

			// controlli a livello utente
			if (!userAuthVerificationService.isRepositoryDetailEnabled(re)) {
				if (log.isErrorEnabled()) {
					log.error("user not authorized for repository detail");
				}
				throw new NotAuthorizedException(21, "user not authorized for repository detail");
			}
			
			RepositoryEntryDto redto = new RepositoryEntryDto();
			redto.setCorrelationId(re.getCorrelationId());
			redto.setId(re.getId());
			redto.setQrcodeGet(urlApi.calculateQrcodeGet(re));
			redto.setRepositoryGet(urlApi.calculateRepositoryGet(re));
			redto.setCatalogEntries(new ArrayList<CatalogEntryDto>());
			for (CatalogEntry ce : re.getCatalogEntryList()) {
				CatalogEntryDto cedto = new CatalogEntryDto();
				cedto.setName(ce.getName());
				cedto.setDescription(ce.getDescription());
				cedto.setId(ce.getId());
				cedto.setCatalogGet(urlApi.calculateCatalogGet(ce));
				cedto.setNickNamePublisher(ce.getUser().getNickName());
				cedto.setNumberOfReferences(ce.getCatalogEntryStat().getNumberOfReferences());
				cedto.setStars(ce.getCatalogEntryStat().getStars());
				redto.getCatalogEntries().add(cedto);
			}
			redto.setRepositoryLInks(new ArrayList<RepositoryLinkDto>());
			for (RepositoryLink rl : re.getRepositoryLinkList()) {
				RepositoryLinkDto rldto = new RepositoryLinkDto();
				rldto.setId(rl.getId());
				rldto.setLinkGet(urlApi.calculateLinkGet(rl));
				rldto.setOtherCode(rl.getOtherCode());
				redto.getRepositoryLInks().add(rldto);
			}
			ret.setRepositoryEntry(redto);
			
			if (log.isDebugEnabled()) log.debug("repository detail : success");
			return ret;
		} catch (MetaqrcodeException e) {
			throw e;
		} catch (Exception e) {
			log.error("repository detail : fail, "+e.getMessage(), e);
			throw new GenericRepositoryException(7, "repository detail : fail, "+e.getMessage(), e);
		}
	}

	@Override
	@Transactional(value = Constants.METAQRCODE_TRANSACTION_MANAGER, propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = MetaqrcodeException.class)
	public byte[] download(RequestRepositoryDownload request) throws MetaqrcodeException {
		try {
			if (log.isDebugEnabled()) log.debug("repository download request for " + request.getId());
			
			// controlli preliminari
			if (request.getId()==null) {
				if (log.isErrorEnabled()) {
					log.error("id is mandatory in download repository request");
				}
				throw new InvalidRequestException(40, "id is mandatory in download repository request");
			}

			// get puntuale in tabella
			RepositoryEntry re = repositoryEntryRepository.findOne(request.getId());
			if (re==null) {
				// se non trovato l'id richiesto...
				if (log.isErrorEnabled()) {
					log.error("repository entry with id " + request.getId() + " does not exists, throwing exception");
				}
				throw new RepositoryEntryNotFoundException(6, "repository entry with id " + request.getId() + " not found");
			}

			// controlli a livello di utente
			if (!userAuthVerificationService.isRepositoryDownloadEnabled(re)) {
				if (log.isErrorEnabled()) {
					log.error("user not authorized for download of this repository entry");
				}
				throw new NotAuthorizedException(9, "user not authorized for download of this repository entry");
			}

			if (log.isDebugEnabled()) log.debug("repository download : success");
			return re.getXml();
		} catch (RepositoryEntryNotFoundException e) {
			if (log.isInfoEnabled()) {
				log.info("repository download : fail, "+e.getMessage(), e);
			}
			throw e;
		} catch (MetaqrcodeException e) {
			throw e;
		} catch (Exception e) {
			log.error("repository download : fail, "+e.getMessage(), e);
			throw new GenericRepositoryException(5, "repository download : fail, "+e.getMessage(), e);
		}
	}

	@Override
	@Transactional(value = Constants.METAQRCODE_TRANSACTION_MANAGER, propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = MetaqrcodeException.class)
	public byte[] downloadAsJson(RequestRepositoryDownload request) throws MetaqrcodeException {
		try {
			if (log.isDebugEnabled()) log.debug("repository download request for " + request.getId());
			
			// controlli preliminari
			if (request.getId()==null) {
				if (log.isErrorEnabled()) {
					log.error("id is mandatory in download repository request");
				}
				throw new InvalidRequestException(50, "id is mandatory in download repository request");
			}

			// get puntuale in tabella
			RepositoryEntry re = repositoryEntryRepository.findOne(request.getId());
			if (re==null) {
				// se non trovato l'id richiesto...
				if (log.isErrorEnabled()) {
					log.error("repository entry with id " + request.getId() + " does not exists, throwing exception");
				}
				throw new RepositoryEntryNotFoundException(11, "repository entry with id " + request.getId() + " not found");
			}

			// controlli a livello di utente
			if (!userAuthVerificationService.isRepositoryDownloadEnabled(re)) {
				if (log.isErrorEnabled()) {
					log.error("user not authorized for download of this repository entry");
				}
				throw new NotAuthorizedException(14, "user not authorized for download of this repository entry");
			}

			if (log.isDebugEnabled()) log.debug("repository download : success");
			InputStream bais = new ByteArrayInputStream(re.getXml());
			ByteArrayOutputStream output = new ByteArrayOutputStream();
	        JsonXMLConfig config = new JsonXMLConfigBuilder()
	            .autoArray(true)
	            .autoPrimitive(true)
	            .prettyPrint(true)
	            .build();
	        try {
	            XMLEventReader reader = XMLInputFactory.newInstance().createXMLEventReader(bais);
	            XMLEventWriter writer = new JsonXMLOutputFactory(config).createXMLEventWriter(output);
	            writer.add(reader);
	            reader.close();
	            writer.close();
	        } finally {
	            output.close();
	            bais.close();
	        }
	        return output.toByteArray();
		} catch (RepositoryEntryNotFoundException e) {
			if (log.isInfoEnabled()) {
				log.info("repository download : fail, "+e.getMessage(), e);
			}
			throw e;
		} catch (MetaqrcodeException e) {
			throw e;
		} catch (Exception e) {
			log.error("repository download : fail, "+e.getMessage(), e);
			throw new GenericRepositoryException(10, "repository download : fail, "+e.getMessage(), e);
		}
	}

	@Override
	@Transactional(value = Constants.METAQRCODE_TRANSACTION_MANAGER, propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = MetaqrcodeException.class)
	public byte[] qrCode(RequestRepositoryQRCode request) throws MetaqrcodeException {
		try {
			if (log.isDebugEnabled()) log.debug("repository qrcode request for " + request.getId());
			
			// controlli preliminari
			if (request.getId()==null) {
				if (log.isErrorEnabled()) {
					log.error("id is mandatory in qrcode repository request");
				}
				throw new InvalidRequestException(41, "id is mandatory in qrcode repository request");
			}

			// get puntuale in tabella
			RepositoryEntry re = repositoryEntryRepository.findOne(request.getId());
			if (re==null) {
				// se non trovato l'id richiesto...
				if (log.isErrorEnabled()) {
					log.error("repository with id " + request.getId() + " does not exists, throwing exception");
				}
				throw new RepositoryEntryNotFoundException(7, "repository entry with id " + request.getId() + " not found");
			}

			// controlli a livello di utente
			if (!userAuthVerificationService.isRepositoryQRCodeEnabled(re)) {
				if (log.isErrorEnabled()) {
					log.error("user not authorized for download of this repository entry qrcode");
				}
				throw new NotAuthorizedException(10, "user not authorized for download of this repository entry qrcode");
			}
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			String repositoryGet = urlApi.calculateRepositoryGet(re);
			
			//
			// http://www.qrstuff.com/blog/2011/11/23/qr-code-minimum-size
			//
			// Generando un qrcode contenente fino a 50 caratteri, la dimensione ideale per la stampa è di 18mm (0,7 inch) (scansione a 15 cm di distanza)
			// partendo da una stama a 300pdi il calcolo per ottenere la dimensione (in pixel) del qrcode è il seguente : 
			// 300 (dpi) * 0.7 (inch) * 210 (pixel)
			// supponendo che la stampa venga fatta a 600dpi, significa che il qrcode prodotto può essere 
			// ridimensionato alla metà della dimensione senza perdere qualità
			
			QRCode.from(repositoryGet).
			to(ImageType.PNG).
			withCharset("UTF-8").
			withErrorCorrection(ErrorCorrectionLevel.H).
			withHint(EncodeHintType.MARGIN, 2).
			withSize(210, 210).
			writeTo(baos);
			
			ByteArrayOutputStream ret = null;
			
			if (qrcodeBackgroundBytes!=null) {
				// load source images
				BufferedImage image = ImageIO.read(new ByteArrayInputStream(qrcodeBackgroundBytes));
				BufferedImage background = ImageIO.read(new ByteArrayInputStream(baos.toByteArray()));
	
				// create the new image, canvas size is the max. of both image sizes
				BufferedImage combined = new BufferedImage(225, 225, BufferedImage.TYPE_BYTE_BINARY);
	
				// paint both images, preserving the alpha channels
				Graphics g = combined.getGraphics();

				// write text vertical on left
				BufferedImage combinedText = new BufferedImage(225, 225, BufferedImage.TYPE_INT_ARGB);
				Graphics2D gt = combinedText.createGraphics();
				Font font = new Font("Arial", Font.PLAIN, 15);
				gt.setFont(font);
				gt.setColor(Color.BLACK);
				gt.setBackground(Color.WHITE);
				gt.clearRect(0, 0, 225, 225);
		        gt.rotate(Math.toRadians(-90));
		        gt.drawString(String.format("%025d", re.getId()), -207, 222);
				g.drawImage(combinedText, 0, 0, null);

				g.drawImage(background, 0, 0, null);
				g.drawImage(image, 0, 0, null);
				
				ret = new ByteArrayOutputStream();

				// Save as new image
				ImageIO.write(combined, "PNG", ret);
			} else {
				ret=baos;
			}

			if (log.isDebugEnabled()) log.debug("repository qrcode : success");
			return ret.toByteArray();
		} catch (RepositoryEntryNotFoundException e) {
			if (log.isInfoEnabled()) {
				log.info("repository qrcode : fail, "+e.getMessage(), e);
			}
			throw e;
		} catch (MetaqrcodeException e) {
			throw e;
		} catch (Exception e) {
			log.error("repository qrcode : fail, "+e.getMessage(), e);
			throw new GenericRepositoryException(6, "repository qrcode : fail, "+e.getMessage(), e);
		}
	}

}
