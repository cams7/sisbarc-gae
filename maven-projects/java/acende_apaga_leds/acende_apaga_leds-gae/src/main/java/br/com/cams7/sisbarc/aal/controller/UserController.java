/**
 * 
 */
package br.com.cams7.sisbarc.aal.controller;

import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.cams7.app.domain.entity.UserEntity;
import br.com.cams7.app.domain.entity.UserEntity.Role;
import br.com.cams7.gae.controller.AbstractAppController;
import br.com.cams7.gae.controller.LoginController;
import br.com.cams7.gae.service.UserService;
import br.com.cams7.gae.validator.UserValidator;

/**
 * @author cams7
 *
 */
@Controller
public class UserController extends
		AbstractAppController<UserService, UserEntity> {

	private static final String ATTRIBUTE_ENTITY = LoginController.ATTRIBUTE_ENTITY;

	private final String ATTRIBUTE_ENTITIES = "usuarios";

	public static final String PAGE_MAIN = "/" + ATTRIBUTE_ENTITY;

	private final String PAGE_LIST = "listar_usuarios";
	private final String PAGE_EDIT = "editar_usuario";

	private UserValidator validator;

	@Autowired
	public UserController(UserValidator validator) {
		super();
		this.validator = validator;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		super.initBinder(binder);
	}

	@Override
	@RequestMapping(value = PAGE_MAIN, method = RequestMethod.GET)
	public String listar(Model uiModel) {
		String page = super.listar(uiModel);
		return page;
	}

	@Override
	@RequestMapping(value = PAGE_MAIN + "/{" + VARIABLE_ID + "}", params = PARAM_FORM, method = RequestMethod.GET)
	public String editarForm(@PathVariable(VARIABLE_ID) Long id, Model uiModel) {
		String page = super.editarForm(id, uiModel);

		switch (page) {
		case PAGE_EDIT:
			addAttributeIsntLoginPage(uiModel);
			break;
		case PAGE_ERROR:
			break;
		default:
			break;
		}

		return page;
	}

	@Override
	@RequestMapping(value = "/" + PAGE_EDIT, method = RequestMethod.PUT)
	public String editar(
			@Valid @ModelAttribute(ATTRIBUTE_ENTITY) UserEntity user,
			BindingResult result, Model uiModel, Locale locale) {

		validator.validate(user, result);

		String page = super.editar(user, result, uiModel, locale);

		switch (page) {
		case PAGE_EDIT:
			addAttributeIsntLoginPage(uiModel);
			break;
		case PAGE_LIST:
			addINFOMessage(
					uiModel,
					getMessageSource().getMessage(
							"msg.ok.summary.atualizar.usuario",
							new Object[] {}, locale),
					getMessageSource().getMessage(
							"msg.ok.detail.atualizar.usuario",
							new Object[] { user.getEmail() }, locale));
			break;
		default:
			break;
		}

		return page;
	}

	@Override
	@RequestMapping(value = PAGE_MAIN + "/{" + VARIABLE_ID + "}", method = RequestMethod.DELETE)
	public String remover(@PathVariable(VARIABLE_ID) Long id, Model uiModel,
			Locale locale) {
		String page = super.remover(id, uiModel, locale);

		switch (page) {
		case PAGE_ERROR:
			break;
		case PAGE_LIST:
			addINFOMessage(
					uiModel,
					getMessageSource().getMessage(
							"msg.ok.summary.remover.usuario", new Object[] {},
							locale),
					getMessageSource().getMessage(
							"msg.ok.detail.remover.usuario",
							new Object[] { id }, locale));
			break;
		default:
			break;
		}

		return page;
	}

	private void addAttributeIsntLoginPage(Model uiModel) {
		uiModel.addAttribute(LoginController.ATTRIBUTE_IS_LOGIN_PAGE,
				Boolean.FALSE);
	}

	@Override
	@RequestMapping(value = PAGE_MAIN + "/synch", method = RequestMethod.GET)
	public String atualizar(Model uiModel) {
		String page = super.atualizar(uiModel);
		return page;
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

	@Override
	protected String getAttributeEntity() {
		return ATTRIBUTE_ENTITY;
	}

	@Override
	protected String getAttributeEntities() {
		return ATTRIBUTE_ENTITIES;
	}

	@Override
	protected String getPageList() {
		return PAGE_LIST;
	}

	@Override
	protected String getPageInclude() {
		// return PAGE_INCLUDE;
		return null;
	}

	@Override
	protected String getPageEdit() {
		return PAGE_EDIT;
	}

}
