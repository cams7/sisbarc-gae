/**
 * 
 */
package br.com.cams7.gae.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.cams7.app.domain.entity.UserEntity;
import br.com.cams7.app.domain.entity.UserEntity.Role;
import br.com.cams7.gae.security.GoogleAccountsAuthenticationEntryPoint;
import br.com.cams7.gae.security.UserAuthentication;
import br.com.cams7.gae.service.UserService;

import com.google.appengine.api.users.UserServiceFactory;

/**
 * @author cams7
 *
 */
@Controller
public class LoginController {

	private final String ATTRIBUTE_ENTITY = "usuario";
	public static final String PAGE_LOGIN = "cadastrarLogin";

	private final String PAGE_LOGOUT = "logout";

	private final String PAGE_403 = "403";
	private final String PAGE_DISABLED = "disabled";

	@Autowired
	private UserService service;

	public LoginController() {
		super();
	}

	@RequestMapping(value = "/" + PAGE_LOGIN, method = RequestMethod.GET)
	public String criarForm(Model uiModel) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		UserEntity currentUser = (UserEntity) auth.getPrincipal();
		uiModel.addAttribute(ATTRIBUTE_ENTITY, currentUser);

		return PAGE_LOGIN;
	}

	@RequestMapping(value = "/" + PAGE_LOGIN, method = RequestMethod.POST)
	public String register(
			@Valid @ModelAttribute(ATTRIBUTE_ENTITY) UserEntity user,
			BindingResult result, Model uiModel, HttpServletRequest request) {
		if (result.hasErrors()) {
			uiModel.addAttribute(ATTRIBUTE_ENTITY, user);
			return PAGE_LOGIN;
		}

		Set<Role> authorities = new HashSet<Role>();
		authorities.add(Role.ROLE_USER);

		if (UserServiceFactory.getUserService().isUserAdmin())
			authorities.add(Role.ROLE_ADMIN);

		user.setAuthorities(authorities);

		service.insert(user);

		SecurityContext securityContext = SecurityContextHolder.getContext();

		Authentication auth = securityContext.getAuthentication();

		// Update the context with the full authentication
		securityContext.setAuthentication(new UserAuthentication(user, auth
				.getDetails()));

		ServletContext servletContext = request.getSession()
				.getServletContext();
		String previousPage = (String) servletContext
				.getAttribute(GoogleAccountsAuthenticationEntryPoint.PREVIOUS_PAGE);

		if (previousPage != null)
			return "redirect:" + previousPage;

		return "redirect:/home";
	}

	// for 403 access denied page
	@RequestMapping(value = "/" + PAGE_403, method = RequestMethod.GET)
	public String accesssDenied(Model uiModel) {
		// check if user is login
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserEntity currentUser = (UserEntity) auth.getPrincipal();

			uiModel.addAttribute("username", currentUser.getUsername());
		}

		return PAGE_403;
	}

	@RequestMapping(value = "/" + PAGE_DISABLED, method = RequestMethod.GET)
	public String disabled() {
		return PAGE_DISABLED;
	}

	@RequestMapping(value = "/" + PAGE_LOGOUT, method = RequestMethod.GET)
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.getSession().invalidate();

		String logoutUrl = UserServiceFactory.getUserService().createLogoutURL(
				"/home");

		response.sendRedirect(logoutUrl);
	}

}
