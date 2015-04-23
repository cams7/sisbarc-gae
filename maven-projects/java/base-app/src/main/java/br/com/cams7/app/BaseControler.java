package br.com.cams7.app;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import br.com.cams7.app.AbstractBase;
import br.com.cams7.domain.BaseEntity;

public abstract class BaseControler<S extends BaseService<E, ID>, E extends BaseEntity<ID>, ID extends Serializable>
		extends AbstractBase<E> {

	private final byte ENTITY_ARGUMENT_NUMBER = 1;

	/**
	 * Utiliza a injeção de dependência do <code>Spring Framework</code> para
	 * resolver a instancia do <code>Service/code>.
	 */
	@Autowired
	private S service;

	public BaseControler() {
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

	@Override
	protected byte getEntityArgumentNumber() {
		return ENTITY_ARGUMENT_NUMBER;
	}

	protected S getService() {
		return service;
	}

}
