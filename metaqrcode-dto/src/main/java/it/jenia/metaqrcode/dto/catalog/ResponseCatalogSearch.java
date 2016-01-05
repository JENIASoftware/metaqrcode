package it.jenia.metaqrcode.dto.catalog;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import it.jenia.metaqrcode.dto.ResponseSearch;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
/**
 * result of the search request
 * 
 * @author andreatessaro
 *
 */
public class ResponseCatalogSearch extends ResponseSearch {
	
	@Getter
	@Setter
	/**
	 * list of catalog entries (XSD) that match search filters
	 */
	private List<CatalogEntryDto> result;

}
