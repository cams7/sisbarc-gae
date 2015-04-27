/**
 * 
 */
package br.com.cams7.webapp;

import java.io.Serializable;

import br.com.cams7.app.BaseController;
import br.com.cams7.app.BaseService;
import br.com.cams7.domain.BaseEntity;

/**
 * @author cams7
 *
 */
public abstract class BaseJSFController<S extends BaseService<E, ID>, E extends BaseEntity<ID>, ID extends Serializable>
		extends BaseController<S, E, ID> {

	public BaseJSFController() {
		super();
	}

}
