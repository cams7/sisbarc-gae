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
import br.com.cams7.gae.GaeService;
import br.com.cams7.sisbarc.aal.domain.MercadoriaEntity;
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
		BaseController<MercadoriaService, MercadoriaEntity, Long> {

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
		uiModel.addAttribute("mercadorias", mercadorias);

		return "lista";
	}

	/**
	 * Método executado antes de carregar a tela de inclusão de mercadoria.
	 * 
	 * @param uiModel
	 * @return url da página de inclusão.
	 */
	@RequestMapping(params = "form", method = RequestMethod.GET)
	public String criarForm(Model uiModel) {
		MercadoriaEntity mercadoria = new MercadoriaEntity();

		uiModel.addAttribute("mercadoria", mercadoria);
		uiModel.addAttribute("active", "incluir");

		getLog().debug("Pronto para incluir mercadoria");
		return "incluir";
	}

	/**
	 * Método executado na inserção da mercadoria.
	 * 
	 * @param mercadoria
	 *            instância com os dados preenchidos na tela
	 * @param bindingResult
	 *            componente usado para verificar problemas com validação.
	 * @param uiModel
	 * @return a url para a listagem, se algum erro de validação for encontrado
	 *         volta para a pagina de inclusão.
	 */
	@RequestMapping(value = "incluir", method = RequestMethod.POST)
	public String criar(@Valid MercadoriaEntity mercadoria,
			BindingResult bindingResult, Model uiModel) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("mercadoria", mercadoria);
			uiModel.addAttribute("active", "incluir");
			return "incluir";
		}

		getService().save(mercadoria);

		return "redirect:/";
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
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
	public String editarForm(@PathVariable("id") Long id, Model uiModel) {
		MercadoriaEntity mercadoria = getService().findOne(id);

		if (mercadoria != null) {
			uiModel.addAttribute("mercadoria", mercadoria);
			getLog().debug("Pronto para editar mercadoria");
		}

		return "editar";
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
	// @RequestMapping(method = RequestMethod.PUT)
	@RequestMapping(value = "editar", method = RequestMethod.POST)
	public String editar(@Valid MercadoriaEntity mercadoria,
			BindingResult bindingResult, Model uiModel) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("mercadoria", mercadoria);
			return "editar";
		}

		getService().update(mercadoria);

		return "redirect:/";
	}

	/**
	 * Método executado na exclusão da mercadoria.
	 * 
	 * @param id
	 *            da mercadoria que deverá ser removida.
	 * @param uiModel
	 * @return url da página de listagem.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String remover(@PathVariable("id") Long id, Model uiModel) {
		MercadoriaEntity mercadoria = getService().findOne(id);

		if (mercadoria != null)
			getService().remove(mercadoria);

		return "redirect:/";
	}

	/**
	 * Método executado para sincronizar os dados do <code>DataSource</code>.
	 * Botão atualizar.
	 * 
	 * @return url da página de listagem.
	 */
	@RequestMapping(value = "synch", method = RequestMethod.GET)
	public String atualizar() {
		((GaeService<?, ?>) getService()).synch();
		return "redirect:/";
	}

	@RequestMapping(value = "sobre", method = RequestMethod.GET)
	public String sobre(Model uiModel) {
		uiModel.addAttribute("sobre", sobre);
		uiModel.addAttribute("active", "sobre");
		return "sobre";
	}

}
