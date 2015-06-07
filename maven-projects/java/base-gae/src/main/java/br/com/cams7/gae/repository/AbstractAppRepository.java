/**
 * 
 */
package br.com.cams7.gae.repository;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import br.com.cams7.app.AbstractBase;
import br.com.cams7.app.domain.AbstractEntity;

import com.googlecode.objectify.Key;

/**
 * @author cams7
 *
 */
public abstract class AbstractAppRepository<E extends AbstractEntity> extends
		AbstractBase<E> implements AppRepository<E> {

	public AbstractAppRepository() {
		super();
	}

	@Override
	public E insert(E entity) {
		return save(entity);
	}

	@Override
	public E save(E entity) {
		ofy().save().entity(entity).now();
		return entity;
	}

	@Override
	public void save(List<E> entities) {
		for (E entity : entities)
			save(entity);
	}

	@Override
	public void delete(E entity) {
		ofy().delete().entity(entity).now();
	}

	@Override
	public E findOne(Long id) {
		Key<E> k = Key.create(getEntityType(), id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<E> findAll() {
		List<E> entities = ofy().load().type(getEntityType()).list();
		return entities;
	}

}
