package it.jenia.metaqrcode.dto.catalog;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import it.jenia.metaqrcode.dto.Response;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
/**
 * result of the detail request
 * 
 * @author andreatessaro
 *
 */
public class ResponseCatalogDetail extends Response {
	
	@Getter
	@Setter
	/**
	 * catalog entries (XSD) detail
	 */
	private CatalogEntryDto catalogEntry;

}
