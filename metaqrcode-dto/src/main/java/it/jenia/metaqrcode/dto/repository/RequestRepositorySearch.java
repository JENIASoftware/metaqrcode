package it.jenia.metaqrcode.dto.repository;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import it.jenia.metaqrcode.dto.RequestSearch;
import it.jenia.metaqrcode.dto.adapter.AutotrimAdapter;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
/**
 * search for uploaded repository entries (XML)
 * 
 * @author andreatessaro
 *
 */
public class RequestRepositorySearch extends RequestSearch {
	
	@Getter
	@Setter
	/**
	 * the id of the catalog entry (XSD) to be searched for (optional)
	 */
	private BigInteger catalogEntryId;

	@Getter
	@Setter
	/**
	 * the id of the repository entry (XML) to be searched for (optional)
	 */
	private BigInteger id;

	@Getter
	@Setter
	@XmlJavaTypeAdapter(AutotrimAdapter.class)
	/**
	 * like expression for the correlationId of the repository entry (XML) to be searched for
	 */
	private String correlationIdLike;

}
