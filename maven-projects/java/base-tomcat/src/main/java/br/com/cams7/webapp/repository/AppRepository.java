package br.com.cams7.webapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.cams7.app.domain.AbstractEntity;
import br.com.cams7.app.repository.BaseRepository;

public interface AppRepository<E extends AbstractEntity> extends BaseRepository<E>,
		MongoRepository<E, Long> {

}
