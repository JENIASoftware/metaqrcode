package it.jenia.metaqrcode.dto.link;

import it.jenia.metaqrcode.dto.Response;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
/**
 * result of the link operation
 * 
 * @author andreatessaro
 *
 */
public class ResponseLinkCreate extends Response {
	
	@Getter
	@Setter
	/**
	 * the id of the link created
	 */
	private BigInteger id;

	@Getter
	@Setter
	/**
	 * url of the link to retrieve it on metaqrcode server
	 */
	private String linkGet;

}
