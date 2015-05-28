package br.com.cams7.sisbarc.aal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.cams7.gae.GaeController;
import br.com.cams7.gae.GaeService;
import br.com.cams7.sisbarc.aal.domain.entity.MercadoriaEntity;
import br.com.cams7.sisbarc.aal.service.MercadoriaService;

/**
 * Principal componente do framework <code>Spring MVC</code>, esse é o
 * controller do cadastro de mercadorias.
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
 * @author YaW Tecnologia
 */
@RequestMapping(value = "/")
@Controller
public class MercadoriaController extends
		GaeController<MercadoriaService, MercadoriaEntity> {

	private final String ATTRIBUTE_MERCADORIAS = "mercadorias";
	private final String ATTRIBUTE_MERCADORIA = "mercadoria";
	private final String ATTRIBUTE_PAGE_ACTIVE = "active";

	private final String ROOT_PAGE = "redirect:/";
	private final String LIST_PAGE = "listarMercadorias";

	private final String FORM_PAGE = "form";
	private final String INCLUDE_PAGE = "incluirMercadoria";
	private final String EDIT_PAGE = "editarMercadoria";

	// private final String DELETE_PAGE = "";

	private final String ABOUT_PAGE = "sobre";

	@Autowired
	@Qualifier("sobreApp")
	private ArrayList<?> sobre;

	public MercadoriaController() {
		super();
	}

	/**
	 * Ponto de entrada da aplicação ("/").
	 * 
	 * @param uiModel
	 *            recebe a lista de mercadorias.
	 * @return url para a pagina de listagem de mercadorias.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String listar(Model uiModel) {
		List<MercadoriaEntity> mercadorias = getService().findAll();
		uiModel.addAttribute(ATTRIBUTE_MERCADORIAS, mercadorias);

		return LIST_PAGE;
	}

	/**
	 * Método executado antes de carregar a tela de inclusão de mercadoria.
	 * 
	 * @param uiModel
	 * @return url da página de inclusão.
	 */
	@RequestMapping(params = FORM_PAGE, method = RequestMethod.GET)
	public String criarForm(Model uiModel) {
		MercadoriaEntity mercadoria = new MercadoriaEntity();

		uiModel.addAttribute(ATTRIBUTE_MERCADORIA, mercadoria);
		uiModel.addAttribute(ATTRIBUTE_PAGE_ACTIVE, INCLUDE_PAGE);

		getLog().log(Level.INFO, "Pronto para incluir mercadoria");
		return INCLUDE_PAGE;
	}

	/**
	 * Método executado na inserção da mercadoria.
	 * 
	 * @param mercadoria
	 *            instância com os dados preenchidos na tela
	 * @param result
	 *            componente usado para verificar problemas com validação.
	 * @param uiModel
	 * @return a url para a listagem, se algum erro de validação for encontrado
	 *         volta para a pagina de inclusão.
	 */
	@RequestMapping(value = INCLUDE_PAGE, method = RequestMethod.POST)
	public String criar(
			@Valid @ModelAttribute(ATTRIBUTE_MERCADORIA) MercadoriaEntity mercadoria,
			BindingResult result, Model uiModel) {
		if (result.hasErrors()) {
			uiModel.addAttribute(ATTRIBUTE_MERCADORIA, mercadoria);
			uiModel.addAttribute(ATTRIBUTE_PAGE_ACTIVE, INCLUDE_PAGE);
			return INCLUDE_PAGE;
		}

		getService().insert(mercadoria);

		return ROOT_PAGE;
	}

	/**
	 * Método executado antes de carregar a tela de edição de mercadorias.
	 * 
	 * @param id
	 *            da mercadoria que deve ser editada.
	 * @param uiModel
	 *            armazena o objeto mercadoria que deverá ser alterado.
	 * @return url da página de edição.
	 */
	@RequestMapping(value = "/{id}", params = FORM_PAGE, method = RequestMethod.GET)
	public String editarForm(@PathVariable("id") Long id, Model uiModel) {
		MercadoriaEntity mercadoria = getService().findOne(id);

		if (mercadoria != null) {
			uiModel.addAttribute(ATTRIBUTE_MERCADORIA, mercadoria);
			getLog().log(Level.INFO, "Pronto para editar mercadoria");
		}

		return EDIT_PAGE;
	}

	/**
	 * Método executado ao salvar a edição de mercadoria.
	 * 
	 * @param mercadoria
	 *            objeto com os dados enviados pela tela.
	 * @param bindingResult
	 *            componente usado para verificar problemas com validação.
	 * @param uiModel
	 * @return a url para a listagem, se algum erro de validação for encontrado
	 *         volta para a pagina de edição.
	 */
	@RequestMapping(value = EDIT_PAGE, method = RequestMethod.PUT)
	public String editar(
			@Valid @ModelAttribute(ATTRIBUTE_MERCADORIA) MercadoriaEntity mercadoria,
			BindingResult bindingResult, Model uiModel) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute(ATTRIBUTE_MERCADORIA, mercadoria);
			return EDIT_PAGE;
		}

		getService().save(mercadoria);

		return ROOT_PAGE;
	}

	/**
	 * Método executado na exclusão da mercadoria.
	 * 
	 * @param id
	 *            da mercadoria que deverá ser removida.
	 * @param uiModel
	 * @return url da página de listagem.
	 */
	@RequestMapping(value = "/" + ATTRIBUTE_MERCADORIA + "/{id}", method = RequestMethod.DELETE)
	public String remover(@PathVariable("id") Long id, Model uiModel) {
		MercadoriaEntity mercadoria = getService().findOne(id);

		if (mercadoria != null)
			getService().delete(mercadoria);

		return ROOT_PAGE;
	}

	/**
	 * Método executado para sincronizar os dados do <code>DataSource</code>.
	 * Botão atualizar.
	 * 
	 * @return url da página de listagem.
	 */
	@RequestMapping(value = "synch", method = RequestMethod.GET)
	public String atualizar() {
		((GaeService<?>) getService()).synch();
		return ROOT_PAGE;
	}

	@RequestMapping(value = ABOUT_PAGE, method = RequestMethod.GET)
	public String sobre(Model uiModel) {
		uiModel.addAttribute(ABOUT_PAGE, sobre);
		uiModel.addAttribute(ATTRIBUTE_PAGE_ACTIVE, ABOUT_PAGE);
		return ABOUT_PAGE;
	}

}
