/**
 * 
 */
package br.com.cams7.gae.security;

import java.util.EnumSet;
import java.util.Set;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import br.com.cams7.app.domain.entity.UserEntity;
import br.com.cams7.app.domain.entity.UserEntity.Role;
import br.com.cams7.gae.service.UserService;

import com.google.appengine.api.users.User;

/**
 * @author cams7
 *
 */
public class GoogleAccountsAuthenticationProvider implements
		AuthenticationProvider, MessageSourceAware {

	protected MessageSourceAccessor messages = SpringSecurityMessageSource
			.getAccessor();

	private UserService service;

	public GoogleAccountsAuthenticationProvider() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.authentication.AuthenticationProvider#
	 * authenticate(org.springframework.security.core.Authentication)
	 */
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		User googleUser = (User) authentication.getPrincipal();
		String googleId = googleUser.getUserId();

		UserEntity user = service.findByGoogleId(googleId);

		if (user == null) {
			user = new UserEntity();
			user.setGoogleId(googleId);
			user.setEmail(googleUser.getEmail());
			user.setEnabled(true);

			Set<Role> authorities = EnumSet.of(Role.ROLE_NEWUSER);

			user.setAuthorities(authorities);
		}

		if (!user.isEnabled())
			throw new DisabledException("Account is disabled");

		UserAuthentication userAuthentication = new UserAuthentication(user,
				authentication.getDetails());

		return userAuthentication;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.authentication.AuthenticationProvider#supports
	 * (java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return PreAuthenticatedAuthenticationToken.class
				.isAssignableFrom(authentication);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.context.MessageSourceAware#setMessageSource(org.
	 * springframework.context.MessageSource)
	 */
	@Override
	public void setMessageSource(MessageSource messageSource) {
		messages = new MessageSourceAccessor(messageSource);
	}

	public void setService(UserService service) {
		this.service = service;
	}

}
