/**
 * 
 */
package br.com.cams7.gae.service;

import java.io.Serializable;

import br.com.cams7.gae.jpa.repository.Repository;
import br.com.cams7.jpa.domain.BaseEntity;

/**
 * @author cesar
 *
 */
public interface BaseService<E extends BaseEntity<ID>, ID extends Serializable>
		extends Repository<E, ID> {

	void synch();

}
