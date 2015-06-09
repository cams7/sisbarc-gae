/**
 * 
 */
package br.com.cams7.sisbarc.aal.repository;

import java.util.List;

import br.com.cams7.app.domain.AbstractEntity;
import br.com.cams7.app.domain.entity.UserEntity;
import br.com.cams7.gae.repository.AppRepository;

/**
 * @author cams7
 *
 */
public interface AALRepository<E extends AbstractEntity> extends
		AppRepository<E> {

	public List<E> findAll(UserEntity user);

}
