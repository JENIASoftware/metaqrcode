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
 * request to access to the specified catalog entry (XSD) details
 * 
 * @author andreatessaro
 *
 */
public class RequestCatalogDetail extends Request {
	
	@Getter
	@Setter
	/**
	 * the id of the catalog that will be detailed
	 */
	private BigInteger id;

}
