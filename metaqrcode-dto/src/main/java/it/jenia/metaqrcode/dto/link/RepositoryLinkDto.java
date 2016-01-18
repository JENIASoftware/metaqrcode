package it.jenia.metaqrcode.dto.link;

import it.jenia.metaqrcode.dto.Dto;
import it.jenia.metaqrcode.dto.adapter.AutotrimAdapter;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
/**
 * this is a link to a repository entry (XML)
 * 
 * @author andreatessaro
 *
 */
public class RepositoryLinkDto extends Dto {

	@Getter
	@Setter
	/**
	 * id of the link
	 */
	private BigInteger id;

	@Getter
	@Setter
	@XmlJavaTypeAdapter(AutotrimAdapter.class)
	/**
	 * other code that is referring to the repository entry (XML)
	 */
	private String otherCode;

	@Getter
	@Setter
	@XmlJavaTypeAdapter(AutotrimAdapter.class)
	/**
	 * url of the link to retrieve it on metaqrcode server
	 */
	private String linkGet;

}
