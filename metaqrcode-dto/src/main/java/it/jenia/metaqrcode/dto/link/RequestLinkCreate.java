package it.jenia.metaqrcode.dto.link;

import java.math.BigInteger;

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
 * request to bind (link) an existing barcode or qrcode to a previously generated repository entry (XML)
 * 
 * @author andreatessaro
 *
 */
public class RequestLinkCreate extends Request {
	
	@Getter
	@Setter
	/**
	 * the id of the repository entry (XML) object of this link
	 */
	private BigInteger repositoryId;

	@Getter
	@Setter
	@XmlJavaTypeAdapter(AutotrimAdapter.class)
	/**
	 * the "other" barcode or qrcode to be binded to existing repository entry (XML)
	 */
	private String otherCode;

}
