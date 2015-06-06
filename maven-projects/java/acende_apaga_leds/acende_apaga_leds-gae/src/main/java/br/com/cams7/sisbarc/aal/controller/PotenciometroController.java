/**
 * 
 */
package br.com.cams7.sisbarc.aal.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.cams7.gae.controller.AbstractAppController;
import br.com.cams7.sisbarc.aal.domain.entity.PotenciometroEntity;
import br.com.cams7.sisbarc.aal.service.PotenciometroService;

/**
 * Principal componente do framework <code>Spring MVC</code>, esse e o
 * controller do cadastro de potenciometros.
 * 
 * <p>
 * Tem como responsabilidade: definir o mapeamento de navegacao, acionar
 * validadores e conversores de dados, fornecer e receber os dados da camada de
 * visao (<code>JSP</code>).
 * </p>
 * 
 * <p>
 * Os metodos de navegacao, retornam a url definida no Tiles. Veja tambem o
 * arquivo <code>views.xml</code>.
 * </p>
 * 
 * @author cams7
 *
 */
@Controller
public class PotenciometroController extends
		AbstractAppController<PotenciometroService, PotenciometroEntity> {

	private static final String ATTRIBUTE_ENTITY = "potenciometro";

	private final String ATTRIBUTE_ENTITIES = "potenciometros";

	public static final String PAGE_MAIN = "/" + ATTRIBUTE_ENTITY;

	private final String PAGE_LIST = "listarPotenciometros";
	private final String PAGE_INCLUDE = "incluirPotenciometro";
	private final String PAGE_EDIT = "editarPotenciometro";

	/**
	 * 
	 */
	public PotenciometroController() {
		super();
	}

	@Override
	@RequestMapping(value = PAGE_MAIN, method = RequestMethod.GET)
	public String listar(Model uiModel) {
		return super.listar(uiModel);
	}

	@Override
	@RequestMapping(value = PAGE_MAIN, params = PARAM_FORM, method = RequestMethod.GET)
	public String criarForm(Model uiModel) {
		return super.criarForm(uiModel);
	}

	@Override
	@RequestMapping(value = "/" + PAGE_INCLUDE, method = RequestMethod.POST)
	public String criar(
			@Valid @ModelAttribute(ATTRIBUTE_ENTITY) PotenciometroEntity potenciometro,
			BindingResult result, Model uiModel) {
		return super.criar(potenciometro, result, uiModel);
	}

	@Override
	@RequestMapping(value = PAGE_MAIN + "/{" + VARIABLE_ID + "}", params = PARAM_FORM, method = RequestMethod.GET)
	public String editarForm(@PathVariable(VARIABLE_ID) Long id, Model uiModel) {
		return super.editarForm(id, uiModel);
	}

	@Override
	@RequestMapping(value = "/" + PAGE_EDIT, method = RequestMethod.PUT)
	public String editar(
			@Valid @ModelAttribute(ATTRIBUTE_ENTITY) PotenciometroEntity potenciometro,
			BindingResult bindingResult, Model uiModel) {
		return super.editar(potenciometro, bindingResult, uiModel);
	}

	@Override
	@RequestMapping(value = PAGE_MAIN + "/{" + VARIABLE_ID + "}", method = RequestMethod.DELETE)
	public String remover(@PathVariable(VARIABLE_ID) Long id, Model uiModel) {
		return super.remover(id, uiModel);
	}

	@Override
	@RequestMapping(value = PAGE_MAIN + "/synch", method = RequestMethod.GET)
	public String atualizar() {
		return super.atualizar();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.gae.controller.AbstractAppController#getAttributeEntity()
	 */
	@Override
	protected String getAttributeEntity() {
		return ATTRIBUTE_ENTITY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.gae.controller.AbstractAppController#getAttributeEntities()
	 */
	@Override
	protected String getAttributeEntities() {
		return ATTRIBUTE_ENTITIES;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.gae.controller.AbstractAppController#getPageMain()
	 */
	@Override
	protected String getPageMain() {
		return PAGE_MAIN;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.gae.controller.AbstractAppController#getPageList()
	 */
	@Override
	protected String getPageList() {
		return PAGE_LIST;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.gae.controller.AbstractAppController#getPageInclude()
	 */
	@Override
	protected String getPageInclude() {
		return PAGE_INCLUDE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.gae.controller.AbstractAppController#getPageEdit()
	 */
	@Override
	protected String getPageEdit() {
		return PAGE_EDIT;
	}

}
