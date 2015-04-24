package br.com.cams7.sisbarc.aal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.cams7.app.BaseController;
import br.com.cams7.sisbarc.aal.domain.EmployeeEntity;
import br.com.cams7.sisbarc.aal.service.EmployeeService;

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
@RequestMapping(value = "/")
@Controller
public class EmployeeController extends
		BaseController<EmployeeService, EmployeeEntity, String> {

	private final String ATTRIBUTE_EMPLOYEES = "funcionarios";
	private final String ATTRIBUTE_EMPLOYEE = "funcionario";
	private final String ATTRIBUTE_PAGE_ACTIVE = "active";

	private final String ROOT_PAGE = "redirect:/";
	private final String LIST_PAGE = "lista";

	private final String FORM_PAGE = "form";
	private final String INCLUDE_PAGE = "incluir";
	private final String EDIT_PAGE = "editar";

	// private final String DELETE_PAGE = "";

	private final String ABOUT_PAGE = "sobre";

	@Autowired
	@Qualifier("sobreApp")
	private ArrayList<?> sobre;

	public EmployeeController() {
		super();
	}

	/**
	 * Ponto de entrada da aplicação ("/").
	 * 
	 * @param uiModel
	 *            recebe a lista de funcionarios.
	 * @return url para a pagina de listagem de funcionarios.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String listar(Model uiModel) {
		List<EmployeeEntity> employees = getService().findAll();
		uiModel.addAttribute(ATTRIBUTE_EMPLOYEES, employees);

		return LIST_PAGE;
	}

	/**
	 * Método executado antes de carregar a tela de inclusão de funcionario.
	 * 
	 * @param uiModel
	 * @return url da página de inclusão.
	 */
	@RequestMapping(params = FORM_PAGE, method = RequestMethod.GET)
	public String criarForm(Model uiModel) {
		EmployeeEntity employee = new EmployeeEntity();

		uiModel.addAttribute(ATTRIBUTE_EMPLOYEE, employee);
		uiModel.addAttribute(ATTRIBUTE_PAGE_ACTIVE, INCLUDE_PAGE);

		getLog().debug("Pronto para incluir funcionario");
		return INCLUDE_PAGE;
	}

	/**
	 * Método executado na inserção de funcionario.
	 * 
	 * @param employee
	 *            instância com os dados preenchidos na tela
	 * @param bindingResult
	 *            componente usado para verificar problemas com validação.
	 * @param uiModel
	 * @return a url para a listagem, se algum erro de validação for encontrado
	 *         volta para a pagina de inclusão.
	 */
	@RequestMapping(value = INCLUDE_PAGE, method = RequestMethod.POST)
	public String criar(@Valid EmployeeEntity employee,
			BindingResult bindingResult, Model uiModel) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute(ATTRIBUTE_EMPLOYEE, employee);
			uiModel.addAttribute(ATTRIBUTE_PAGE_ACTIVE, INCLUDE_PAGE);
			return INCLUDE_PAGE;
		}

		getService().save(employee);

		return ROOT_PAGE;
	}

	/**
	 * Método executado antes de carregar a tela de edição de funcionarios.
	 * 
	 * @param id
	 *            do funcionario que deve ser editado.
	 * @param uiModel
	 *            armazena o objeto funcionario que deverá ser alterado.
	 * @return url da página de edição.
	 */
	@RequestMapping(value = "/{id}", params = FORM_PAGE, method = RequestMethod.GET)
	public String editarForm(@PathVariable("id") String id, Model uiModel) {
		EmployeeEntity employee = getService().findOne(id);

		if (employee != null) {
			uiModel.addAttribute(ATTRIBUTE_EMPLOYEE, employee);
			getLog().debug("Pronto para editar funcionario");
		}

		return EDIT_PAGE;
	}

	/**
	 * Método executado ao salvar a edição de funcionario.
	 * 
	 * @param employee
	 *            objeto com os dados enviados pela tela.
	 * @param bindingResult
	 *            componente usado para verificar problemas com validação.
	 * @param uiModel
	 * @return a url para a listagem, se algum erro de validação for encontrado
	 *         volta para a pagina de edição.
	 */
	// @RequestMapping(method = RequestMethod.PUT)
	@RequestMapping(value = EDIT_PAGE, method = RequestMethod.POST)
	public String editar(@Valid EmployeeEntity employee,
			BindingResult bindingResult, Model uiModel) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute(ATTRIBUTE_EMPLOYEE, employee);
			return EDIT_PAGE;
		}

		getService().update(employee);

		return ROOT_PAGE;
	}

	/**
	 * Método executado na exclusão do funcionario.
	 * 
	 * @param id
	 *            do funcionario que deverá ser removido.
	 * @param uiModel
	 * @return url da página de listagem.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String remover(@PathVariable("id") String id, Model uiModel) {
		EmployeeEntity employeee = getService().remove(id);
		if (employeee != null)
			getLog().debug("Funcionario removido");

		return ROOT_PAGE;
	}

	@RequestMapping(value = ABOUT_PAGE, method = RequestMethod.GET)
	public String sobre(Model uiModel) {
		uiModel.addAttribute(ABOUT_PAGE, sobre);
		uiModel.addAttribute(ATTRIBUTE_PAGE_ACTIVE, ABOUT_PAGE);
		return ABOUT_PAGE;
	}

}
