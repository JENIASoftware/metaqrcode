package it.jenia.metaqrcode.dto.repository;

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
 * request to update a previously generated catalog entry (XML)   
 * 
 * @author andreatessaro
 *
 */
public class RequestRepositoryUpdate extends Request {
	
	@Getter
	@Setter
	/**
	 * the id of the catalog entry (XML) to be updated
	 */
	private BigInteger id;

	@Getter
	@Setter
	@XmlJavaTypeAdapter(AutotrimAdapter.class)
	/**
	 * the new default catalog entry (XSD) that this repository entry (XML) is referring to 
	 */
	private String defaultCatalog;

	@Getter
	@Setter
	/** you can specify if this repository entry (XML) is private (visible only to you).
	 * Metaqrcode server will bind it to the repository entry (XML) for further processing
	 */
	private boolean personal;

}
