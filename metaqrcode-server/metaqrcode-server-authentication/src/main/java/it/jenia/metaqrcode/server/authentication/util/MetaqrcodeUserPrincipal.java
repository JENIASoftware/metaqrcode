package it.jenia.metaqrcode.server.authentication.util;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;

public class MetaqrcodeUserPrincipal implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Getter
	private String username;

	@Getter
	private String userStoredPassword;

	@Getter
	private boolean enabled;

	@Getter
	private boolean credentialsNonExpired;

	@Getter
	private boolean accountNonLocked;

	@Getter
	private boolean accountNonExpired;

	@Getter
	private final Collection<GrantedAuthority> authorities;

	public MetaqrcodeUserPrincipal(String username, String userStoredPassword, Collection<GrantedAuthority> authorities) {
		super();
		accountNonExpired = true;
		accountNonLocked = true;
		credentialsNonExpired = true;
		enabled = true;
		this.authorities = authorities;
		this.username = username;
		this.userStoredPassword = userStoredPassword;
	}
	
	public String getPassword() {
		return userStoredPassword;
	}

}
