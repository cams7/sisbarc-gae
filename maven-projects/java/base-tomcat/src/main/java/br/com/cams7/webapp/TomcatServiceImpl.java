/**
 * 
 */
package br.com.cams7.webapp;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.com.cams7.app.AbstractBase;
import br.com.cams7.domain.BaseEntity;
import br.com.cams7.webapp.domain.SortOrderField;

/**
 * @author cesar
 *
 */
public abstract class TomcatServiceImpl<R extends TomcatRepository<E, ID>, E extends BaseEntity<ID>, ID extends Serializable>
		extends AbstractBase<E> implements TomcatService<E, ID> {

	private final byte ENTITY_ARGUMENT_NUMBER = 1;

	@Autowired
	private R repository;

	public TomcatServiceImpl() {
		super();
	}

	@Transactional
	public void save(E entity) {
		getRepository().save(entity);
	}

	@Transactional
	public E update(E entity) {
		entity = getRepository().update(entity);
		return entity;
	}

	// @Transactional
	// public void update(List<E> entities) {
	// getRepository().update(entities);
	// }

	@Transactional
	public E remove(E entity) {
		return getRepository().remove(entity);
	}

	@Transactional
	public E remove(ID id) {
		return getRepository().remove(id);
	}

	@Transactional(readOnly = true)
	public E findOne(ID id) {
		E entity = getRepository().findOne(id);
		return entity;
	}

	@Transactional(readOnly = true)
	public List<E> findAll() {
		List<E> entities = getRepository().findAll();
		return entities;
	}

	// @Transactional(readOnly = true)
	// public List<E> findRange(int[] range) {
	// List<E> entities = getRepository().findRange(range);
	// return entities;
	// }

	@Transactional(readOnly = true)
	@Override
	public long count() {
		long count = getRepository().count();
		return count;
	}

	@Transactional(readOnly = true)
	@Override
	public long count(Map<String, Object> filters) {
		long count = getRepository().count(filters);
		return count;
	}

	@Transactional(readOnly = true)
	@Override
	public List<E> search(short first, byte pageSize, String sortField,
			SortOrderField sortOrder, Map<String, Object> filters) {
		List<E> entities = getRepository().search(first, pageSize, sortField,
				sortOrder, filters);
		return entities;
	}

	@Override
	protected byte getEntityArgumentNumber() {
		return ENTITY_ARGUMENT_NUMBER;
	}

	protected R getRepository() {
		return repository;
	}

}
