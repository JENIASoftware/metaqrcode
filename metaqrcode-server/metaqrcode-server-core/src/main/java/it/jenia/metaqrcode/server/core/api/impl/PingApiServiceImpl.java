package it.jenia.metaqrcode.server.core.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.jenia.metaqrcode.dto.ping.RequestPingAnonymous;
import it.jenia.metaqrcode.dto.ping.RequestPingAuthenticated;
import it.jenia.metaqrcode.dto.ping.ResponsePing;
import it.jenia.metaqrcode.server.commons.constants.Constants;
import it.jenia.metaqrcode.server.commons.exception.MetaqrcodeException;
import it.jenia.metaqrcode.server.core.api.PingApiService;
import it.jenia.metaqrcode.server.core.util.AuthenticationService;
import it.jenia.metaqrcode.server.repositories.authentication.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Service(value="metaqrcode-server-core-PingApiServiceImpl")
@Slf4j
public class PingApiServiceImpl implements PingApiService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationService authenticationService;

	@Override
	@Transactional(value = Constants.METAQRCODE_TRANSACTION_MANAGER, propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = MetaqrcodeException.class)
	public ResponsePing pingAnonymous(RequestPingAnonymous request) {
		try {
			if (log.isDebugEnabled()) log.debug("ping Anonymous called from " + request.getFrom());
			ResponsePing ret = new ResponsePing();
			userRepository.searchByEmail("admin");
			ret.setResult("ping Anonymous success from " + request.getFrom());
			if (log.isDebugEnabled()) log.debug("ping Anonymous : success");
			return ret;
		} catch (Exception e) {
			log.error("ping Anonymous : fail, "+e.getMessage(), e);
			throw e;
		}
	}

	@Override
	@Transactional(value = Constants.METAQRCODE_TRANSACTION_MANAGER, propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = MetaqrcodeException.class)
	public ResponsePing pingAuthenticated(RequestPingAuthenticated request) {
		try {
			if (log.isDebugEnabled()) log.debug("ping Authenticated called from " + request.getFrom() + ", current user : " + authenticationService.getCurrentUser().getEmail());
			ResponsePing ret = new ResponsePing();
			userRepository.searchByEmail("admin");
			ret.setResult("ping Authenticated success from " + request.getFrom());
			if (log.isDebugEnabled()) log.debug("ping Authenticated : success");
			return ret;
		} catch (Exception e) {
			log.error("ping Authenticated : fail, "+e.getMessage(), e);
			throw e;
		}
	}

}
