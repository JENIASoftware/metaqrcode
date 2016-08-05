package it.jenia.metaqrcode.server.core.exception;

import it.jenia.metaqrcode.server.commons.exception.MetaqrcodeException;

public class CatalogEntryNotFoundException extends MetaqrcodeException {

	private static final long serialVersionUID = 1L;

	private static final int BASE_RETURN_CODE = -108000;

	public CatalogEntryNotFoundException(int returnCode) {
		super(BASE_RETURN_CODE-returnCode);
	}

	public CatalogEntryNotFoundException(int returnCode, String message) {
		super(BASE_RETURN_CODE-returnCode, message);
	}

	public CatalogEntryNotFoundException(int returnCode, Throwable cause) {
		super(BASE_RETURN_CODE-returnCode, cause);
	}

	public CatalogEntryNotFoundException(int returnCode, String message, Throwable cause) {
		super(BASE_RETURN_CODE-returnCode, message, cause);
	}

	public CatalogEntryNotFoundException(int returnCode, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(BASE_RETURN_CODE-returnCode, message, cause, enableSuppression, writableStackTrace);
	}

}
