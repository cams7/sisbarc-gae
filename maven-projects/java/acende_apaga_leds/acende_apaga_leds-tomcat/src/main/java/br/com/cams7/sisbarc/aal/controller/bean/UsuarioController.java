/**
 * 
 */
package br.com.cams7.sisbarc.aal.controller.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import org.springframework.stereotype.Controller;

import br.com.cams7.app.domain.entity.UsuarioEntity;
import br.com.cams7.app.domain.entity.UsuarioEntity.Autorizacao;
import br.com.cams7.webapp.controller.AbstractAppController;
import br.com.cams7.webapp.service.UsuarioService;

/**
 * @author cams7
 *
 */
@Controller(UsuarioController.CONTROLLER_NAME)
@ManagedBean(name = UsuarioController.CONTROLLER_NAME)
@ViewScoped
public class UsuarioController extends
		AbstractAppController<UsuarioService, UsuarioEntity> {

	private static final long serialVersionUID = 1L;

	public final static String CONTROLLER_NAME = "usuarioMB";

	private final String LIST_PAGE = "listaUsuarios";

	/**
	 * 
	 */
	public UsuarioController() {
		super();
	}

	@Override
	public String createEntity() {
		String listPage = super.createEntity();

		addINFOMessage(
				getMessageFromI18N("msg.ok.summary.salvar.usuario"),
				getMessageFromI18N("msg.ok.detail.salvar.usuario",
						getSelectedEntity().getNome()));

		return listPage;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		addINFOMessage(
				getMessageFromI18N("msg.ok.summary.atualizar.usuario"),
				getMessageFromI18N("msg.ok.detail.atualizar.usuario",
						getSelectedEntity().getNome()));

	}

	@Override
	public void removeEntity() {
		super.removeEntity();

		addINFOMessage(
				getMessageFromI18N("msg.ok.summary.remover.usuario"),
				getMessageFromI18N("msg.ok.detail.remover.usuario",
						getSelectedEntity().getNome()));
	}

	public void validatePassword(ComponentSystemEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();

		UIComponent component = event.getComponent();

		// get password
		UIInput uiInputPassword = (UIInput) component
				.findComponent("usuarioSenha");
		String password = uiInputPassword.getLocalValue() == null ? ""
				: uiInputPassword.getLocalValue().toString().trim();
		String passwordId = uiInputPassword.getClientId();

		// get confirm password
		UIInput uiInputConfirmPassword = (UIInput) component
				.findComponent("confirmacaoSenha");
		String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? ""
				: uiInputConfirmPassword.getLocalValue().toString().trim();
		String confirmId = uiInputConfirmPassword.getClientId();

		if (password.isEmpty() || confirmPassword.isEmpty()) {
			if (password.isEmpty()) {
				FacesMessage message = new FacesMessage(
						getMessageFromI18N("label.usuario.senha.requiredMessage"));
				message.setSeverity(FacesMessage.SEVERITY_ERROR);

				context.addMessage(passwordId, message);
			}

			if (confirmPassword.isEmpty()) {
				FacesMessage message = new FacesMessage(
						getMessageFromI18N("label.usuario.confirmacaoSenha.requiredMessage"));
				message.setSeverity(FacesMessage.SEVERITY_ERROR);

				context.addMessage(confirmId, message);
			}

			context.renderResponse();
		} else if (!password.equals(confirmPassword)) {
			FacesMessage message = new FacesMessage(
					getMessageFromI18N("label.usuario.senha.notEqualsConfirmacaoSenha"));
			message.setSeverity(FacesMessage.SEVERITY_ERROR);

			context.addMessage(passwordId, message);
			context.renderResponse();
		}
	}

	public Autorizacao[] getAutorizacoes() {
		return Autorizacao.values();
	}

	@Override
	protected String getListPage() {
		return LIST_PAGE;
	}

}
