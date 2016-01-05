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
 * this request will unregister the user
 * 
 * @author andreatessaro
 *
 */
public class RequestUnregister extends Request {
	
	@Getter
	@Setter
	@XmlJavaTypeAdapter(AutotrimAdapter.class)
	/** 
	 * email of the user that will be unregistered
	 */
	private String email;

}
