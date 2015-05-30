/**
 * 
 */
package br.com.cams7.sisbarc.aal.service.impl;

import org.springframework.stereotype.Service;

import br.com.cams7.app.domain.entity.UsuarioEntity;
import br.com.cams7.gae.service.AbstractAppService;
import br.com.cams7.sisbarc.aal.ds.UsuarioDS;
import br.com.cams7.sisbarc.aal.repository.UsuarioRepository;
import br.com.cams7.sisbarc.aal.service.UsuarioService;

/**
 * @author cams7
 *
 */
@Service
public class UsuarioServiceImpl extends
		AbstractAppService<UsuarioRepository, UsuarioDS, UsuarioEntity>
		implements UsuarioService {

	public UsuarioServiceImpl() {
		super();
	}

}
