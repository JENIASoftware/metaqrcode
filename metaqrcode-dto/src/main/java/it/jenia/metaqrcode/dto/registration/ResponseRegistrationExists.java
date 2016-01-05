package it.jenia.metaqrcode.dto.registration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import it.jenia.metaqrcode.dto.Response;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
/**
 * user information about currently logged in user
 * 
 * @author andreatessaro
 *
 */
public class ResponseRegistrationExists extends Response {
	
	@Getter
	@Setter
	/**
	 * user email
	 */
	private String email;

	@Getter
	@Setter
	/**
	 * user exists?
	 */
	private boolean exists;

}
