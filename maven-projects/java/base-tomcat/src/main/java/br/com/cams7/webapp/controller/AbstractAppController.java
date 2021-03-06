/**
 * 
 */
package br.com.cams7.webapp.controller;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import br.com.cams7.app.controller.AbstractController;
import br.com.cams7.app.domain.AbstractEntity;
import br.com.cams7.util.AppException;
import br.com.cams7.util.AppHelper;
import br.com.cams7.webapp.service.AppService;

/**
 * @author cams7
 *
 */
public abstract class AbstractAppController<S extends AppService<E>, E extends AbstractEntity>
		extends AbstractController<S, E> implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String RESOURCE_BUNDLE = "i18n/messages";
	protected final String PARAM_CHANGED = "changed";
	private final String PARAM_MESSAGE = "message";

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

	public AbstractAppController() {
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

				filters = AppHelper.removeEmptyArray(filters);

				if (pageSize != lastPageSize) {
					lastPageSize = (byte) pageSize;
				} else if ((first == 0 || first == lastPageFirst)) {
					if (sortField != null
							&& (!sortField.equals(lastSortField) || !sortOrder
									.equals(lastSortOrder))) {
						lastSortField = sortField;
						lastSortOrder = sortOrder;
					} else if (((!filters.isEmpty() || lastFilters != null) && !AppHelper
							.equalMaps(filters, lastFilters))) {

						if (!filters.isEmpty())
							lastFilters = filters;
						else
							lastFilters = null;
					}
				}

				lastPageFirst = (short) first;

				Sort.Direction direction = null;

				switch (lastSortOrder) {
				case ASCENDING:
					direction = Sort.Direction.ASC;
					break;
				case DESCENDING:
					direction = Sort.Direction.DESC;
					break;
				default:
					break;
				}

				Page<E> page = getService().search(lastPageFirst, lastPageSize,
						lastSortField, direction, lastFilters);

				entities = page.getContent();

				setRowCount((int) page.getTotalElements());

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
			setSelectedEntity(AppHelper.getNewEntity(getEntityType()));
			getLog().info("Nova entidade");
		} catch (AppException e) {
			getLog().log(Level.SEVERE, e.getMessage(), e);
		}
	}

	/**
	 * Operação acionada pela tela de inclusão, através do
	 * <code>commandButton</code> <strong>Salvar</strong>.
	 * 
	 * @return Se a inclusão foi realizada vai para listagem, senão permanece na
	 *         mesma tela.
	 */
	public String createEntity() {
		E entitity = getService().insert(getSelectedEntity());

		getLog().info("A entidade " + entitity + " foi salva");

		return getListPage();
	}

	public void updateEntity() {
		E entitity = getService().save(getSelectedEntity());

		RequestContext context = RequestContext.getCurrentInstance();
		context.addCallbackParam(PARAM_CHANGED, true);

		getLog().info("A entidade " + entitity + " foi alterada");
	}

	/**
	 * Operação acionada pela tela de edição, através do
	 * <code>commandButton</code> <strong>Excluir</strong>.
	 * 
	 * @return Se a exclusão for realizada vai para a listagem, senão permanece
	 *         na mesma tela.
	 */
	public void removeEntity() {
		getService().delete(getSelectedEntity().getId());

		RequestContext context = RequestContext.getCurrentInstance();
		context.addCallbackParam(PARAM_CHANGED, true);

		reset();
		getLog().info("A entidade " + getSelectedEntity() + " foi excluida");
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

		RequestContext context = RequestContext.getCurrentInstance();
		context.addCallbackParam(PARAM_MESSAGE, message);
	}

	protected void addINFOMessage(String summary, String detail) {
		addMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		getLog().info(detail);
	}

	protected void addWARNMessage(String summary, String detail) {
		addMessage(FacesMessage.SEVERITY_WARN, summary, detail);
		getLog().log(Level.WARNING, detail);
	}

	protected void addERRORMessage(String summary, String detail) {
		addMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
		getLog().log(Level.SEVERE, detail);
	}

	protected void addFATALMessage(String summary, String detail) {
		addMessage(FacesMessage.SEVERITY_FATAL, summary, detail);
		getLog().log(Level.SEVERE, detail);
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
