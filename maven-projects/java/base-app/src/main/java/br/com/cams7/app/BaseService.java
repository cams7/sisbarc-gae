package br.com.cams7.app;

import java.io.Serializable;

import br.com.cams7.domain.BaseEntity;

/**
 * @author cesar
 *
 */
public interface BaseService<E extends BaseEntity<ID>, ID extends Serializable>
		extends BaseRepository<E, ID> {

}
