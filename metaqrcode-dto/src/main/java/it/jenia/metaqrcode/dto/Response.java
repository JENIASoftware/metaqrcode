package it.jenia.metaqrcode.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * Generic response.
 * All response returned by metaqrcode MUST extends this response
 * 
 * @author andreatessaro
 *
 */
public class Response extends Dto {
	
	private static final int SUCCESS_RETURN_CODE = 0;

	private static final String SUCCESS = "success";

	@Getter
	@Setter
	/**
	 * if different then zero, the service is KO
	 */
	private int returnCode;

	@Getter
	@Setter
	/**
	 * if returnCode is different then zero, this is the reason of the return code
	 */
	private String reason;

	public Response() {
		super();
		returnCode = SUCCESS_RETURN_CODE;
		reason = SUCCESS;
	}

}
