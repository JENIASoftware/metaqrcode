package it.jenia.metaqrcode.server.authentication.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import it.jenia.metaqrcode.server.commons.constants.Constants;
import it.jenia.metaqrcode.server.core.util.AuthenticationService;
import it.jenia.metaqrcode.server.entities.authentication.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticationFilter extends OncePerRequestFilter {
	
	private static final String ADMIN = "admin";
	@Autowired
	private AuthenticationService authenticationService;

	/**
	 * authentication method. This method will keep the request, verify the sessionToken and retrieve the user.
	 * This method also verify if the sessionToken is expired
	 * 
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		// in the request is already secured (security contexy is present) do nothing
		// in caso di qualsiasi problema si DEVE per forza creare un utente fake con l'autorizzazione ROLE_ANONYMOUS
		// il controllo di sicurezza (profilazione) viene fatto con annotazioni nei servizi rest
		// CXF controlla il ruolo, se non è presente gestisce errore.
		if (!request.getMethod().equals("OPTIONS")) {
			if (log.isDebugEnabled()) log.debug("http request received, retriving security context");
			SecurityContext ctx = SecurityContextHolder.getContext();
			if (ctx.getAuthentication() == null) {
				if (log.isErrorEnabled()) log.error("generating generic authentication credentials");
				generateGenericAuthentication(ctx);
			}
		} else {
			if (log.isDebugEnabled()) log.debug("http request OPTIONS received, no security applied");
		}
		filterChain.doFilter(request, response);
	}
	
    /**
	 * create fake user with anonymous user only
	 * 
	 * @param ctx
	 */
	private void generateGenericAuthentication(SecurityContext ctx) {
		if (log.isDebugEnabled()) log.debug("token is not present, trying with admin user");
		List<GrantedAuthority> autorities = new ArrayList<GrantedAuthority>();
		GrantedAuthority auth_anonymous = new SimpleGrantedAuthority(Constants.ROLE_ANONYMOUS);
		autorities.add(auth_anonymous);
		GrantedAuthority auth_admin = new SimpleGrantedAuthority(Constants.ROLE_ADMIN);
		autorities.add(auth_admin);
		GrantedAuthority auth_user = new SimpleGrantedAuthority(Constants.ROLE_USER);
		autorities.add(auth_user);
	
		// the password passed to principal is the chiphered one
		MetaqrcodeUserPrincipal currentSpringUser = new MetaqrcodeUserPrincipal(ADMIN, ADMIN, autorities);
		User user = authenticationService.getUserByEmail(ADMIN);
		PreAuthenticatedAuthenticationToken auth = new PreAuthenticatedAuthenticationToken(currentSpringUser, user, autorities);
		auth.setDetails(user);
		auth.setAuthenticated(true);
		ctx.setAuthentication(auth);
		if (log.isDebugEnabled()) log.debug("spring security initialized (admin)");
	}
}