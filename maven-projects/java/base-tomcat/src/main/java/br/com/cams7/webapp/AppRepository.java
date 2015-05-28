package br.com.cams7.webapp;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.cams7.app.domain.BaseEntity;
import br.com.cams7.app.repository.BaseRepository;

public interface AppRepository<E extends BaseEntity> extends BaseRepository<E>,
		MongoRepository<E, Long> {

}
