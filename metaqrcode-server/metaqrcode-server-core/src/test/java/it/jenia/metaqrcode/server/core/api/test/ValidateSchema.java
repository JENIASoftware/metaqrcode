package it.jenia.metaqrcode.server.core.api.test;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;

import org.junit.Test;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSResourceResolver;

public class ValidateSchema {

	@Test
	public void a_test_ok() throws Exception {
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		StreamSource source = new StreamSource(new FileInputStream(new File("/jenia2.0/metaqrcode/workspace/metaqrcode/metaqrcode-server/metaqrcode-server-core/src/test/resources/it/jenia/metaqrcode/server/core/api/test/schemaok.xsd")));
		schemaFactory.newSchema(source);
		System.out.println("schema read ok");
	}

	@Test
	public void b_test_ko() throws Exception {
		try {
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			schemaFactory.newSchema(new File("/jenia2.0/metaqrcode/workspace/metaqrcode/metaqrcode-server/metaqrcode-server-core/src/test/resources/it/jenia/metaqrcode/server/core/api/test/schemako.err"));
			throw new Exception("invalid schema");
		} catch (Exception e) {
			System.out.println("schema read fails as aspected");
		}
	}

	@Test
	public void c_test_ko() throws Exception {
		try {
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			schemaFactory.newSchema(new File("/jenia2.0/metaqrcode/workspace/metaqrcode/metaqrcode-server/metaqrcode-server-core/src/test/resources/it/jenia/metaqrcode/server/core/api/test/notfound"));
			throw new Exception("invalid schema");
		} catch (Exception e) {
			System.out.println("schema read fails as aspected");
		}
	}

	@Test
	public void d_test_ko() throws Exception {
		try {
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			schemaFactory.newSchema(new File("/jenia2.0/metaqrcode/workspace/metaqrcode/metaqrcode-server/metaqrcode-server-core/src/test/resources/it/jenia/metaqrcode/server/core/api/test/schemako_notxml.txt"));
			throw new Exception("invalid schema");
		} catch (Exception e) {
			System.out.println("schema read fails as aspected");
		}
	}

	@Test
	public void e_test_ko() throws Exception {
		try {
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			schemaFactory.newSchema(new File("/jenia2.0/metaqrcode/workspace/metaqrcode/metaqrcode-server/metaqrcode-server-core/src/test/resources/it/jenia/metaqrcode/server/core/api/test/schemako_notxsd.xml"));
			throw new Exception("invalid schema");
		} catch (Exception e) {
			System.out.println("schema read fails as aspected");
		}
	}
	
	@Test
	public void f_test_ok() throws Exception {
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		schemaFactory.setResourceResolver(new LSResourceResolver() {
			@Override
			public LSInput resolveResource(String type, String namespaceURI,
					String publicId, String systemId, String baseURI) {
				System.out.println("done! type=" + type + ", namespaceURI=" + namespaceURI + ", publicId=" + publicId + ", systemId=" + systemId + ", baseURI=" +baseURI);
				return null;
			}
		});
		schemaFactory.newSchema(new File("/jenia2.0/metaqrcode/workspace/metaqrcode/metaqrcode-server/metaqrcode-server-core/src/test/resources/it/jenia/metaqrcode/server/core/api/test/schemaok_import.xsd"));
		System.out.println("schema read ok");
	}


}
