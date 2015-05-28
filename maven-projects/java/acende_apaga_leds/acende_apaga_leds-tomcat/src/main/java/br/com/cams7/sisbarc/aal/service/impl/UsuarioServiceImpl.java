package br.com.cams7.sisbarc.aal.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.cams7.app.domain.entity.UsuarioEntity;
import br.com.cams7.sisbarc.aal.repository.UsuarioRepository;
import br.com.cams7.sisbarc.aal.service.UsuarioService;
import br.com.cams7.webapp.AppServiceImpl;

@Service
public class UsuarioServiceImpl extends
		AppServiceImpl<UsuarioRepository, UsuarioEntity> implements
		UsuarioService {

	public UsuarioServiceImpl() {
		super();
	}

	@Override
	public UsuarioEntity findByUsername(String username) {
		return getRepository().findByUsername(username);
	}

	@Override
	public <S extends UsuarioEntity> S insert(S usuario) {
		String hashedPassword = getHashedPassword(usuario.getSenha());

		usuario.setSenha(hashedPassword);

		return super.insert(usuario);
	}

	@Override
	public <S extends UsuarioEntity> S save(S usuario) {
		String hashedPassword = getHashedPassword(usuario.getSenha());

		usuario.setSenha(hashedPassword);

		return super.save(usuario);
	}

	private String getHashedPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}

}
