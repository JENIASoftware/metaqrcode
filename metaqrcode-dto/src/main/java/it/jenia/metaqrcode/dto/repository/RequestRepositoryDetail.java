package it.jenia.metaqrcode.dto.repository;

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
 * request to get a repository entry (XML) detail
 * 
 * @author andreatessaro
 *
 */
public class RequestRepositoryDetail extends Request {
	
	@Getter
	@Setter
	/**
	 * the id of the repository entry (XML) to be detailed
	 */
	private BigInteger id;

}
