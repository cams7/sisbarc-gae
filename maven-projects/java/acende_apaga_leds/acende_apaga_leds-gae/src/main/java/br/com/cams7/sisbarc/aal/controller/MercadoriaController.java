package br.com.cams7.sisbarc.aal.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.cams7.gae.GaeController;
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
@RequestMapping(value = "/" + MercadoriaController.ATTRIBUTE_ENTITY + "/")
@Controller
public class MercadoriaController extends
		GaeController<MercadoriaService, MercadoriaEntity> {

	public static final String ATTRIBUTE_ENTITY = "mercadoria";

	private final String ATTRIBUTE_ENTITIES = "mercadorias";

	private final String PAGE_LIST = "listarMercadorias";
	private final String PAGE_INCLUDE = "incluirMercadoria";
	private final String PAGE_EDIT = "editarMercadoria";

	public MercadoriaController() {
		super();
	}

	@Override
	@RequestMapping(value = PAGE_INCLUDE, method = RequestMethod.POST)
	public String criar(
			@Valid @ModelAttribute(ATTRIBUTE_ENTITY) MercadoriaEntity mercadoria,
			BindingResult result, Model uiModel) {
		return super.criar(mercadoria, result, uiModel);
	}

	@Override
	@RequestMapping(value = PAGE_EDIT, method = RequestMethod.PUT)
	public String editar(
			@Valid @ModelAttribute(ATTRIBUTE_ENTITY) MercadoriaEntity mercadoria,
			BindingResult bindingResult, Model uiModel) {
		return super.editar(mercadoria, bindingResult, uiModel);
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
		return PAGE_INCLUDE;
	}

	@Override
	protected String getPageEdit() {
		return PAGE_EDIT;
	}

}
