package it.jenia.metaqrcode.dto.catalog;

import it.jenia.metaqrcode.dto.Dto;

import java.math.BigInteger;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
/**
 * this is a catalog entry (XSD)
 * 
 * @author andreatessaro
 *
 */
public class CatalogEntryDto extends Dto {

	@Getter
	@Setter
	/**
	 * catalog entry (XSD) id (the unique id of this XSD)
	 */
	private BigInteger id;

	@Getter
	@Setter
	/**
	 * name of the catalog entry (XSD)
	 */
	private String name;

	@Getter
	@Setter
	/**
	 * description of the catalog entry (XSD)
	 */
	private String description;

	@Getter
	@Setter
	/**
	 * url of the catalog entry (XSD) to retrieve it on metaqrcode server
	 */
	private String catalogGet;

	@Getter
	@Setter
	/**
	 * list of catalog entry (XSD) that this repository entry (XML) is referring to
	 */
	private List<CatalogEntryDto> catalogEntries;

	@Getter
	@Setter
	/**
	 * nickname of the publisher of this catalog entry (XSD)
	 */
	private String nickNamePublisher;

	@Getter
	@Setter
	/**
	 * number of repository entry (XML) that refers to this catalog entry (XSD)
	 */
	private BigInteger numberOfReferences;

	@Getter
	@Setter
	/**
	 * number of stars (quality) assigned from the users to this catalog entry (XSD)
	 */
	private double stars;

}
