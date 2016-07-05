package it.jenia.metaqrcode.enterprise.dto.login;

import it.jenia.metaqrcode.dto.Response;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
/**
 * each login create a user session.
 * each request that need authentication need to pass the sessionToken returned by the login service.
 * sessionToken is used (on server side) to understand wich is the user logged in.
 * sessionToken has a timeout, pleas check metaqrcode configuration parameter to understand the timeout (default 1 hour)
 * 
 * @author andreatessaro
 *
 */
public class ResponseLogin extends Response {
	
	@Getter
	@Setter
	/**
	 * the sessionToken created for this login request. default timeout : 1 hour
	 */
	private String sessionToken;

}
