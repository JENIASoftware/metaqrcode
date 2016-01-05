package it.jenia.metaqrcode.dto.registration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import it.jenia.metaqrcode.dto.Request;
import it.jenia.metaqrcode.dto.adapter.AutotrimAdapter;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
/**
 * request for user registration prepare.
 * After calling this request a mail will be sent to the user with an activation code.
 * After received activation code you need to use RequestRegistrationConfirm to activate user
 * 
 * @author andreatessaro
 *
 */
public class RequestRegistrationPrepare extends Request {
	
	@Getter
	@Setter
	@XmlJavaTypeAdapter(AutotrimAdapter.class)
	/**
	 * user email
	 */
	private String email;

	@Getter
	@Setter
	@XmlJavaTypeAdapter(AutotrimAdapter.class)
	/**
	 * user password
	 */
	private String password;

	@Getter
	@Setter
	@XmlJavaTypeAdapter(AutotrimAdapter.class)
	/**
	 * user nickname
	 */
	private String nickName;

	@Getter
	@Setter
	@XmlJavaTypeAdapter(AutotrimAdapter.class)
	/**
	 * user first name
	 */
	private String firstName;

	@Getter
	@Setter
	@XmlJavaTypeAdapter(AutotrimAdapter.class)
	/**
	 * user last name
	 */
	private String lastName;

	@Getter
	@Setter
	@XmlJavaTypeAdapter(AutotrimAdapter.class)
	/**
	 * user address
	 */
	private String address;

	@Getter
	@Setter
	@XmlJavaTypeAdapter(AutotrimAdapter.class)
	/**
	 * user city
	 */
	private String city;

	@Getter
	@Setter
	@XmlJavaTypeAdapter(AutotrimAdapter.class)
	/**
	 * user zip code
	 */
	private String zipCode;
	
	@Getter
	@Setter
	@XmlJavaTypeAdapter(AutotrimAdapter.class)
	/** 
	 * user preferred language
	 */
	private String preferredLanguage;

}
