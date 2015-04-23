package br.com.cams7.app;

import java.io.Serializable;

import br.com.cams7.domain.BaseEntity;

public abstract class BaseWSControler<S extends BaseService<E, ID>, E extends BaseEntity<ID>, ID extends Serializable>
		extends BaseControler<S, E, ID> {

	public BaseWSControler() {
		super();
	}

}