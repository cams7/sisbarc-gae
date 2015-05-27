package br.com.cams7.app;

import br.com.cams7.domain.BaseEntity;

public abstract class BaseWSController<S extends BaseService<E>, E extends BaseEntity>
		extends BaseController<S, E> {

	public BaseWSController() {
		super();
	}

}