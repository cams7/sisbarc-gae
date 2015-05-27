package br.com.cams7.webapp;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.cams7.app.BaseRepository;
import br.com.cams7.domain.BaseEntity;

public interface AppRepository<E extends BaseEntity> extends BaseRepository<E>,
		MongoRepository<E, Long> {

}
