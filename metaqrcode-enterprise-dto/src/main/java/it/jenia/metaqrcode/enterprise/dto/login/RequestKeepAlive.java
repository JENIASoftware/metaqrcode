package it.jenia.metaqrcode.enterprise.dto.login;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import it.jenia.metaqrcode.dto.Request;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
/**
 * If your application need to have a longer session that defined in metaqrcode server parameters, you can use keep alive service to have a longer login session.
 * Each keepAlive will reset sessionToken timeout   
 * 
 * @author andreatessaro
 *
 */
public class RequestKeepAlive extends Request {
	
}
