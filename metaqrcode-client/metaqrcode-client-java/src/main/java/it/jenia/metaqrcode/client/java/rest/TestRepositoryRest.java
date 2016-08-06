/*
 * Copyright 2016 JENIA Software.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
import it.jenia.metaqrcode.dto.repository.RequestRepositoryDelete;
import it.jenia.metaqrcode.dto.repository.RequestRepositoryDownload;
import it.jenia.metaqrcode.dto.repository.RequestRepositoryUpdate;
import it.jenia.metaqrcode.dto.repository.RequestRepositoryUpload;
import it.jenia.metaqrcode.dto.repository.ResponseRepositoryDelete;
import it.jenia.metaqrcode.dto.repository.ResponseRepositoryUpdate;
import it.jenia.metaqrcode.dto.repository.ResponseRepositoryUpload;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRepositoryRest extends TestRest {

	private static final String NAME = "name";
	private static final String DESCRIPTION = "description";

	@Test
	public void a_test() throws Exception {
		BigInteger idC = null;
		BigInteger idR = null;
		String catalogGet = null;
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
			assert(0 ==  res.getReturnCode());
			System.out.println("+++++++++++++++++++++++++Upload repository ok ");
			System.out.println("+++++++++++++++++++++++++id = " + res.getId());
			System.out.println(" qrcodeGet = " + res.getQrcodeGet());
			System.out.println(" repositoryGet = " + res.getRepositoryGet());
			idR=res.getId();
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

	@Test
	public void b_test() throws Exception {
		BigInteger idC = null;
		BigInteger idR = null;
		String catalogGet = null;
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
			RequestRepositoryUpdate req = new RequestRepositoryUpdate();
			req.setId(idR);
			byte[] xml = createXML(catalogGet);
			ResponseRepositoryUpdate res =  (ResponseRepositoryUpdate)doPostCallWithAttachmentXML("/rest/xml/repository/update", req, "xml", xml, ResponseRepositoryUpdate.class);
			assert(0 == res.getReturnCode());
			System.out.println("+++++++++++++++++++++++++Update repository ok ");
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

	@Test
	public void c_test() throws Exception {
		BigInteger idC = null;
		BigInteger idR = null;
		String repositoryGet;
		String qrcodeGet;
		String catalogGet = null;
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
			repositoryGet = res.getRepositoryGet();
			qrcodeGet = res.getQrcodeGet();
			idR=res.getId();
		}
		{
			RequestRepositoryDownload req = new RequestRepositoryDownload();
			req.setId(idR);
			byte[] res =  doPostCallReturningResourceXML("/rest/xml/repository/download", req);
			assert(true == (res.length>0));
			System.out.println("+++++++++++++++++++++++++Download repoitory ok ");
		}
		{
			byte[] res =  doGetCallReturningResource(repositoryGet);
			assert(true == (res.length>0));
			System.out.println("+++++++++++++++++++++++++Get repository ok ");
		}
		{
			byte[] res =  doGetCallReturningResource(qrcodeGet);
			assert(true == (res.length>0));
			System.out.println("+++++++++++++++++++++++++Get qrcode ok ");
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
