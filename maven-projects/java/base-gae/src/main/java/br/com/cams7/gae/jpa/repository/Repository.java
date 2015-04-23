/**
 * 
 */
package br.com.cams7.gae.jpa.repository;

import java.io.Serializable;

import br.com.cams7.app.jpa.repository.BaseRepository;
import br.com.cams7.jpa.domain.BaseEntity;

/**
 * @author cams7
 *
 */
public interface Repository<E extends BaseEntity<ID>, ID extends Serializable>
		extends BaseRepository<E, ID> {

}
