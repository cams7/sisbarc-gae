package br.com.cams7.webapp.service;

import br.com.cams7.app.domain.entity.UsuarioEntity;
import br.com.cams7.webapp.repository.UsuarioRepository;

public interface UsuarioService extends AppService<UsuarioEntity>,
		UsuarioRepository {

}
