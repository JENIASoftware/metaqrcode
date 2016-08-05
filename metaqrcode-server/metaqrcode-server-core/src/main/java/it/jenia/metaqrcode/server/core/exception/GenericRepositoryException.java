package it.jenia.metaqrcode.server.core.exception;

import it.jenia.metaqrcode.server.commons.exception.MetaqrcodeException;

public class GenericRepositoryException extends MetaqrcodeException {

	private static final long serialVersionUID = 1L;

	private static final int BASE_RETURN_CODE = -113000;

	public GenericRepositoryException(int returnCode) {
		super(BASE_RETURN_CODE-returnCode);
	}

	public GenericRepositoryException(int returnCode, String message) {
		super(BASE_RETURN_CODE-returnCode, message);
	}

	public GenericRepositoryException(int returnCode, Throwable cause) {
		super(BASE_RETURN_CODE-returnCode, cause);
	}

	public GenericRepositoryException(int returnCode, String message, Throwable cause) {
		super(BASE_RETURN_CODE-returnCode, message, cause);
	}

	public GenericRepositoryException(int returnCode, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(BASE_RETURN_CODE-returnCode, message, cause, enableSuppression, writableStackTrace);
	}

}
