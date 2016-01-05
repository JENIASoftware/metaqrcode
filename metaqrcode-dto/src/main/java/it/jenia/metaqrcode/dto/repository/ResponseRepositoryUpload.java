package it.jenia.metaqrcode.dto.repository;

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
 * repository entry (XML) upload result
 * 
 * @author andreatessaro
 *
 */
public class ResponseRepositoryUpload extends Response {
	
	@Getter
	@Setter
	/**
	 * the generated id for this repository entry (XML)
	 */
	private BigInteger id;

	@Getter
	@Setter
	/**
	 * url of the repository entry (XML) to retrieve it on metaqrcode server
	 */
	private String repositoryGet;

	@Getter
	@Setter
	/**
	 * url of the image (PNG) of the qrcode of this repository entry (XML)
	 */
	private String qrcodeGet;

}
