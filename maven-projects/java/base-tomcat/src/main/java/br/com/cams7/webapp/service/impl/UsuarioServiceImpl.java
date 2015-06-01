package br.com.cams7.webapp.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.cams7.app.domain.entity.UsuarioEntity;
import br.com.cams7.app.domain.entity.UsuarioEntity.Autorizacao;
import br.com.cams7.webapp.repository.UsuarioRepository;
import br.com.cams7.webapp.service.AbstractAppService;
import br.com.cams7.webapp.service.UsuarioService;

@Service("usuarioService")
public class UsuarioServiceImpl extends
		AbstractAppService<UsuarioRepository, UsuarioEntity> implements
		UsuarioService, UserDetailsService {

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

	@Override
	public UserDetails loadUserByUsername(final String username)
			throws UsernameNotFoundException {

		if (username == null || "".equals(username))
			throw new UsernameNotFoundException("username is empty or null");

		UsuarioEntity usuario = getRepository().findByUsername(username);
		Set<GrantedAuthority> authorities = buildUserAuthority(usuario
				.getAutorizacoes());

		return buildUserForAuthentication(usuario, authorities);
	}

	// Converts com.mkyong.users.model.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(UsuarioEntity usuario,
			Set<GrantedAuthority> authorities) {
		String username = usuario.getNome();
		String password = usuario.getSenha();
		boolean enabled = usuario.isAtivo();

		return new User(username, password, enabled, true, true, true,
				authorities);
	}

	private Set<GrantedAuthority> buildUserAuthority(Autorizacao[] autorizacoes) {

		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

		// Build user's authorities
		if (autorizacoes != null)
			for (Autorizacao autorizacao : autorizacoes)
				authorities.add(new SimpleGrantedAuthority(autorizacao.name()));

		return authorities;
	}

}
