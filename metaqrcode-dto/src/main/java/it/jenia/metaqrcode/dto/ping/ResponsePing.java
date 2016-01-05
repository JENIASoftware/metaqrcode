package it.jenia.metaqrcode.dto.ping;

import it.jenia.metaqrcode.dto.Response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
/**
 * 
 * All ping requests return this result. It's only a fake result
 * 
 * @author andreatessaro
 *
 */
public class ResponsePing extends Response {
	
	@Getter
	@Setter
	/** 
	 * the result of the ping request, something like an hello world
	 */
	private String result;

}
