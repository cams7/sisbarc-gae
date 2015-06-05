/**
 * 
 */
package br.com.cams7.gae.controller;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;

import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import br.com.cams7.app.controller.AbstractController;
import br.com.cams7.app.domain.AbstractEntity;
import br.com.cams7.gae.service.AppService;
import br.com.cams7.util.AppException;
import br.com.cams7.util.AppUtil;

/**
 * @author cams7
 *
 */
public abstract class AbstractAppController<S extends AppService<E>, E extends AbstractEntity>
		extends AbstractController<S, E> {

	protected final String ATTRIBUTE_PAGE_ACTIVE = "active";

	protected final String PARAM_FORM = "form";
	protected final String VARIABLE_ID = "id";

	protected final String PAGE_ROOT = "redirect:" + getPageMain();
	private final String PAGE_ERROR = "error";

	public AbstractAppController() {
		super();
	}

	/**
	 * Configura um conversor para double em pt-BR, usado no campo de preço.
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Double.class, new CustomNumberEditor(
				Double.class, NumberFormat.getInstance(new Locale("pt", "BR")),
				true));
	}

	/**
	 * Ponto de entrada da aplicação ("/").
	 * 
	 * @param uiModel
	 *            recebe a lista de entidades.
	 * @return url para a pagina de listagem.
	 */
	public String listar(Model uiModel) {
		List<E> entities = getService().findAll();
		uiModel.addAttribute(ATTRIBUTE_PAGE_ACTIVE, getPageList());
		uiModel.addAttribute(getAttributeEntities(), entities);

		return getPageList();
	}

	/**
	 * Método executado antes de carregar a tela de inclusão da entidade.
	 * 
	 * @param uiModel
	 * @return url da página de inclusão.
	 */
	public String criarForm(Model uiModel) {
		try {
			E entity = AppUtil.getNewEntity(getEntityType());

			uiModel.addAttribute(getAttributeEntity(), entity);
			uiModel.addAttribute(ATTRIBUTE_PAGE_ACTIVE, getPageInclude());

			getLog().log(Level.INFO, "Pronto para incluir a entidade");

			return getPageInclude();
		} catch (AppException e) {
			getLog().log(Level.SEVERE, e.getMessage(), e);
		}
		return PAGE_ERROR;
	}

	/**
	 * Método executado na inserção da entidade.
	 * 
	 * @param entity
	 *            instância com os dados preenchidos na tela
	 * @param result
	 *            componente usado para verificar problemas com validação.
	 * @param uiModel
	 * @return a url para a listagem, se algum erro de validação for encontrado
	 *         volta para a pagina de inclusão.
	 */
	public String criar(E entity, BindingResult result, Model uiModel) {
		if (result.hasErrors()) {
			uiModel.addAttribute(getAttributeEntity(), entity);
			uiModel.addAttribute(ATTRIBUTE_PAGE_ACTIVE, getPageInclude());
			return getPageInclude();
		}

		getService().insert(entity);

		return PAGE_ROOT;
	}

	/**
	 * Método executado antes de carregar a tela de edição de entidades.
	 * 
	 * @param id
	 *            da entidade que deve ser editada.
	 * @param uiModel
	 *            armazena a entidade que deverá ser alterado.
	 * @return url da página de edição.
	 */
	public String editarForm(Long id, Model uiModel) {
		E entity = getService().findOne(id);

		if (entity != null) {
			uiModel.addAttribute(getAttributeEntity(), entity);
			getLog().log(Level.INFO, "Pronto para editar a entidade");

			return getPageEdit();
		}

		return PAGE_ERROR;
	}

	/**
	 * Método executado ao salvar a edição da entidade.
	 * 
	 * @param entity
	 *            objeto com os dados enviados pela tela.
	 * @param bindingResult
	 *            componente usado para verificar problemas com validação.
	 * @param uiModel
	 * @return a url para a listagem, se algum erro de validação for encontrado
	 *         volta para a pagina de edição.
	 */

	public String editar(E entity, BindingResult bindingResult, Model uiModel) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute(getAttributeEntity(), entity);
			return getPageEdit();
		}

		getService().save(entity);

		return PAGE_ROOT;
	}

	/**
	 * Método executado na exclusão da entidade.
	 * 
	 * @param id
	 *            da entidade que deverá ser removida.
	 * @param uiModel
	 * @return url da página de listagem.
	 */
	public String remover(Long id, Model uiModel) {
		E entity = getService().findOne(id);

		if (entity != null)
			getService().delete(entity);

		return PAGE_ROOT;
	}

	/**
	 * Método executado para sincronizar os dados do <code>DataSource</code>.
	 * Botão atualizar.
	 * 
	 * @return url da página de listagem.
	 */

	public String atualizar() {
		((AppService<?>) getService()).synch();
		return PAGE_ROOT;
	}

	protected abstract String getAttributeEntity();

	protected abstract String getAttributeEntities();

	protected abstract String getPageMain();

	protected abstract String getPageList();

	protected abstract String getPageInclude();

	protected abstract String getPageEdit();

}
