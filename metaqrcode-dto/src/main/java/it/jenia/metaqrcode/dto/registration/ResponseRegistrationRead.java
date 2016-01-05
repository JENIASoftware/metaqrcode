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
public class ResponseRegistrationRead extends Response {
	
	@Getter
	@Setter
	/**
	 * user email
	 */
	private String email;

	@Getter
	@Setter
	/**
	 * user nickname
	 */
	private String nickName;

	@Getter
	@Setter
	/**
	 * user first name
	 */
	private String firstName;

	@Getter
	@Setter
	/**
	 * user last name
	 */
	private String lastName;

	@Getter
	@Setter
	/**
	 * user address
	 */
	private String address;

	@Getter
	@Setter
	/**
	 * user city
	 */
	private String city;

	@Getter
	@Setter
	/**
	 * user zipcode
	 */
	private String zipCode;
	
	@Getter
	@Setter
	/**
	 * user preferred language
	 */
	private String preferredLanguage;

}
