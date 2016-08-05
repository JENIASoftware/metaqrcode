package it.jenia.metaqrcode.server.commons.exception;

import lombok.Getter;

/**
 * common generic metaqrcode exception (include return code)
 * 
 * @author andreatessaro
 *
 */
public class MetaqrcodeException extends Exception {

	private static final long serialVersionUID = 1L;

	@Getter
	private int returnCode;
	
	public MetaqrcodeException(int returnCode) {
		this.returnCode = returnCode;
	}

	public MetaqrcodeException(int returnCode, String message) {
		super(message);
		this.returnCode = returnCode;
	}

	public MetaqrcodeException(int returnCode, Throwable cause) {
		super(cause);
		this.returnCode = returnCode;
	}

	public MetaqrcodeException(int returnCode, String message, Throwable cause) {
		super(message, cause);
		this.returnCode = returnCode;
	}

	public MetaqrcodeException(int returnCode, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.returnCode = returnCode;
	}

}
