/**
 * 
 */
package br.com.cams7.gae;

import java.io.Serializable;
import java.util.List;

import br.com.cams7.app.AbstractBase;
import br.com.cams7.domain.BaseEntity;

/**
 * @author cams7
 *
 */
public abstract class GaeRepositoryImpl<E extends BaseEntity<ID>, ID extends Serializable>
		extends AbstractBase<E> implements GaeRepository<E, ID> {

	public GaeRepositoryImpl() {
		super();
	}

	@Override
	public void save(E entity) {
		// ofy().save().entity(entity).now();
	}

	@Override
	public E update(E entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E remove(E entity) {
		// ofy().delete().entity(entity).now();
		// return entity;
		return null;
	}

	@Override
	public E findOne(ID id) {
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
