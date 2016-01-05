package it.jenia.metaqrcode.dto.link;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import it.jenia.metaqrcode.dto.Response;
import it.jenia.metaqrcode.dto.repository.RepositoryEntryDto;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
/**
 * result of the detail link request
 * 
 * @author andreatessaro
 *
 */
public class ResponseLinkDetail extends Response {
	
	@Getter
	@Setter
	/**
	 * reposiotry entries (XSD) detail
	 */
	private RepositoryEntryDto repositoryEntry;

}
