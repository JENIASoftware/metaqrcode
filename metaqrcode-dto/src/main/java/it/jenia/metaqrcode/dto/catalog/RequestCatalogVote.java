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
 * request to vote for a catalog entry (XSD)
 * 
 * @author andreatessaro
 *
 */
public class RequestCatalogVote extends Request {
	
	@Getter
	@Setter
	/**
	 * the id of the catalog entry (XSD) to be voted from the metaqrcode server
	 */
	private BigInteger id;

	@Getter
	@Setter
	/**
	 * number of stars (quality) assigned from the user to this catalog entry (XSD)
	 */
	private double stars;

	@Getter
	@Setter
	/**
	 * comment of the user to this catalog entry (XSD)
	 */
	private String note;
}
