package it.jenia.metaqrcode.client.java.rest;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.io.IOUtils;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import it.jenia.metaqrcode.client.catalog.sample.PersonData;
import it.jenia.metaqrcode.client.catalog.sample.Sex;
import it.jenia.metaqrcode.dto.catalog.RequestCatalogDelete;
import it.jenia.metaqrcode.dto.catalog.RequestCatalogUpload;
import it.jenia.metaqrcode.dto.catalog.ResponseCatalogDelete;
import it.jenia.metaqrcode.dto.catalog.ResponseCatalogUpload;
import it.jenia.metaqrcode.dto.link.RequestLinkCreate;
import it.jenia.metaqrcode.dto.link.RequestLinkDelete;
import it.jenia.metaqrcode.dto.link.RequestLinkDownload;
import it.jenia.metaqrcode.dto.link.ResponseLinkCreate;
import it.jenia.metaqrcode.dto.link.ResponseLinkDelete;
import it.jenia.metaqrcode.dto.repository.RequestRepositoryDelete;
import it.jenia.metaqrcode.dto.repository.RequestRepositoryUpload;
import it.jenia.metaqrcode.dto.repository.ResponseRepositoryDelete;
import it.jenia.metaqrcode.dto.repository.ResponseRepositoryUpload;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestLinkRest extends TestRest {

	private static final String NAME = "name";
	private static final String DESCRIPTION = "description";

	@Test
	public void a_test() throws Exception {
		verifyRegistrationAndLogin();
		BigInteger idC = null;
		BigInteger idR = null;
		String otherCode = null;
		String catalogGet = null;
		String linkGet = null;
		{
			RequestCatalogUpload req = new RequestCatalogUpload();
			req.setDescription(DESCRIPTION);
			req.setName(NAME);
			byte[] xsd = IOUtils.toByteArray(TestCatalogRest.class.getResourceAsStream("/schemaok2.xsd"));
			ResponseCatalogUpload res =  (ResponseCatalogUpload)doPostCallWithAttachmentXML("/rest/xml/catalog/upload", req, "xsd", xsd, ResponseCatalogUpload.class);
			assert(0 == res.getReturnCode());
			System.out.println("+++++++++++++++++++++++++Upload catalog ok ");
			System.out.println("+++++++++++++++++++++++++id = " + res.getId());
			System.out.println(" catalogGet = " + res.getCatalogGet());
			idC=res.getId();
			catalogGet = res.getCatalogGet();
		}
		{
			RequestRepositoryUpload req = new RequestRepositoryUpload();
			req.setCorrelationId(""+System.currentTimeMillis());
			byte[] xml = createXML(catalogGet);
			ResponseRepositoryUpload res =  (ResponseRepositoryUpload)doPostCallWithAttachmentXML("/rest/xml/repository/upload", req, "xml", xml, ResponseRepositoryUpload.class);
			assert(0 == res.getReturnCode());
			System.out.println("+++++++++++++++++++++++++Upload repository ok ");
			System.out.println("+++++++++++++++++++++++++id = " + res.getId());
			System.out.println(" qrcodeGet = " + res.getQrcodeGet());
			System.out.println(" repositoryGet = " + res.getRepositoryGet());
			idR=res.getId();
		}
		{
			RequestLinkCreate req = new RequestLinkCreate();
			req.setRepositoryId(idR);
			otherCode = ""+System.currentTimeMillis();
			req.setOtherCode(otherCode);
			ResponseLinkCreate res =  (ResponseLinkCreate)doPostCallBasicXML("/rest/xml/link/create", req, ResponseLinkCreate.class);
			assert(0 == res.getReturnCode());
			System.out.println("+++++++++++++++++++++++++Create link ok ");
			System.out.println("+++++++++++++++++++++++++id = " + res.getId());
			System.out.println(" linkGet = " + res.getLinkGet());
			linkGet = res.getLinkGet();
		}
		{
			RequestLinkDownload req = new RequestLinkDownload();
			req.setOtherCode(otherCode);
			byte[] res =  doPostCallReturningResourceXML("/rest/xml/link/download", req);
			assert(true == (res.length>0));
			System.out.println("+++++++++++++++++++++++++Download link ok ");
		}
		{
			byte[] res =  doGetCallReturningResource(linkGet);
			assert(true == (res.length>0));
			System.out.println("+++++++++++++++++++++++++Get link ok ");
		}
		{
			RequestLinkDelete req = new RequestLinkDelete();
			req.setOtherCode(otherCode);
			ResponseLinkDelete res =  (ResponseLinkDelete)doPostCallBasicXML("/rest/xml/link/delete", req, ResponseLinkDelete.class);
			assert(0 == res.getReturnCode());
			System.out.println("+++++++++++++++++++++++++Delete link ok ");
		}
		{
			RequestRepositoryDelete req = new RequestRepositoryDelete();
			req.setId(idR);
			ResponseRepositoryDelete res =  (ResponseRepositoryDelete)doPostCallBasicXML("/rest/xml/repository/delete", req, ResponseRepositoryDelete.class);
			assert(0 == res.getReturnCode());
			System.out.println("+++++++++++++++++++++++++Delete repository ok ");
		}
		{
			RequestCatalogDelete req = new RequestCatalogDelete();
			req.setId(idC);
			ResponseCatalogDelete res =  (ResponseCatalogDelete)doPostCallBasicXML("/rest/xml/catalog/delete", req, ResponseCatalogDelete.class);
			assert(0 == res.getReturnCode());
			System.out.println("+++++++++++++++++++++++++Delete catalog ok ");
		}
	}

	@SuppressWarnings("deprecation")
	public byte[] createXML(String schema) throws JAXBException {
		PersonData person = new PersonData();
		person.setAddress("via Garibaldi 45, Casalecchio di Reno (BO)");
		person.setBirthday(new Date(71, 6, 18));
		person.setBirthPlace("Calcinate(BG)");
		person.setCitizenship("Italian");
		person.seteMail("andrea.tessaro@jenia.it");
		person.setFirstName("Andrea");
		person.setLastName("Tessaro Porta");
		person.setPhone("+393280549059");
		person.setSex(Sex.MALE);
		person.setTaxCode("TSSNDR71L18B393R");
		
		JAXBContext jc = JAXBContext.newInstance(PersonData.class);
		 
	    Marshaller marshaller = jc.createMarshaller();
	    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    marshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, schema);
	    
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    marshaller.marshal(person, baos);
	        
		return baos.toByteArray();
	}
	
}
