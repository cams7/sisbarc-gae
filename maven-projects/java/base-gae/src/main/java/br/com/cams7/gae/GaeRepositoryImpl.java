/**
 * 
 */
package br.com.cams7.gae;

import java.util.List;

import br.com.cams7.app.AbstractBase;
import br.com.cams7.app.domain.BaseEntity;

/**
 * @author cams7
 *
 */
public abstract class GaeRepositoryImpl<E extends BaseEntity> extends
		AbstractBase<E> implements GaeRepository<E> {

	public GaeRepositoryImpl() {
		super();
	}

	@Override
	public E insert(E entity) {
		// ofy().save().entity(entity).now();
		return null;
	}

	@Override
	public E save(E entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(E entity) {
	}

	@Override
	public E findOne(Long id) {
		// TODO: Corrigir erro
		// Key<E> k = Key.create(getEntityType(), id);
		// return ofy().load().key(k).now();
		return null;
	}

	@Override
	public List<E> findAll() {
		// List<E> entities = ofy().load().type(getEntityType()).list();
		// return entities;
		return null;
	}

}
