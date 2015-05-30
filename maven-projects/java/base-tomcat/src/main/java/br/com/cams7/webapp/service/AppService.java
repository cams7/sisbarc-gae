/**
 * 
 */
package br.com.cams7.webapp.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import br.com.cams7.app.domain.AbstractEntity;
import br.com.cams7.app.service.BaseService;
import br.com.cams7.webapp.repository.AppRepository;

/**
 * @author cesar
 *
 */
public interface AppService<E extends AbstractEntity> extends BaseService<E>,
		AppRepository<E> {

	public Page<E> search(short first, byte pageSize, String sortField,
			Sort.Direction direction, Map<String, Object> filters);

}
