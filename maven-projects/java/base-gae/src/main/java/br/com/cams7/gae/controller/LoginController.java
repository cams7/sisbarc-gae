/**
 * 
 */
package br.com.cams7.gae.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.cams7.app.domain.entity.UserEntity;
import br.com.cams7.app.domain.entity.UserEntity.Role;
import br.com.cams7.gae.security.AuthenticationHelper;
import br.com.cams7.gae.security.GoogleAccountsAuthenticationEntryPoint;
import br.com.cams7.gae.service.UserService;
import br.com.cams7.gae.validator.UserValidator;

import com.google.appengine.api.users.UserServiceFactory;

/**
 * @author cams7
 *
 */
@Controller
public class LoginController {

	private final String PARAM_FORM = "form";

	private final String ATTRIBUTE_ENTITY = "usuario";

	public static final String PAGE_CADASTRAR_LOGIN = "cadastrar_login";
	private final String PAGE_EDITAR_LOGIN = "editar_login";

	private final String PAGE_LOGOUT = "logout";

	private final String PAGE_403 = "403";
	private final String PAGE_DISABLED = "disabled";

	@Autowired
	private UserService service;

	private UserValidator validator;

	@Autowired
	public LoginController(UserValidator validator) {
		super();
		this.validator = validator;
	}

	@RequestMapping(value = "/" + PAGE_CADASTRAR_LOGIN, params = PARAM_FORM, method = RequestMethod.GET)
	public String criarForm(Model uiModel) {
		UserEntity currentUser = AuthenticationHelper.getCurrentUser();
		uiModel.addAttribute(ATTRIBUTE_ENTITY, currentUser);

		return PAGE_CADASTRAR_LOGIN;
	}

	@RequestMapping(value = "/" + PAGE_CADASTRAR_LOGIN, method = RequestMethod.POST)
	public String criar(
			@Valid @ModelAttribute(ATTRIBUTE_ENTITY) UserEntity user,
			BindingResult result, Model uiModel, HttpServletRequest request) {

		validator.validate(user, result);

		if (result.hasErrors()) {
			uiModel.addAttribute(ATTRIBUTE_ENTITY, user);
			return PAGE_CADASTRAR_LOGIN;
		}

		Set<Role> authorities = new HashSet<Role>();
		authorities.add(Role.ROLE_USER);

		if (UserServiceFactory.getUserService().isUserAdmin())
			authorities.add(Role.ROLE_ADMIN);

		user.setAuthorities(authorities);

		service.insert(user);

		AuthenticationHelper.changeAuthentication(user);

		ServletContext servletContext = request.getSession()
				.getServletContext();
		String previousPage = (String) servletContext
				.getAttribute(GoogleAccountsAuthenticationEntryPoint.PREVIOUS_PAGE);

		if (previousPage != null)
			return "redirect:" + previousPage;

		return "redirect:/home";
	}

	@RequestMapping(value = "/" + PAGE_EDITAR_LOGIN, params = PARAM_FORM, method = RequestMethod.GET)
	public String editarForm(Model uiModel) {
		UserEntity currentUser = AuthenticationHelper.getCurrentUser();
		uiModel.addAttribute(ATTRIBUTE_ENTITY, currentUser);

		return PAGE_EDITAR_LOGIN;
	}

	@RequestMapping(value = "/" + PAGE_EDITAR_LOGIN, method = RequestMethod.PUT)
	public String editar(
			@Valid @ModelAttribute(ATTRIBUTE_ENTITY) UserEntity user,
			BindingResult result, Model uiModel, Locale locale) {

		validator.validate(user, result);

		if (result.hasErrors()) {
			uiModel.addAttribute(ATTRIBUTE_ENTITY, user);
			return PAGE_EDITAR_LOGIN;
		}

		service.save(user);

		AuthenticationHelper.changeAuthentication(user);

		return "redirect:/home";

	}

	// for 403 access denied page
	@RequestMapping(value = "/" + PAGE_403, method = RequestMethod.GET)
	public String accesssDenied() {
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

	@ModelAttribute("roles")
	public Set<Role> populateAutorizacoes() {
		// Data referencing for web framework checkboxes
		Set<Role> roles = new LinkedHashSet<Role>();

		for (Role role : Role.values()) {
			if (role.equals(Role.ROLE_NEWUSER))
				continue;

			roles.add(role);
		}

		return roles;
	}

}
