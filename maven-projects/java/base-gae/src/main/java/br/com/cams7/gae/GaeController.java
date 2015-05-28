/**
 * 
 */
package br.com.cams7.gae;

import java.text.NumberFormat;
import java.util.Locale;

import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import br.com.cams7.app.controller.BaseController;
import br.com.cams7.app.domain.BaseEntity;

/**
 * @author cams7
 *
 */
public abstract class GaeController<S extends GaeService<E>, E extends BaseEntity>
		extends BaseController<S, E> {

	public GaeController() {
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

}
