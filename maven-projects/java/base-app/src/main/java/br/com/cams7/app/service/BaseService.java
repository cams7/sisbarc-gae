package br.com.cams7.app.service;

import br.com.cams7.app.domain.AbstractEntity;
import br.com.cams7.app.repository.BaseRepository;

/**
 * @author cesar
 *
 */
public interface BaseService<E extends AbstractEntity> extends BaseRepository<E> {

}
