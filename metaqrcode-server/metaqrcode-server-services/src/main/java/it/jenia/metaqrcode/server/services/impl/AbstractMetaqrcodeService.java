package it.jenia.metaqrcode.server.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import it.jenia.metaqrcode.server.commons.utils.HttpUtils;
import lombok.Getter;

/**
 * basic service implementation. Expose various utility methods.
 * All metaqrcode generic service implementation MUST extend this abstract service
 * 
 * @author andreatessaro
 *
 */
public abstract class AbstractMetaqrcodeService {
	
	@Getter
	@Autowired
	private HttpUtils httpUtils;

}
