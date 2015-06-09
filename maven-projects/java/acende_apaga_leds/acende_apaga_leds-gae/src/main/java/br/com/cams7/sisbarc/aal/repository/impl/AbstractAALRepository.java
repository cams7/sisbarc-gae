/**
 * 
 */
package br.com.cams7.sisbarc.aal.repository.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import br.com.cams7.app.domain.AbstractEntity;
import br.com.cams7.app.domain.entity.UserEntity;
import br.com.cams7.gae.repository.AbstractAppRepository;
import br.com.cams7.sisbarc.aal.repository.AALRepository;

import com.googlecode.objectify.cmd.Query;

/**
 * @author cams7
 *
 */
public abstract class AbstractAALRepository<E extends AbstractEntity> extends
		AbstractAppRepository<E> implements AALRepository<E> {

	public AbstractAALRepository() {
		super();
	}

	@Override
	public List<E> findAll(UserEntity user) {
		Query<E> query = ofy().load().type(getEntityType());
		query = query.filter("user", user);
		List<E> entities = query.list();
		return entities;
	}

}
