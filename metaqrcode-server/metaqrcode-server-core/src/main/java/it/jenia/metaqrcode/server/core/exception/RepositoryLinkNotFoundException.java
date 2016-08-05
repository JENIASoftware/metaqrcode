package it.jenia.metaqrcode.server.core.exception;

import it.jenia.metaqrcode.server.commons.exception.MetaqrcodeException;

public class RepositoryLinkNotFoundException extends MetaqrcodeException {

	private static final long serialVersionUID = 1L;

	private static final int BASE_RETURN_CODE = -126000;

	public RepositoryLinkNotFoundException(int returnCode) {
		super(BASE_RETURN_CODE-returnCode);
	}

	public RepositoryLinkNotFoundException(int returnCode, String message) {
		super(BASE_RETURN_CODE-returnCode, message);
	}

	public RepositoryLinkNotFoundException(int returnCode, Throwable cause) {
		super(BASE_RETURN_CODE-returnCode, cause);
	}

	public RepositoryLinkNotFoundException(int returnCode, String message, Throwable cause) {
		super(BASE_RETURN_CODE-returnCode, message, cause);
	}

	public RepositoryLinkNotFoundException(int returnCode, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(BASE_RETURN_CODE-returnCode, message, cause, enableSuppression, writableStackTrace);
	}

}
