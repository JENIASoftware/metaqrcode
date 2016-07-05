package it.jenia.metaqrcode.enterprise.dto.registration;

import it.jenia.metaqrcode.dto.Response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
/**
 * if the registration prepare is ok, a mail will be sent.
 * User RequestRegistrationConfirm to activate user
 * 
 * @author andreatessaro
 *
 */
public class ResponseRegistrationPrepare extends Response {
	
}
