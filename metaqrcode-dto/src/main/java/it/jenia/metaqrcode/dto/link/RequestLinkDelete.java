package it.jenia.metaqrcode.dto.link;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import it.jenia.metaqrcode.dto.Request;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
/**
 * request to delete a link to a repository entry (XML)
 * 
 * @author andreatessaro
 *
 */
public class RequestLinkDelete extends Request {
	
	@Getter
	@Setter
	/**
	 * the other code associated to the repository entry (XML)
	 */
	private String otherCode;

}
