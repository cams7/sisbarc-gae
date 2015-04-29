package br.com.cams7.webapp;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import br.com.cams7.app.BaseRepository;
import br.com.cams7.domain.BaseEntity;
import br.com.cams7.webapp.domain.SortOrderField;

public interface TomcatRepository<E extends BaseEntity<ID>, ID extends Serializable>
		extends BaseRepository<E, ID> {

	public long count();

	public long count(Map<String, Object> filters);

	public List<E> search(short first, byte pageSize, String sortField,
			SortOrderField sortOrder, Map<String, Object> filters);

}
