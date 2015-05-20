package br.com.cams7.sisbarc.aal.controller.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.stereotype.Controller;

import br.com.cams7.sisbarc.aal.domain.entity.FuncionarioEntity;
import br.com.cams7.sisbarc.aal.service.FuncionarioService;
import br.com.cams7.webapp.AppController;

/**
 * Principal componente do framework <code>Spring MVC</code>, esse é o
 * controller do cadastro de funcionarios.
 * 
 * <p>
 * Tem como responsabilidade: definir o mapeamento de navegação, acionar
 * validadores e conversores de dados, fornecer e receber os dados da camada de
 * visão (<code>JSP</code>).
 * </p>
 * 
 * <p>
 * Os métodos de navegação, retornam a url definida no Tiles. Veja também o
 * arquivo <code>views.xml</code>.
 * </p>
 * 
 * @author cams7
 *
 */
@Controller(FuncionarioController.CONTROLLER_NAME)
@ManagedBean(name = FuncionarioController.CONTROLLER_NAME)
@ViewScoped
public class FuncionarioController extends
		AppController<FuncionarioService, FuncionarioEntity, String> {

	private static final long serialVersionUID = 1L;

	public final static String CONTROLLER_NAME = "funcionarioMB";

	private final String LIST_PAGE = "listaFuncionarios";

	public FuncionarioController() {
		super();
	}

	@Override
	public String createEntity() {
		String listPage = super.createEntity();

		addINFOMessage(
				getMessageFromI18N("msg.ok.summary.salvar.funcionario"),
				getMessageFromI18N("msg.ok.detail.salvar.funcionario",
						getSelectedEntity().getNome()));

		return listPage;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		addINFOMessage(
				getMessageFromI18N("msg.ok.summary.atualizar.funcionario"),
				getMessageFromI18N("msg.ok.detail.atualizar.funcionario",
						getSelectedEntity().getNome()));

	}

	@Override
	public void removeEntity() {
		super.removeEntity();

		addINFOMessage(
				getMessageFromI18N("msg.ok.summary.remover.funcionario"),
				getMessageFromI18N("msg.ok.detail.remover.funcionario",
						getSelectedEntity().getNome()));
	}

	@Override
	protected String getListPage() {
		return LIST_PAGE;
	}

}
