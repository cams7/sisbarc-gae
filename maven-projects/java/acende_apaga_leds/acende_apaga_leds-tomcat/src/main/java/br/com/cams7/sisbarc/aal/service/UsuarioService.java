package br.com.cams7.sisbarc.aal.service;

import br.com.cams7.app.domain.entity.UsuarioEntity;
import br.com.cams7.sisbarc.aal.repository.UsuarioRepository;
import br.com.cams7.webapp.service.AppService;

public interface UsuarioService extends AppService<UsuarioEntity>,
		UsuarioRepository {

}
