/**
 * 
 */
package br.com.cams7.gae;

import br.com.cams7.app.domain.BaseEntity;
import br.com.cams7.app.service.BaseService;

/**
 * @author cesar
 *
 */
public interface GaeService<E extends BaseEntity> extends BaseService<E>,
		GaeRepository<E> {

	void synch();

}
