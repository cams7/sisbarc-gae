package br.com.cams7.sisbarc.aal.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cams7.sisbarc.aal.domain.UsuarioEntity;
import br.com.cams7.webapp.AppRepository;

@Repository
public interface UsuarioRepository extends AppRepository<UsuarioEntity, String> {

	@Query("{ 'login' : ?0 }")
	public UsuarioEntity findByUsername(String username);

}