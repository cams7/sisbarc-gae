/**
 * 
 */
package br.com.cams7.webapp;

import java.io.Serializable;

import br.com.cams7.app.BaseService;
import br.com.cams7.domain.BaseEntity;

/**
 * @author cesar
 *
 */
public interface TomcatService<E extends BaseEntity<ID>, ID extends Serializable>
		extends BaseService<E, ID>, TomcatRepository<E, ID> {

}
