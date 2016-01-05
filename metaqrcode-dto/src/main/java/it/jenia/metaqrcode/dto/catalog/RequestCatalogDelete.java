package it.jenia.metaqrcode.dto.catalog;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import it.jenia.metaqrcode.dto.Request;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
/**
 * request to delete for a catalog entry (XSD)
 * 
 * @author andreatessaro
 *
 */
public class RequestCatalogDelete extends Request {
	
	@Getter
	@Setter
	/**
	 * the id of the catalog entry (XSD) to be deleted from the metaqrcode server
	 */
	private BigInteger id;

}
