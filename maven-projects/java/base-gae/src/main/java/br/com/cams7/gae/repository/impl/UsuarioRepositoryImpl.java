/**
 * 
 */
package br.com.cams7.gae.repository.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.cams7.app.domain.entity.UsuarioEntity;
import br.com.cams7.gae.repository.AbstractAppRepository;
import br.com.cams7.gae.repository.UsuarioRepository;

/**
 * @author cams7
 *
 */
@Repository
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
		List<UsuarioEntity> usuarios = findAll();
		for (UsuarioEntity usuario : usuarios)
			if (usuario.getNome().equals(username))
				return usuario;

		return null;
	}

}
