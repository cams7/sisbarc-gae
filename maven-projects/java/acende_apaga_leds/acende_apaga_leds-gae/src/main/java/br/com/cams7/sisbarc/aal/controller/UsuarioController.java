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

import br.com.cams7.app.domain.entity.UsuarioEntity;
import br.com.cams7.gae.controller.AbstractAppController;
import br.com.cams7.sisbarc.aal.service.UsuarioService;

/**
 * @author cams7
 *
 */
@Controller
public class UsuarioController extends
		AbstractAppController<UsuarioService, UsuarioEntity> {

	private static final String ATTRIBUTE_ENTITY = "usuario";

	private final String ATTRIBUTE_ENTITIES = "usuarios";

	public static final String PAGE_MAIN = "/" + ATTRIBUTE_ENTITY;

	private final String PAGE_LIST = "listarUsuarios";
	private final String PAGE_INCLUDE = "incluirUsuario";
	private final String PAGE_EDIT = "editarUsuario";

	public UsuarioController() {
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
			@Valid @ModelAttribute(ATTRIBUTE_ENTITY) UsuarioEntity usuario,
			BindingResult result, Model uiModel) {
		return super.criar(usuario, result, uiModel);
	}

	@Override
	@RequestMapping(value = PAGE_MAIN + "/{" + VARIABLE_ID + "}", params = PARAM_FORM, method = RequestMethod.GET)
	public String editarForm(@PathVariable(VARIABLE_ID) Long id, Model uiModel) {
		return super.editarForm(id, uiModel);
	}

	@Override
	@RequestMapping(value = "/" + PAGE_EDIT, method = RequestMethod.PUT)
	public String editar(
			@Valid @ModelAttribute(ATTRIBUTE_ENTITY) UsuarioEntity usuario,
			BindingResult bindingResult, Model uiModel) {
		return super.editar(usuario, bindingResult, uiModel);
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

	@Override
	protected String getAttributeEntity() {
		return ATTRIBUTE_ENTITY;
	}

	@Override
	protected String getAttributeEntities() {
		return ATTRIBUTE_ENTITIES;
	}

	@Override
	protected String getPageMain() {
		return PAGE_MAIN;
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
