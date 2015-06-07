/**
 * 
 */
package br.com.cams7.gae.service;

import java.util.List;
import java.util.logging.Level;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.cams7.app.AbstractBase;
import br.com.cams7.app.domain.AbstractEntity;
import br.com.cams7.gae.ds.AbstractDS;
import br.com.cams7.gae.repository.AppRepository;
import br.com.cams7.util.AppException;
import br.com.cams7.util.AppUtil;

/**
 * @author cesar
 *
 */
public abstract class AbstractAppService<R extends AppRepository<E>, D extends AbstractDS<E>, E extends AbstractEntity>
		extends AbstractBase<E> implements AppService<E> {

	private final byte DS_ARGUMENT_NUMBER = 1;
	private final byte ENTITY_ARGUMENT_NUMBER = 2;

	private Class<D> dsType;

	@Autowired
	private R repository;

	@SuppressWarnings("unchecked")
	public AbstractAppService() {
		super();

		dsType = (Class<D>) AppUtil.getType(this, DS_ARGUMENT_NUMBER);
	}

	@Override
	public E insert(E entity) {
		getRepository().insert(entity);
		getDataSource().insert(entity);

		getLog().log(Level.INFO, "Included: " + entity);

		return entity;
	}

	@Override
	public E save(E entity) {
		entity = getRepository().save(entity);
		entity = getDataSource().save(entity);

		getLog().log(Level.INFO, "Updated: " + entity);

		return entity;
	}

	@Override
	public void save(List<E> entities) {
		getRepository().save(entities);
		getDataSource().save(entities);

		getLog().log(Level.INFO, "Updated: " + entities);
	}

	@Override
	public void delete(E entity) {
		getRepository().delete(entity);
		getDataSource().delete(entity);

		getLog().log(Level.INFO, "Removed: " + entity);
	}

	@Override
	public E findOne(Long id) {
		E entity = getRepository().findOne(id);
		return entity;
	}

	@Override
	public List<E> findAll() {
		List<E> entities = getDataSource().findAll();
		return entities;
	}

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
				getLog().log(Level.SEVERE, e.getMessage(), e);
			}

		}
		return ds;
	}
}
