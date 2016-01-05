package it.jenia.metaqrcode.dto.registration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import it.jenia.metaqrcode.dto.Request;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
/**
 * this request will check if a user exists
 * 
 * @author andreatessaro
 *
 */
public class RequestRegistrationExists extends Request {

	@Getter
	@Setter
	/**
	 * user email
	 */
	private String email;

}
