/**
 * 
 */
package br.com.cams7.gae.service;

import br.com.cams7.app.domain.AbstractEntity;
import br.com.cams7.app.service.BaseService;
import br.com.cams7.gae.repository.AppRepository;

/**
 * @author cesar
 *
 */
public interface AppService<E extends AbstractEntity> extends BaseService<E>,
		AppRepository<E> {

	void synch();

}
