package br.com.cams7.sisbarc.aal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.cams7.sisbarc.aal.domain.UserEntity;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {

}
