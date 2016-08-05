package it.jenia.metaqrcode.server.services.exception;

import it.jenia.metaqrcode.server.commons.exception.MetaqrcodeException;

/**
 * generic exception
 * 
 * @author andreatessaro
 *
 */
public class GenericException extends MetaqrcodeException {

	private static final long serialVersionUID = 1L;
	private static final int GENERIC_ERROR = -2;
	
	public GenericException() {
		super(GENERIC_ERROR);
	}

	public GenericException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(GENERIC_ERROR, message, cause, enableSuppression, writableStackTrace);
	}

	public GenericException(String message, Throwable cause) {
		super(GENERIC_ERROR, message, cause);
	}

	public GenericException(String message) {
		super(GENERIC_ERROR, message);
	}

	public GenericException(Throwable cause) {
		super(GENERIC_ERROR, cause);
	}

}
