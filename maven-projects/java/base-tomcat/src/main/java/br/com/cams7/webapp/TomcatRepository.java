package br.com.cams7.webapp;

import java.io.Serializable;

import br.com.cams7.app.BaseRepository;
import br.com.cams7.domain.BaseEntity;

public interface TomcatRepository<E extends BaseEntity<ID>, ID extends Serializable>
		extends BaseRepository<E, ID> {

}
