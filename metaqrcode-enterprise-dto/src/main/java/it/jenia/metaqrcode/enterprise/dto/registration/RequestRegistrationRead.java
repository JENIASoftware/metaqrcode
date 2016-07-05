package it.jenia.metaqrcode.enterprise.dto.registration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import it.jenia.metaqrcode.dto.Request;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
/**
 * this request will return user information of currently logged in user
 * 
 * @author andreatessaro
 *
 */
public class RequestRegistrationRead extends Request {

}
