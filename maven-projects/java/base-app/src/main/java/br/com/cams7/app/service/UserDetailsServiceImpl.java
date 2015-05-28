package br.com.cams7.app.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.cams7.app.domain.entity.UsuarioEntity;
import br.com.cams7.app.domain.entity.UsuarioEntity.Autorizacao;
import br.com.cams7.app.repository.BaseUsuarioRepository;

public class UserDetailsServiceImpl implements UserDetailsService {

	private BaseUsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(final String username)
			throws UsernameNotFoundException {

		if (username == null || "".equals(username))
			throw new UsernameNotFoundException("username is empty or null");

		UsuarioEntity usuario = repository.findByUsername(username);
		Set<GrantedAuthority> authorities = buildUserAuthority(usuario
				.getAutorizacoes());

		return buildUserForAuthentication(usuario, authorities);
	}

	// Converts com.mkyong.users.model.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(UsuarioEntity usuario,
			Set<GrantedAuthority> authorities) {
		return new User(usuario.getNome(), usuario.getSenha(),
				usuario.isAtivo(), true, true, true, authorities);
	}

	private Set<GrantedAuthority> buildUserAuthority(Autorizacao[] autorizacoes) {

		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

		// Build user's authorities
		if (autorizacoes != null)
			for (Autorizacao autorizacao : autorizacoes)
				authorities.add(new SimpleGrantedAuthority(autorizacao.name()));

		return authorities;
	}

	public void setRepository(BaseUsuarioRepository repository) {
		this.repository = repository;
	}

}