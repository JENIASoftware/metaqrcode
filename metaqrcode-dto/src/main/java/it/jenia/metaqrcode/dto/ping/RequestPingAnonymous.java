package it.jenia.metaqrcode.dto.ping;

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
 * 
 * Request for a ping service. You have to specify the from attribute to have back a result.
 * This service will only return a fake result, something like an hello world.
 * You don't need to be logged in to use this request
 * 
 * @author andreatessaro
 *
 */
public class RequestPingAnonymous extends Request {
	
	@Getter
	@Setter
	@XmlJavaTypeAdapter(AutotrimAdapter.class)
	/**
	 * from attribute that will be returned in the result
	 */
	private String from;

}
