package it.jenia.metaqrcode.dto.registration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import it.jenia.metaqrcode.dto.Request;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
/**
 * if the user forgotten the password, using this request a mail with the current password will be sent to the current user
 * 
 * @author andreatessaro
 *
 */
public class RequestPasswordForgotten extends Request {

}
