package br.com.cams7.sisbarc.aal.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.cams7.sisbarc.aal.domain.UsuarioEntity;
import br.com.cams7.sisbarc.aal.domain.UsuarioEntity.Autorizacao;
import br.com.cams7.sisbarc.aal.service.UsuarioService;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	// get user from the database, via Hibernate
	@Autowired
	private UsuarioService service;

	@Override
	public UserDetails loadUserByUsername(final String username)
			throws UsernameNotFoundException {

		if (username == null || "".equals(username))
			throw new UsernameNotFoundException("username is empty or null");

		UsuarioEntity usuario = service.findByUsername(username);
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
}