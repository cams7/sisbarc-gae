/**
 * 
 */
package br.com.cams7.webapp;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import br.com.cams7.app.BaseService;
import br.com.cams7.domain.BaseEntity;

/**
 * @author cesar
 *
 */
public interface TomcatService<E extends BaseEntity<ID>, ID extends Serializable>
		extends BaseService<E, ID>, TomcatRepository<E, ID> {

	public Page<E> search(short first, byte pageSize, String sortField,
			Sort.Direction direction, Map<String, Object> filters);

}
