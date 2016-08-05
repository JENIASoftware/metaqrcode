package it.jenia.metaqrcode.server.core.api.test;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.junit.Test;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSResourceResolver;

public class ValidateXml {

	@Test
	public void a_test_ok() throws Exception {
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		StreamSource source = new StreamSource(new FileInputStream(new File("/jenia2.0/metaqrcode/workspace/metaqrcode/metaqrcode-server/metaqrcode-server-core/src/test/resources/it/jenia/metaqrcode/server/core/api/test/schema1.xsd")));
		Schema schema = schemaFactory.newSchema(source);
		// validazione xml schema
		
		Validator validator = schema.newValidator();
		validator.setResourceResolver(new LSResourceResolver() {

			@Override
			public LSInput resolveResource(String type, String namespaceURI,
					String publicId, String systemId, String baseURI) {
				System.out.println("done!");
				return null;
			}
			
		});
		validator.validate(new StreamSource(new FileInputStream(new File("/jenia2.0/metaqrcode/workspace/metaqrcode/metaqrcode-server/metaqrcode-server-core/src/test/resources/it/jenia/metaqrcode/server/core/api/test/schema1.xml"))));
		
		System.out.println("xml read ok");
	}

	@Test
	public void b_test_ko() throws Exception {
		try {
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			StreamSource source = new StreamSource(new FileInputStream(new File("/jenia2.0/metaqrcode/workspace/metaqrcode/metaqrcode-server/metaqrcode-server-core/src/test/resources/it/jenia/metaqrcode/server/core/api/test/schema1_different.xsd")));
			Schema schema = schemaFactory.newSchema(source);
			// validazione xml schema
			
			Validator validator = schema.newValidator();
			validator.setResourceResolver(new LSResourceResolver() {
	
				@Override
				public LSInput resolveResource(String type, String namespaceURI,
						String publicId, String systemId, String baseURI) {
					System.out.println("done!");
					return null;
				}
				
			});
			validator.validate(new StreamSource(new FileInputStream(new File("/jenia2.0/metaqrcode/workspace/metaqrcode/metaqrcode-server/metaqrcode-server-core/src/test/resources/it/jenia/metaqrcode/server/core/api/test/schema1_ko.err"))));
		} catch (Exception e) {		
			System.out.println("xml read ko as aspected");
		}
	}
	
}
