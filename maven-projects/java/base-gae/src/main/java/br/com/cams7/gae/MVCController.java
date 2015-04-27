/**
 * 
 */
package br.com.cams7.gae;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import br.com.cams7.app.BaseController;
import br.com.cams7.app.BaseService;
import br.com.cams7.domain.BaseEntity;

/**
 * @author cams7
 *
 */
public abstract class MVCController<S extends BaseService<E, ID>, E extends BaseEntity<ID>, ID extends Serializable>
		extends BaseController<S, E, ID> {

	public MVCController() {
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
