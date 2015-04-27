package br.com.cams7.sisbarc.aal.controller;

import static javax.faces.context.FacesContext.getCurrentInstance;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.springframework.stereotype.Controller;

import br.com.cams7.sisbarc.aal.domain.EmployeeEntity;
import br.com.cams7.sisbarc.aal.service.EmployeeService;
import br.com.cams7.webapp.BaseJSFController;

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
		BaseJSFController<EmployeeService, EmployeeEntity, String> implements
		Serializable {

	private static final long serialVersionUID = 1L;

	public final static String CONTROLLER_NAME = "funcionarioMB";

	/**
	 * Referência para o funcionario utiliza na inclusão (nova) ou edição.
	 */
	private EmployeeEntity funcionario;

	/**
	 * Informação é utilizada na edição do funcionario, quando a seleção de um
	 * registro na listagem ocorrer.
	 */
	private String idSelecionado;

	/**
	 * Mantém os funcionarios apresentadas na listagem indexadas pelo id.
	 * <strong>Importante:</strong> a consulta (query) no DataStore do App
	 * Engine pode retornar <i>dados antigos</i>, que já foram removidos ou que
	 * ainda não foram incluidos, devido a replicação dos dados.
	 * 
	 * Dessa forma esse hashmap mantém um espelho do datastore para minizar o
	 * impacto desse modelo do App Engine.
	 */
	// private Map<String, EmployeeEntity> funcionarios;
	private List<EmployeeEntity> funcionarios;

	public EmployeeController() {
		super();
	}

	@PostConstruct
	private void ini() {
		// getLog().info("Service = " + getService());
		fillFuncionarios();
	}

	public EmployeeEntity getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(EmployeeEntity funcionario) {
		this.funcionario = funcionario;
	}

	public void setIdSelecionado(String idSelecionado) {
		this.idSelecionado = idSelecionado;
	}

	public String getIdSelecionado() {
		return idSelecionado;
	}

	/**
	 * @return <code>DataModel</code> para carregar a lista de funcionarios.
	 */
	public DataModel<EmployeeEntity> getDmFuncionarios() {
		return new ListDataModel<EmployeeEntity>(new ArrayList<EmployeeEntity>(
				funcionarios));
	}

	private void fillFuncionarios() {
		// try {
		// List<EmployeeEntity> qryMercadorias = new ArrayList<EmployeeEntity>(
		// getService().findAll());
		// funcionarios = new HashMap<String, EmployeeEntity>();
		// for (EmployeeEntity funcionario : qryMercadorias) {
		// funcionarios.put(funcionario.getId(), funcionario);
		// }
		//
		// getLog().debug(
		// "Carregou a lista de funcionarios (" + funcionarios.size()
		// + ")");
		// } catch (Exception ex) {
		// getLog().error("Erro ao carregar a lista de funcionarios.", ex);
		// addMessage(getMessageFromI18N("msg.erro.listar.funcionario"),
		// ex.getMessage());
		// }
		funcionarios = getService().findAll();
	}

	/**
	 * Ação executada quando a página de inclusão de funcionarios for carregada.
	 */
	public void incluir() {
		funcionario = new EmployeeEntity();
		getLog().debug("Pronto pra incluir");
	}

	/**
	 * Ação executada quando a página de edição de funcionarios for carregada.
	 */
	public void editar() {
		if (idSelecionado == null) {
			return;
		}
		funcionario = getService().findOne(idSelecionado);
		getLog().debug("Pronto pra editar");
	}

	/**
	 * Operação acionada pela tela de inclusão ou edição, através do
	 * <code>commandButton</code> <strong>Salvar</strong>.
	 * 
	 * @return Se a inclusão/edição foi realizada vai para listagem, senão
	 *         permanece na mesma tela.
	 */
	public String salvar() {
		try {
			getService().save(funcionario);
		} catch (Exception ex) {
			getLog().error("Erro ao salvar funcionario.", ex);
			addMessage(getMessageFromI18N("msg.erro.salvar.funcionario"),
					ex.getMessage());
			return null;
		}
		fillFuncionarios();

		getLog().debug("Salvour funcionario " + funcionario.getId());

		return "listaFuncionarios";
	}

	/**
	 * Operação acionada pela tela de listagem, através do
	 * <code>commandButton</code> <strong>Atualizar</strong>.
	 */
	public void atualizar() {
		fillFuncionarios();
	}

	/**
	 * Operação acionada toda a vez que a tela de listagem for carregada.
	 */
	public void reset() {
		funcionario = null;
		idSelecionado = null;
	}

	/**
	 * Operação acionada pela tela de edição, através do
	 * <code>commandButton</code> <strong>Excluir</strong>.
	 * 
	 * @return Se a exclusão for realizada vai para a listagem, senão permanece
	 *         na mesma tela.
	 */
	public String remover() {
		try {
			getService().remove(funcionario);
			fillFuncionarios();
		} catch (Exception ex) {
			getLog().error("Erro ao remover funcionario.", ex);
			addMessage(getMessageFromI18N("msg.erro.remover.funcionario"),
					ex.getMessage());
			return "";
		}
		getLog().debug("Removeu funcionario " + funcionario.getId());
		return "listaFuncionarios";
	}

	/**
	 * @param key
	 * @return Recupera a mensagem do arquivo properties
	 *         <code>ResourceBundle</code>.
	 */
	private String getMessageFromI18N(String key) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages_labels",
				getCurrentInstance().getViewRoot().getLocale());
		return bundle.getString(key);
	}

	/**
	 * Adiciona um mensagem no contexto do Faces (<code>FacesContext</code>).
	 * 
	 * @param summary
	 * @param detail
	 */
	private void addMessage(String summary, String detail) {
		getCurrentInstance().addMessage(
				null,
				new FacesMessage(summary, summary.concat("<br/>")
						.concat(detail)));
	}

}
