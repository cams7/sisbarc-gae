package br.com.cams7.app.controller;

import br.com.cams7.app.domain.BaseEntity;
import br.com.cams7.app.service.BaseService;

public abstract class BaseWSController<S extends BaseService<E>, E extends BaseEntity>
		extends BaseController<S, E> {

	public BaseWSController() {
		super();
	}

}