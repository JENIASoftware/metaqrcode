package it.jenia.metaqrcode.client.java.rest;

import it.jenia.metaqrcode.dto.catalog.RequestCatalogDelete;
import it.jenia.metaqrcode.dto.catalog.RequestCatalogDownload;
import it.jenia.metaqrcode.dto.catalog.RequestCatalogSearch;
import it.jenia.metaqrcode.dto.catalog.RequestCatalogUpload;
import it.jenia.metaqrcode.dto.catalog.ResponseCatalogDelete;
import it.jenia.metaqrcode.dto.catalog.ResponseCatalogSearch;
import it.jenia.metaqrcode.dto.catalog.ResponseCatalogUpload;

import java.math.BigInteger;

import org.apache.commons.io.IOUtils;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCatalogRest extends TestRest {

	private static final String NAME = "name";
	private static final String DESCRIPTION = "description";

	@Test
	public void a_test() throws Exception {
		BigInteger idC = null;
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
		}
		{
			RequestCatalogSearch req = new RequestCatalogSearch();
			req.setId(idC);
			ResponseCatalogSearch res =  (ResponseCatalogSearch)doPostCallBasicXML("/rest/xml/catalog/search", req, ResponseCatalogSearch.class);
			assert(0 == res.getReturnCode());
			assert(1 == res.getResult().size());
			System.out.println("+++++++++++++++++++++++++Search catalog ok ");
		}
		{
			RequestCatalogSearch req = new RequestCatalogSearch();
			req.setDescriptionLike(DESCRIPTION);
			ResponseCatalogSearch res =  (ResponseCatalogSearch)doPostCallBasicXML("/rest/xml/catalog/search", req, ResponseCatalogSearch.class);
			assert(0 == res.getReturnCode());
			assert(1 == res.getResult().size());
			System.out.println("+++++++++++++++++++++++++Search catalog ok ");
		}
		{
			RequestCatalogSearch req = new RequestCatalogSearch();
			req.setNameLike(NAME);
			ResponseCatalogSearch res =  (ResponseCatalogSearch)doPostCallBasicXML("/rest/xml/catalog/search", req, ResponseCatalogSearch.class);
			assert(0 == res.getReturnCode());
			assert(1 == res.getResult().size());
			System.out.println("+++++++++++++++++++++++++Search catalog ok ");
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
		String catalogGet;
		{
			RequestCatalogUpload req = new RequestCatalogUpload();
			req.setDescription(DESCRIPTION);
			req.setName(NAME);
			byte[] xsd = IOUtils.toByteArray(TestCatalogRest.class.getResourceAsStream("/schemaok2.xsd"));
			ResponseCatalogUpload res =  (ResponseCatalogUpload)doPostCallWithAttachmentXML("/rest/xml/catalog/upload", req, "xsd", xsd, ResponseCatalogUpload.class);
			catalogGet = res.getCatalogGet();
			assert(0 == res.getReturnCode());
			System.out.println("+++++++++++++++++++++++++Upload catalog ok ");
			System.out.println("+++++++++++++++++++++++++id = " + res.getId());
			System.out.println(" catalogGet = " + res.getCatalogGet());
			idC=res.getId();
		}
		{
			RequestCatalogSearch req = new RequestCatalogSearch();
			req.setId(idC);
			ResponseCatalogSearch res =  (ResponseCatalogSearch)doPostCallBasicXML("/rest/xml/catalog/search", req, ResponseCatalogSearch.class);
			assert(0 == res.getReturnCode());
			assert(1 == res.getResult().size());
			System.out.println("+++++++++++++++++++++++++Search catalog ok ");
		}
		{
			RequestCatalogDownload req = new RequestCatalogDownload();
			req.setId(idC);
			byte[] res =  doPostCallReturningResourceXML("/rest/xml/catalog/download", req);
			assert(true == (res.length>0));
			System.out.println("+++++++++++++++++++++++++Download catalog ok ");
		}
		{
			byte[] res =  doGetCallReturningResource(catalogGet);
			assert(true==(res.length>0));
			System.out.println("+++++++++++++++++++++++++Get catalog ok ");
		}
		{
			RequestCatalogDelete req = new RequestCatalogDelete();
			req.setId(idC);
			ResponseCatalogDelete res =  (ResponseCatalogDelete)doPostCallBasicXML("/rest/xml/catalog/delete", req, ResponseCatalogDelete.class);
			assert(0 == res.getReturnCode());
			System.out.println("+++++++++++++++++++++++++Delete catalog ok ");
		}
	}

}
