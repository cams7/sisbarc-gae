/**
 * 
 */
package br.com.cams7.sisbarc.aal.controller.bean;

import java.util.HashSet;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import org.springframework.stereotype.Controller;

import br.com.cams7.app.domain.entity.UserEntity;
import br.com.cams7.app.domain.entity.UserEntity.Role;
import br.com.cams7.webapp.controller.AbstractAppController;
import br.com.cams7.webapp.service.UserService;

/**
 * @author cams7
 *
 */
@Controller(UserController.CONTROLLER_NAME)
@ManagedBean(name = UserController.CONTROLLER_NAME)
@ViewScoped
public class UserController extends
		AbstractAppController<UserService, UserEntity> {

	private static final long serialVersionUID = 1L;

	public final static String CONTROLLER_NAME = "usuarioMB";

	private final String LIST_PAGE = "listaUsuarios";

	/**
	 * 
	 */
	public UserController() {
		super();
	}

	@Override
	public String createEntity() {
		String listPage = super.createEntity();

		addINFOMessage(
				getMessageFromI18N("msg.ok.summary.salvar.usuario"),
				getMessageFromI18N("msg.ok.detail.salvar.usuario",
						getSelectedEntity().getUsername()));

		return listPage;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		addINFOMessage(
				getMessageFromI18N("msg.ok.summary.atualizar.usuario"),
				getMessageFromI18N("msg.ok.detail.atualizar.usuario",
						getSelectedEntity().getUsername()));

	}

	@Override
	public void removeEntity() {
		super.removeEntity();

		addINFOMessage(
				getMessageFromI18N("msg.ok.summary.remover.usuario"),
				getMessageFromI18N("msg.ok.detail.remover.usuario",
						getSelectedEntity().getUsername()));
	}

	public void validatePassword(ComponentSystemEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();

		UIComponent component = event.getComponent();

		// get password
		UIInput uiInputPassword = (UIInput) component
				.findComponent("userPassword");
		String password = uiInputPassword.getLocalValue() == null ? ""
				: uiInputPassword.getLocalValue().toString().trim();
		String passwordId = uiInputPassword.getClientId();

		// get confirm password
		UIInput uiInputConfirmPassword = (UIInput) component
				.findComponent("confirmPassword");
		String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? ""
				: uiInputConfirmPassword.getLocalValue().toString().trim();
		String confirmId = uiInputConfirmPassword.getClientId();

		if (password.isEmpty() || confirmPassword.isEmpty()) {
			if (password.isEmpty()) {
				FacesMessage message = new FacesMessage(
						getMessageFromI18N("label.usuario.password.requiredMessage"));
				message.setSeverity(FacesMessage.SEVERITY_ERROR);

				context.addMessage(passwordId, message);
			}

			if (confirmPassword.isEmpty()) {
				FacesMessage message = new FacesMessage(
						getMessageFromI18N("label.usuario.confirmPassword.requiredMessage"));
				message.setSeverity(FacesMessage.SEVERITY_ERROR);

				context.addMessage(confirmId, message);
			}

			context.renderResponse();
		} else if (!password.equals(confirmPassword)) {
			FacesMessage message = new FacesMessage(
					getMessageFromI18N("label.usuario.password.notEqualsConfirmPassword"));
			message.setSeverity(FacesMessage.SEVERITY_ERROR);

			context.addMessage(passwordId, message);
			context.renderResponse();
		}
	}

	public Set<Role> getRoles() {
		Set<Role> roles = new HashSet<Role>();

		for (Role role : Role.values()) {
			if (role.equals(Role.ROLE_NEWUSER))
				continue;

			roles.add(role);
		}
		return roles;
	}

	@Override
	protected String getListPage() {
		return LIST_PAGE;
	}

}
