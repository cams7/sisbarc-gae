/**
 * 
 */
package br.com.cams7.sisbarc.aal.repository.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.cams7.app.domain.entity.UsuarioEntity;
import br.com.cams7.gae.repository.AppRepositoryImpl;
import br.com.cams7.sisbarc.aal.repository.UsuarioRepository;

import com.googlecode.objectify.Key;

/**
 * @author cams7
 *
 */
@Repository("usuarioRepository")
public class UsuarioRepositoryImpl extends AppRepositoryImpl<UsuarioEntity>
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

	@Override
	public UsuarioEntity insert(UsuarioEntity usuario) {
		return save(usuario);
	}

	@Override
	public UsuarioEntity save(UsuarioEntity usuario) {
		ofy().save().entity(usuario).now();

		return usuario;
	}

	@Override
	public void delete(UsuarioEntity usuario) {
		ofy().delete().entity(usuario).now();
	}

	@Override
	public UsuarioEntity findOne(Long id) {
		Key<UsuarioEntity> k = Key.create(UsuarioEntity.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<UsuarioEntity> findAll() {
		List<UsuarioEntity> usuarios = ofy().load().type(UsuarioEntity.class)
				.list();
		return usuarios;
	}

}
