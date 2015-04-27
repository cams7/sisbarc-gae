package br.com.cams7.app;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.cams7.domain.BaseEntity;

public abstract class BaseController<S extends BaseService<E, ID>, E extends BaseEntity<ID>, ID extends Serializable>
		extends AbstractBase<E> {

	private final byte ENTITY_ARGUMENT_NUMBER = 1;

	/**
	 * Utiliza a injeção de dependência do <code>Spring Framework</code> para
	 * resolver a instancia do <code>Service/code>.
	 */
	@Autowired
	private S service;

	public BaseController() {
		super();
	}

	@Override
	protected byte getEntityArgumentNumber() {
		return ENTITY_ARGUMENT_NUMBER;
	}

	protected S getService() {
		return service;
	}

}
