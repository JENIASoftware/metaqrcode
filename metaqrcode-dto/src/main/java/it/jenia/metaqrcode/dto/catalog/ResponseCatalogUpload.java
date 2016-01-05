package it.jenia.metaqrcode.dto.catalog;

import it.jenia.metaqrcode.dto.Response;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
/**
 * result of the upload request.
 * 
 * @author andreatessaro
 *
 */
public class ResponseCatalogUpload extends Response {
	
	@Getter
	@Setter
	/**
	 * the id of the catalog entry (XSD) assigned from the metaqrcode server
	 */
	private BigInteger id;

	@Getter
	@Setter
	/**
	 * the URL to be used to refer (http get) the catalog entry (XSD)
	 */
	private String catalogGet;

}
