package it.jenia.metaqrcode.server.core.util.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.jenia.metaqrcode.server.core.util.AuthenticationService;
import it.jenia.metaqrcode.server.entities.authentication.User;
import it.jenia.metaqrcode.server.repositories.authentication.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Service(value="metaqrcode-server-core-AuthenticationServiceImpl")
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
	
	@Autowired
	private UserRepository userRepository;	

	@Override
	public User getCurrentUser() {
		if (log.isDebugEnabled()) {
			log.debug("returning current user entity");
		}
		SecurityContext ctx = SecurityContextHolder.getContext();
		if (ctx != null && ctx.getAuthentication() != null) {
			Authentication auth = ctx.getAuthentication();
			if (auth.getDetails() instanceof User) {
				return ((User) auth.getDetails());
			}
		}
		return null;
	}

	@Override
	public User getUserByEmail(String email) {
		User user = userRepository.searchByEmail(email);
		if (user == null) {
			if (log.isErrorEnabled()) {
				log.error("username not registered : " + email);
			}
			throw new UsernameNotFoundException("username not registered : " + email);
		}
		return user;
	}

}
