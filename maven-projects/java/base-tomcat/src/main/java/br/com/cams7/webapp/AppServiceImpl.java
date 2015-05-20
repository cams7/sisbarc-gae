/**
 * 
 */
package br.com.cams7.webapp;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import br.com.cams7.app.AbstractBase;
import br.com.cams7.domain.BaseEntity;

/**
 * @author cesar
 *
 */
public abstract class AppServiceImpl<R extends AppRepository<E, ID>, E extends BaseEntity<ID>, ID extends Serializable>
		extends AbstractBase<E> implements AppService<E, ID> {

	private final byte ENTITY_ARGUMENT_NUMBER = 1;

	@Autowired
	private R repository;

	public AppServiceImpl() {
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
	public Iterable<E> findAll(Iterable<ID> i) {
		return repository.findAll(i);
	}

	@Override
	public E findOne(ID id) {
		return repository.findOne(id);
	}

	@Override
	public <T extends E> T insert(T entity) {
		return repository.insert(entity);
	}

	@Override
	public <T extends E> List<T> insert(Iterable<T> i) {
		return repository.insert(i);
	}

	@Override
	public <T extends E> T save(T entity) {
		return repository.save(entity);
	}

	@Override
	public <T extends E> List<T> save(Iterable<T> i) {
		return repository.save(i);
	}

	@Override
	public long count() {
		return repository.count();
	}

	@Override
	public void delete(ID id) {
		repository.delete(id);
	}

	@Override
	public void delete(E entity) {
		repository.delete(entity);
	}

	@Override
	public void delete(Iterable<? extends E> i) {
		repository.delete(i);
	}

	public void deleteAll() {
		repository.deleteAll();
	}

	@Override
	public boolean exists(ID id) {
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

	@Override
	protected byte getEntityArgumentNumber() {
		return ENTITY_ARGUMENT_NUMBER;
	}

	protected R getRepository() {
		return repository;
	}

}
