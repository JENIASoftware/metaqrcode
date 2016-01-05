package it.jenia.metaqrcode.dto.repository;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import it.jenia.metaqrcode.dto.Request;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
/**
 * request to download the qrcode (PNG) of a repository entry (XML)
 * 
 * @author andreatessaro
 *
 */
public class RequestRepositoryQRCode extends Request {
	
	@Getter
	@Setter
	/**
	 * the id of the repository entry (XML) of the qrcode to be downloaded
	 */
	private BigInteger id;

}
