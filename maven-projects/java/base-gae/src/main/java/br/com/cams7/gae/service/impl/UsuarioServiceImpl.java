/**
 * 
 */
package br.com.cams7.gae.service.impl;

import java.util.HashSet;
import java.util.Set;

//import javax.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cams7.app.domain.entity.UsuarioEntity;
import br.com.cams7.app.domain.entity.UsuarioEntity.Autorizacao;
import br.com.cams7.gae.ds.UsuarioDS;
import br.com.cams7.gae.repository.UsuarioRepository;
import br.com.cams7.gae.service.AbstractAppService;
import br.com.cams7.gae.service.UsuarioService;

/**
 * @author cams7
 *
 */
@Service("usuarioService")
public class UsuarioServiceImpl extends
		AbstractAppService<UsuarioRepository, UsuarioDS, UsuarioEntity>
		implements UsuarioService, UserDetailsService {

	public UsuarioServiceImpl() {
		super();
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(final String username)
			throws UsernameNotFoundException {

		if (username == null || "".equals(username))
			throw new UsernameNotFoundException("username is empty or null");

		// UsuarioEntity usuario = getRepository().findByUsername(username);
		UsuarioEntity usuario = new UsuarioEntity();
		usuario.setNome(username);
		usuario.setSenha("$2a$10$j9Rae1utAPKuTZaK.UYHqeyiqlmXmXuJSmX1AhJrgqM7mj4S31v8O");
		usuario.setAtivo(true);
		usuario.setAutorizacoes(Autorizacao.values());

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
