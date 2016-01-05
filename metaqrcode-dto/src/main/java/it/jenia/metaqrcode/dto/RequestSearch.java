package it.jenia.metaqrcode.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
/**
 * generic search request
 * 
 * @author andreatessaro
 *
 */
public class RequestSearch extends Request {

	@Getter
	@Setter
	/**
	 * number of page requested (zero based)
	 */
	private Integer pageNumber;
	
	@Getter
	@Setter
	/**
	 * number of row for every page
	 */
	private Integer rowPerPage;
	
}
