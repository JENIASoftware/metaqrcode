package it.jenia.metaqrcode.dto.repository;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import it.jenia.metaqrcode.dto.Response;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
/**
 * result of the detail request
 * 
 * @author andreatessaro
 *
 */
public class ResponseRepositoryDetail extends Response {
	
	@Getter
	@Setter
	/**
	 * repository entries (XML) detail 
	 */
	private RepositoryEntryDto repositoryEntry;

}
