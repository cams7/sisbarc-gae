/**
 * 
 */
package br.com.cams7.gae;

import java.io.Serializable;

import br.com.cams7.app.BaseRepository;
import br.com.cams7.domain.BaseEntity;

/**
 * @author cams7
 *
 */
public interface GaeRepository<E extends BaseEntity<ID>, ID extends Serializable>
		extends BaseRepository<E, ID> {

}
