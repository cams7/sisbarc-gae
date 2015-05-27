/**
 * 
 */
package br.com.cams7.gae;

import br.com.cams7.app.BaseService;
import br.com.cams7.domain.BaseEntity;

/**
 * @author cesar
 *
 */
public interface GaeService<E extends BaseEntity> extends BaseService<E>,
		GaeRepository<E> {

	void synch();

}
