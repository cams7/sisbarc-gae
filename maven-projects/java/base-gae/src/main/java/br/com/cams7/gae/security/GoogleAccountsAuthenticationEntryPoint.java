/**
 * 
 */
package br.com.cams7.gae.security;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * @author cams7
 *
 */
public class GoogleAccountsAuthenticationEntryPoint implements
		AuthenticationEntryPoint {

	public static final String PREVIOUS_PAGE = "previousPage";

	/**
	 * 
	 */
	public GoogleAccountsAuthenticationEntryPoint() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.web.AuthenticationEntryPoint#commence(javax
	 * .servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.core.AuthenticationException)
	 */
	@Override
	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		UserService userService = UserServiceFactory.getUserService();
		String previousPage = request.getRequestURI();

		ServletContext context = request.getSession().getServletContext();
		context.setAttribute(PREVIOUS_PAGE, previousPage);

		response.sendRedirect(userService.createLoginURL(previousPage));
	}

}
