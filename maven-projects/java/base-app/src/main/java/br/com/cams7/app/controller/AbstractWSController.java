package br.com.cams7.app.controller;

import br.com.cams7.app.domain.AbstractEntity;
import br.com.cams7.app.service.BaseService;

public abstract class AbstractWSController<S extends BaseService<E>, E extends AbstractEntity>
		extends AbstractController<S, E> {

	public AbstractWSController() {
		super();
	}

}