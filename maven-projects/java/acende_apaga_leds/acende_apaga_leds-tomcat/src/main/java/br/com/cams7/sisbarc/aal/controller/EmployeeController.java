package br.com.cams7.sisbarc.aal.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.stereotype.Controller;

import br.com.cams7.sisbarc.aal.domain.EmployeeEntity;
import br.com.cams7.sisbarc.aal.domain.EmployeeEntity.Status;
import br.com.cams7.sisbarc.aal.service.EmployeeService;
import br.com.cams7.webapp.TomcatController;

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
@Controller(EmployeeController.CONTROLLER_NAME)
@ManagedBean(name = EmployeeController.CONTROLLER_NAME)
@ViewScoped
public class EmployeeController extends
		TomcatController<EmployeeService, EmployeeEntity, String> {

	private static final long serialVersionUID = 1L;

	public final static String CONTROLLER_NAME = "funcionarioMB";

	private final String LIST_PAGE = "listaFuncionarios";

	public EmployeeController() {
		super();
	}

	@Override
	public String saveEntity() {
		String listPage = super.saveEntity();

		addINFOMessage(
				getMessageFromI18N("msg.ok.summary.salvar.funcionario"),
				getMessageFromI18N("msg.ok.detail.salvar.funcionario",
						getSelectedEntity().getName()));

		return listPage;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		addINFOMessage(
				getMessageFromI18N("msg.ok.summary.atualizar.funcionario"),
				getMessageFromI18N("msg.ok.detail.atualizar.funcionario",
						getSelectedEntity().getName()));

	}

	@Override
	public void removeEntity() {
		super.removeEntity();

		addINFOMessage(
				getMessageFromI18N("msg.ok.summary.remover.funcionario"),
				getMessageFromI18N("msg.ok.detail.remover.funcionario",
						getSelectedEntity().getName()));
	}

	public Status[] getSituacoes() {
		return Status.values();
	}

	@Override
	protected String getListPage() {
		return LIST_PAGE;
	}

}
