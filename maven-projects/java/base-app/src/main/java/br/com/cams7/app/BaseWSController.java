package br.com.cams7.app;

import java.io.Serializable;

import br.com.cams7.domain.BaseEntity;

public abstract class BaseWSController<S extends BaseService<E, ID>, E extends BaseEntity<ID>, ID extends Serializable>
		extends BaseController<S, E, ID> {

	public BaseWSController() {
		super();
	}

}