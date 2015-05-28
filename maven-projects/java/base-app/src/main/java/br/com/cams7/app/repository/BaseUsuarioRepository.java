/**
 * 
 */
package br.com.cams7.app.repository;

import br.com.cams7.app.domain.entity.UsuarioEntity;

/**
 * @author cams7
 *
 */
public interface BaseUsuarioRepository extends BaseRepository<UsuarioEntity> {

	public UsuarioEntity findByUsername(String username);

}
