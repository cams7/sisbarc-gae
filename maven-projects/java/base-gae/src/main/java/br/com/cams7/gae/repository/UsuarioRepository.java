/**
 * 
 */
package br.com.cams7.gae.repository;

import br.com.cams7.app.domain.entity.UsuarioEntity;

/**
 * @author cams7
 *
 */
public interface UsuarioRepository extends AppRepository<UsuarioEntity> {
	public UsuarioEntity findByUsername(String username);
}
