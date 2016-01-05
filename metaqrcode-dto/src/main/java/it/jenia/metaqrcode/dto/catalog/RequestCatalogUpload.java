package it.jenia.metaqrcode.dto.catalog;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import it.jenia.metaqrcode.dto.Request;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
/**
 * catalog entry (XSD) upload request.
 * 
 * @author andreatessaro
 *
 */
public class RequestCatalogUpload extends Request {
	
	@Getter
	@Setter
	/**
	 * the name of the catalog entry (XSD) that is uploading
	 */
	private String name;

	@Getter
	@Setter
	/**
	 * description of the catalog entry (XSD) that is uploading
	 */
	private String description;

}
