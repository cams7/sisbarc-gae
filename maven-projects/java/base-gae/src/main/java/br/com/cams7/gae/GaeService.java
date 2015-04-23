/**
 * 
 */
package br.com.cams7.gae;

import java.io.Serializable;

import br.com.cams7.app.BaseService;
import br.com.cams7.domain.BaseEntity;

/**
 * @author cesar
 *
 */
public interface GaeService<E extends BaseEntity<ID>, ID extends Serializable>
		extends BaseService<E, ID> {

	void synch();

}
