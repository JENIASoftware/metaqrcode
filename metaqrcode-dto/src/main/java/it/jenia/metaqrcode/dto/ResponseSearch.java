package it.jenia.metaqrcode.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
/**
 * generic result for all search request
 * 
 * @author andreatessaro
 *
 */
public class ResponseSearch extends Response {

	@Getter
	@Setter
	/**
	 * total number of rows
	 */
	private Long rowTotal;
	
	@Getter
	@Setter
	/**
	 * current page
	 */
	private Integer currentPageNumber;
	
	@Getter
	@Setter
	/**
	 * total number of pages
	 */
	private Integer pageTotal;
	
}
