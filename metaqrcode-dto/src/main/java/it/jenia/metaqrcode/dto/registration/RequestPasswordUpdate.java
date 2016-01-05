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
 * use this request to change the password of the currently logged in user 
 * 
 * @author andreatessaro
 *
 */
public class RequestPasswordUpdate extends Request {
	
	@Getter
	@Setter
	@XmlJavaTypeAdapter(AutotrimAdapter.class)
	/**
	 * new password for current user
	 */
	private String password;

}
