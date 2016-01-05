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
 * request to delete a repository entry (XML)
 * 
 * @author andreatessaro
 *
 */
public class RequestRepositoryDelete extends Request {
	
	@Getter
	@Setter
	/**
	 * the id of the repository entry (XML) that has to be deleted
	 */
	private BigInteger id;

}
