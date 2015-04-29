/**
 * 
 */
package br.com.cams7.webapp;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.cams7.app.BaseController;
import br.com.cams7.domain.BaseEntity;
import br.com.cams7.util.AppException;
import br.com.cams7.util.AppUtil;
import br.com.cams7.webapp.domain.SortOrderField;

/**
 * @author cams7
 *
 */
public abstract class TomcatController<S extends TomcatService<E, ID>, E extends BaseEntity<ID>, ID extends Serializable>
		extends BaseController<S, E, ID> implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String RESOURCE_BUNDLE = "i18n/messages";
	private final String CALLBACK_PARAM = "changedEntity";

	/**
	 * Mantém as entidades apresentadas na listagem.
	 */
	private LazyDataModel<E> lazyModel;

	/**
	 * Referência para a entidade que utilizada na seleção de um registro,
	 * inclusão (nova), edição e exclusão.
	 */
	private E selectedEntity;

	private final short PAGE_FIRST = 0;
	private final byte PAGE_SIZE = 10;

	private int totalRows;

	private short lastPageFirst;
	private byte lastPageSize;

	private String lastSortField;
	private SortOrder lastSortOrder;

	private Map<String, Object> lastFilters;

	public TomcatController() {
		super();
	}

	@PostConstruct
	private void init() {
		lastPageFirst = PAGE_FIRST;
		lastPageSize = PAGE_SIZE;

		lastSortOrder = SortOrder.UNSORTED;

		lazyModel = new LazyDataModel<E>() {

			private static final long serialVersionUID = 1L;

			private List<E> entities;

			@Override
			public E getRowData(String rowKey) {
				for (E entity : entities) {
					if (String.valueOf(entity.getId()).equals(rowKey))
						return entity;
				}

				return null;
			}

			@Override
			public Object getRowKey(E entity) {
				return String.valueOf(entity.getId());
			}

			@Override
			public List<E> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, Object> filters) {

				filters = AppUtil.removeEmptyArray(filters);
				boolean rowCountChanged = false;

				if (pageSize != lastPageSize) {
					lastPageSize = (byte) pageSize;
				} else if ((first == 0 || first == lastPageFirst)) {
					if (sortField != null
							&& (!sortField.equals(lastSortField) || !sortOrder
									.equals(lastSortOrder))) {
						lastSortField = sortField;
						lastSortOrder = sortOrder;
					} else if (((!filters.isEmpty() || lastFilters != null) && !AppUtil
							.equalMaps(filters, lastFilters))) {

						if (!filters.isEmpty())
							lastFilters = filters;
						else
							lastFilters = null;

						rowCountChanged = true;
					}
				}

				lastPageFirst = (short) first;

				entities = getService().search(lastPageFirst, lastPageSize,
						lastSortField,
						SortOrderField.valueOf(lastSortOrder.name()),
						lastFilters);

				// rowCount
				if (rowCountChanged)
					setRowCount((int) getService().count(lastFilters));

				return entities;
			}
		};

		reset();
	}

	/**
	 * Operação acionada toda a vez que a tela de listagem for carregada.
	 */
	public void reset() {
		totalRows = (int) getService().count();
		lazyModel.setRowCount(totalRows);
	}

	/**
	 * Ação executada quando a página de inclusão da entidade for carregada.
	 */
	public void includeNewEntity() {
		try {
			setSelectedEntity(AppUtil.getNewEntity(getEntityType()));
			getLog().debug("Nova entidade");
		} catch (AppException e) {
			getLog().fatal(e.getMessage(), e);
		}
	}

	/**
	 * Operação acionada pela tela de inclusão, através do
	 * <code>commandButton</code> <strong>Salvar</strong>.
	 * 
	 * @return Se a inclusão foi realizada vai para listagem, senão permanece na
	 *         mesma tela.
	 */
	public String saveEntity() {
		getService().save(getSelectedEntity());

		// addERRORMessage(getMessageFromI18N("msg.erro.salvar.funcionario"),
		// e.getMessage());

		getLog().debug("A entidade " + getSelectedEntity() + " foi salva");

		return getListPage();
	}

	public void updateEntity() {
		RequestContext context = RequestContext.getCurrentInstance();

		getService().update(getSelectedEntity());
		context.addCallbackParam(CALLBACK_PARAM, true);

		// context.addCallbackParam(CALLBACK_PARAM, false);
		// addERRORMessage(getMessageFromI18N("msg.erro.salvar.funcionario"),
		// e.getMessage());

		getLog().debug("A entidade " + getSelectedEntity() + " foi alterada");
	}

	/**
	 * Operação acionada pela tela de edição, através do
	 * <code>commandButton</code> <strong>Excluir</strong>.
	 * 
	 * @return Se a exclusão for realizada vai para a listagem, senão permanece
	 *         na mesma tela.
	 */
	public void removeEntity() {
		RequestContext context = RequestContext.getCurrentInstance();

		getService().remove(getSelectedEntity());
		context.addCallbackParam(CALLBACK_PARAM, true);

		// context.addCallbackParam(CALLBACK_PARAM, false);
		// addERRORMessage(getMessageFromI18N("msg.erro.remover.funcionario"),
		// e.getMessage());
		reset();
		getLog().debug("A entidade " + getSelectedEntity() + " foi excluida");
	}

	public void onRowSelect(SelectEvent event) {
		@SuppressWarnings("unchecked")
		E selectedEntity = (E) event.getObject();
		setSelectedEntity(selectedEntity);

		getLog().info("A entidade " + getSelectedEntity() + " foi selecionada");
	}

	public void handleClose(CloseEvent event) {
		setSelectedEntity(null);
	}

	private void addMessage(Severity severity, String summary, String detail) {
		FacesMessage message = new FacesMessage(severity, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	protected void addINFOMessage(String summary, String detail) {
		addMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		getLog().info(detail);
	}

	protected void addWARNMessage(String summary, String detail) {
		addMessage(FacesMessage.SEVERITY_WARN, summary, detail);
		getLog().warn(detail);
	}

	protected void addERRORMessage(String summary, String detail) {
		addMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
		getLog().error(detail);
	}

	/**
	 * @param key
	 * @return Recupera a mensagem do arquivo properties
	 *         <code>ResourceBundle</code>.
	 */
	protected String getMessageFromI18N(String key, Object... params) {
		Locale locale = FacesContext.getCurrentInstance().getViewRoot()
				.getLocale();

		ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE,
				locale, this.getClass().getClassLoader());

		String message;
		if (params.length > 0)
			message = MessageFormat.format(bundle.getString(key), params);
		else
			message = bundle.getString(key);

		return message;
	}

	public LazyDataModel<E> getLazyModel() {
		return lazyModel;
	}

	public E getSelectedEntity() {
		return selectedEntity;
	}

	public void setSelectedEntity(E selectedEntity) {
		this.selectedEntity = selectedEntity;
	}

	/**
	 * @return the first
	 */
	public short getFirst() {
		return lastPageFirst;
	}

	/**
	 * @return
	 */
	public byte getRows() {
		return lastPageSize;
	}

	/**
	 * @return the rowCount
	 */
	public int getTotalRows() {
		return totalRows;
	}

	protected abstract String getListPage();

}
