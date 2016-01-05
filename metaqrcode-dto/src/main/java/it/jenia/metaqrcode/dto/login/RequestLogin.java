package it.jenia.metaqrcode.dto.login;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import it.jenia.metaqrcode.dto.Request;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
/**
 * by using given email and password, using this request you can login into metaqrcode server
 * 
 * @author andreatessaro
 *
 */
public class RequestLogin extends Request {
	
	@Getter
	@Setter
	/**
	 * email of the user that is logging in
	 */
	private String email;

	@Getter
	@Setter
	/**
	 * login passowrd
	 */
	private String password;

	@Getter
	@Setter
	/**
	 * clientId
	 */
	private String clientId;

}
