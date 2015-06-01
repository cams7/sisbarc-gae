package br.com.cams7.webapp.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cams7.app.domain.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends AppRepository<UsuarioEntity> {

	@Query("{ 'login' : ?0 }")
	public UsuarioEntity findByUsername(String username);

}