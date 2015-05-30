/**
 * 
 */
package br.com.cams7.webapp.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import br.com.cams7.app.AbstractBase;
import br.com.cams7.app.domain.AbstractEntity;
import br.com.cams7.webapp.repository.AppRepository;
import br.com.cams7.webapp.sequence.SequenceRepository;

/**
 * @author cesar
 *
 */
public abstract class AbstractAppService<R extends AppRepository<E>, E extends AbstractEntity>
		extends AbstractBase<E> implements AppService<E> {

	private final byte ENTITY_ARGUMENT_NUMBER = 1;

	@Autowired
	private R repository;

	@Autowired
	private SequenceRepository sequence;

	public AbstractAppService() {
		super();
	}

	@Override
	public List<E> findAll() {
		return repository.findAll();
	}

	@Override
	public List<E> findAll(Sort sort) {
		return repository.findAll(sort);
	}

	@Override
	public Page<E> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public Iterable<E> findAll(Iterable<Long> ids) {
		return repository.findAll(ids);
	}

	@Override
	public E findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public <T extends E> T insert(T entity) {
		entity.setId(getNextId());
		return repository.insert(entity);
	}

	@Override
	public <T extends E> List<T> insert(Iterable<T> entities) {
		for (E entity : entities)
			entity.setId(getNextId());

		return repository.insert(entities);
	}

	@Override
	public <T extends E> T save(T entity) {
		return repository.save(entity);
	}

	@Override
	public <T extends E> List<T> save(Iterable<T> entities) {
		return repository.save(entities);
	}

	@Override
	public long count() {
		return repository.count();
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public void delete(E entity) {
		repository.delete(entity);
	}

	@Override
	public void delete(Iterable<? extends E> entities) {
		repository.delete(entities);
	}

	public void deleteAll() {
		repository.deleteAll();
	}

	@Override
	public boolean exists(Long id) {
		return repository.exists(id);
	}

	@Override
	public Page<E> search(short first, byte pageSize, String sortField,
			Sort.Direction direction, Map<String, Object> filters) {

		Pageable pageable;
		if (direction != null)
			pageable = new PageRequest(first / pageSize, pageSize, direction,
					sortField);
		else
			pageable = new PageRequest(first / pageSize, pageSize);

		Page<E> page = findAll(pageable);
		return page;
	}

	private long getNextId() {
		long id = sequence.getNextId(getEntityType().getName());
		return id;
	}

	@Override
	protected byte getEntityArgumentNumber() {
		return ENTITY_ARGUMENT_NUMBER;
	}

	protected R getRepository() {
		return repository;
	}

}
