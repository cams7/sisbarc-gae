/**
 * 
 */
package br.com.cams7.gae.repository.impl;

import org.springframework.stereotype.Repository;

import br.com.cams7.app.domain.entity.UsuarioEntity;
import br.com.cams7.app.domain.entity.UsuarioEntity.Autorizacao;
import br.com.cams7.gae.repository.AbstractAppRepository;
import br.com.cams7.gae.repository.UsuarioRepository;

/**
 * @author cams7
 *
 */
@Repository("usuarioRepository")
public class UsuarioRepositoryImpl extends AbstractAppRepository<UsuarioEntity>
		implements UsuarioRepository {

	/**
	 * 
	 */
	public UsuarioRepositoryImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.app.repository.BaseUsuarioRepository#findByUsername(java
	 * .lang.String)
	 */
	@Override
	public UsuarioEntity findByUsername(String username) {
		UsuarioEntity usuario = new UsuarioEntity();
		usuario.setNome(username);

		usuario.setSenha("12345");
		usuario.setAtivo(true);
		usuario.setAutorizacoes(Autorizacao.values());

		return usuario;

	}

}
