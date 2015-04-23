/**
 * 
 */
package br.com.cams7.gae.service;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.cams7.app.AbstractBase;
import br.com.cams7.app.jpa.repository.BaseRepository;
import br.com.cams7.gae.ds.DataSource;
import br.com.cams7.jpa.domain.BaseEntity;
import br.com.cams7.util.AppException;
import br.com.cams7.util.AppUtil;

/**
 * @author cesar
 *
 */
public abstract class BaseServiceImpl<R extends BaseRepository<E, ID>, D extends DataSource<E, ID>, E extends BaseEntity<ID>, ID extends Serializable>
		extends AbstractBase<E> implements BaseService<E, ID> {

	private final byte DS_ARGUMENT_NUMBER = 1;
	private final byte ENTITY_ARGUMENT_NUMBER = 2;

	private Class<D> dsType;

	@Autowired
	private R repository;

	@SuppressWarnings("unchecked")
	public BaseServiceImpl() {
		super();

		dsType = (Class<D>) AppUtil.getType(this, DS_ARGUMENT_NUMBER);
	}

	@Transactional
	public void save(E entity) {
		getRepository().save(entity);
		getDataSource().save(entity);

		getLog().debug("Included: " + entity);
	}

	@Transactional
	public E update(E entity) {
		entity = getRepository().update(entity);
		entity = getDataSource().update(entity);

		getLog().debug("Updated: " + entity);

		return entity;
	}

	// @Transactional
	// public void update(List<E> entities) {
	// getRepository().update(entities);
	// }

	// @Transactional
	// public E remove(ID id) {
	// return getRepository().remove(id);
	// }

	@Transactional
	public E remove(E entity) {
		entity = getRepository().remove(entity);
		entity = getDataSource().remove(entity);

		getLog().debug("Removed: " + entity);
		return entity;
	}

	@Transactional(readOnly = true)
	public E findOne(ID id) {
		E entity = getRepository().findOne(id);
		return entity;
	}

	@Override
	public List<E> findAll() {
		List<E> entities = getDataSource().findAll();
		return entities;
	}

	// @Transactional(readOnly = true)
	// public List<E> findRange(int[] range) {
	// List<E> entities = getRepository().findRange(range);
	// return entities;
	// }

	@Override
	public void synch() {
		getDataSource().synch(getRepository().findAll());
	}

	@Override
	protected byte getEntityArgumentNumber() {
		return ENTITY_ARGUMENT_NUMBER;
	}

	protected R getRepository() {
		return repository;
	}

	/**
	 * O <code>DataSource</code> da entidade é armazenado na sessão do usuário.
	 * Esse método é responsável por recuperar esse objeto e deixá-lo pronto
	 * para uso.
	 * 
	 * @return <code>MercadoriaDataSource</code> da sessão do usuário.
	 */
	@SuppressWarnings("unchecked")
	protected D getDataSource() {
		final String ATTRIBUTE_DS = this.getClass().getSimpleName() + "_DS";

		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		D ds = (D) session.getAttribute(ATTRIBUTE_DS);
		if (ds == null) {
			try {
				ds = (D) AppUtil.getNewDataSource(dsType);
				ds.synch(getRepository().findAll());
				session.setAttribute(ATTRIBUTE_DS, ds);
			} catch (AppException e) {
				getLog().fatal(e.getMessage(), e);
			}

		}
		return ds;
	}
}
