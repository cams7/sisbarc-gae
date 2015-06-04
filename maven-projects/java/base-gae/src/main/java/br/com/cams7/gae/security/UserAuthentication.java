/**
 * 
 */
package br.com.cams7.gae.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import br.com.cams7.app.domain.entity.UserEntity;

/**
 * @author cams7
 *
 */
public class UserAuthentication implements Authentication {

	private static final long serialVersionUID = 1L;

	private final UserEntity principal;
	private final Object details;
	private boolean authenticated;

	public UserAuthentication(UserEntity principal, Object details) {
		super();

		this.principal = principal;
		this.details = details;

		authenticated = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.security.Principal#getName()
	 */
	@Override
	public String getName() {
		return principal.getEmail();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.Authentication#getAuthorities()
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<? extends GrantedAuthority> authorities = principal
				.getAuthorities();
		return authorities;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.Authentication#getCredentials()
	 */
	@Override
	public Object getCredentials() {
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.Authentication#getDetails()
	 */
	@Override
	public Object getDetails() {
		return details;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.Authentication#getPrincipal()
	 */
	@Override
	public Object getPrincipal() {
		return principal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.Authentication#isAuthenticated()
	 */
	@Override
	public boolean isAuthenticated() {
		return authenticated;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.core.Authentication#setAuthenticated(boolean
	 * )
	 */
	@Override
	public void setAuthenticated(boolean authenticated)
			throws IllegalArgumentException {
		this.authenticated = authenticated;
	}

	@Override
	public String toString() {
		return this.getClass().getName() + " [principal = " + getPrincipal()
				+ ", details = " + getDetails() + ", authenticated = "
				+ isAuthenticated() + "]";
	}

}
