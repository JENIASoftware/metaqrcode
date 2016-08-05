package it.jenia.metaqrcode.server.core.util;

import java.io.InputStream;
import java.io.Reader;

import org.w3c.dom.ls.LSInput;

import lombok.Getter;
import lombok.Setter;

public class DefaultLSInput implements LSInput {

	@Getter
	@Setter
	private String publicId;

	@Getter
	@Setter
	private String systemId;

	@Getter
	@Setter
	private String baseURI;

	@Getter
	@Setter
	private InputStream byteStream;

	@Getter
	@Setter
	private Reader characterStream;

	@Getter
	@Setter
	private String stringData;

	@Getter
	@Setter
	private String encoding;

	@Setter
	private boolean certifiedText;

	public DefaultLSInput() {
	}

	public DefaultLSInput(String publicId, String systemId, InputStream byteStream) {
		this.publicId = publicId;
		this.systemId = systemId;
		this.byteStream = byteStream;
	}

	@Override
	public boolean getCertifiedText() {
		return certifiedText;
	}

}
