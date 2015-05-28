package br.com.cams7.app.service;

import br.com.cams7.app.domain.BaseEntity;
import br.com.cams7.app.repository.BaseRepository;

/**
 * @author cesar
 *
 */
public interface BaseService<E extends BaseEntity> extends BaseRepository<E> {

}
