/**
 * 
 */
package br.com.cams7.sisbarc.aal.repository;

import br.com.cams7.app.domain.entity.UsuarioEntity;
import br.com.cams7.app.repository.BaseUsuarioRepository;
import br.com.cams7.gae.AppRepository;

/**
 * @author cams7
 *
 */
public interface UsuarioRepository extends AppRepository<UsuarioEntity>,
		BaseUsuarioRepository {

}
