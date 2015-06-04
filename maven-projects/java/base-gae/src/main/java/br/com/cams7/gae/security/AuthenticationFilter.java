/**
 * 
 */
package br.com.cams7.gae.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.GenericFilterBean;

import br.com.cams7.app.domain.entity.UserEntity;
import br.com.cams7.app.domain.entity.UserEntity.Role;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * @author cams7
 *
 */
public class AuthenticationFilter extends GenericFilterBean {
	private static final String REGISTRATION_URL = "/cadastrarLogin";

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	private final AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> ads = new WebAuthenticationDetailsSource();

	private AuthenticationManager authenticationManager;
	private AuthenticationFailureHandler failureHandler;

	/**
	 * 
	 */
	public AuthenticationFilter() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		User googleUser = UserServiceFactory.getUserService().getCurrentUser();

		if (authentication != null
				&& !loggedInUserMatchesGaeUser(authentication, googleUser)) {
			SecurityContextHolder.clearContext();
			((HttpServletRequest) request).getSession().invalidate();
		}

		if (authentication == null) {
			if (googleUser != null) {
				LOGGER.debug("Currently logged on to GAE as user " + googleUser);
				LOGGER.debug("Authenticating to Spring Security");
				// User has returned after authenticating via GAE. Need to
				// authenticate
				// through Spring Security.
				PreAuthenticatedAuthenticationToken token = new PreAuthenticatedAuthenticationToken(
						googleUser, null);
				token.setDetails(ads.buildDetails((HttpServletRequest) request));

				try {
					authentication = authenticationManager.authenticate(token);
					SecurityContextHolder.getContext().setAuthentication(
							authentication);

					Collection<? extends GrantedAuthority> authorities = authentication
							.getAuthorities();
					if (authorities.contains(Role.ROLE_NEWUSER)) {
						LOGGER.debug("New user authenticated. Redirecting to registration page");

						((HttpServletResponse) response)
								.sendRedirect(REGISTRATION_URL);

						return;
					}

				} catch (AuthenticationException e) {
					failureHandler.onAuthenticationFailure(
							(HttpServletRequest) request,
							(HttpServletResponse) response, e);

					return;
				}
			}
		}

		chain.doFilter(request, response);

	}

	private boolean loggedInUserMatchesGaeUser(Authentication authentication,
			User googleUser) {
		if (googleUser == null)
			// User has logged out of GAE but is still logged into application
			return false;

		UserEntity user = (UserEntity) authentication.getPrincipal();

		if (!user.getEmail().equals(googleUser.getEmail()))
			return false;

		return true;

	}

	public void setAuthenticationManager(
			AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public void setFailureHandler(AuthenticationFailureHandler failureHandler) {
		this.failureHandler = failureHandler;
	}

}
