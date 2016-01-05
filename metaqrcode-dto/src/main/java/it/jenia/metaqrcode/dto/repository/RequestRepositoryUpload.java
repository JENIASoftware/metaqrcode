package it.jenia.metaqrcode.dto.repository;

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
 * request to upload a repository entry
 * 
 * @author andreatessaro
 *
 */
public class RequestRepositoryUpload extends Request {
	
	@Getter
	@Setter
	/**
	 * you can specify a default catalog entry (XSD) for the uploading repository entry (XML).
	 * In alternative you can insert catalog entry (XSD) references inside the repository entry (XML) 
	 */
	private String defaultCatalog;

	@Getter
	@Setter
	@XmlJavaTypeAdapter(AutotrimAdapter.class)
	/**
	 * you can specify your application unique identifier for this repository entry (XML).
	 * Metaqrcode server will bind it to the repository entry (XML) for further processing
	 */
	private String correlationId;

}
