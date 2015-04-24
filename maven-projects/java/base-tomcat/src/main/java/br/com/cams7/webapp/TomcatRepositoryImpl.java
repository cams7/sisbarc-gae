package br.com.cams7.webapp;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.cams7.app.AbstractBase;
import br.com.cams7.domain.BaseEntity;

public abstract class TomcatRepositoryImpl<E extends BaseEntity<ID>, ID extends Serializable>
		extends AbstractBase<E> implements TomcatRepository<E, ID> {

	public TomcatRepositoryImpl() {
		super();
	}

	@Override
	public void save(E entity) {
		getEntityManager().persist(entity);
	}

	@Override
	public E update(E entity) {
		return getEntityManager().merge(entity);
	}

	// public void update(List<E> entities) {
	// for (E entity : entities)
	// update(entity);
	// }

	@Override
	public E remove(E entity) {
		entity = update(entity);
		getEntityManager().remove(entity);
		return entity;
	}

	@Override
	public E remove(ID id) {
		E entity = findOne(id);
		getEntityManager().remove(entity);
		return entity;
	}

	@Override
	public E findOne(ID id) {
		E entity = getEntityManager().find(getEntityType(), id);
		return entity;
	}

	@Override
	public List<E> findAll() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<E> cq = cb.createQuery(getEntityType());

		Root<E> from = cq.from(getEntityType());
		cq.select(from);

		TypedQuery<E> query = getEntityManager().createQuery(cq);

		List<E> entities = query.getResultList();
		return entities;
	}

	// public List<E> findRange(int[] range) {
	// CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
	// CriteriaQuery<E> cq = cb.createQuery(getEntityType());
	//
	// Root<E> from = cq.from(getEntityType());
	// cq.select(from);
	//
	// TypedQuery<E> query = getEntityManager().createQuery(cq);
	// query.setMaxResults(range[1] - range[0]);
	// query.setFirstResult(range[0]);
	//
	// List<E> entities = query.getResultList();
	// return entities;
	// }

	/**
	 * Exige a definição do <code>EntityManager</code> responsável pelas
	 * operações de persistência.
	 */
	protected abstract EntityManager getEntityManager();

}
