package br.com.cams7.webapp.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cams7.app.domain.entity.UserEntity;

@Repository
public interface UserRepository extends AppRepository<UserEntity> {

	@Query("{ 'email' : ?0 }")
	public UserEntity findByEmail(String email);

}