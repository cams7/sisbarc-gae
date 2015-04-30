package br.com.cams7.sisbarc.aal.repository.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.cams7.gae.GaeRepositoryImpl;
import br.com.cams7.sisbarc.aal.domain.MercadoriaEntity;
import br.com.cams7.sisbarc.aal.repository.MercadoriaRepository;

import com.googlecode.objectify.Key;

/**
 * Implementa o contrato de persistência da entidade <code>Mercadoria</code>.
 * 
 * <p>
 * Nessa aplicação resolvemos a persistência utilizando o Objectify, um
 * framework de persistência para o App Engine.<br/>
 * A proposta do Objetify é denifir uma API mais alto-nível para manipular dados
 * no <code>DataStore</code> do App Engine.
 * </p>
 * 
 * <p>
 * <code>MercadoriaDAOObjectify</code> é anotado com <code>@Repository</code>,
 * dessa forma o Spring Framework gerencia o ciclo de vida dos objetos dessa
 * classe, além de permitir a injeção de dependência em outros componentes.
 * 
 * @see br.com.yaw.MercadoriaRepository.dao.MercadoriaDAO
 * @see com.googlecode.objectify.ObjectifyService.
 * 
 * @author YaW Tecnologia
 */
@Repository
public class MercadoriaRepositoryImpl extends
		GaeRepositoryImpl<MercadoriaEntity, Long> implements
		MercadoriaRepository {

	public MercadoriaRepositoryImpl() {
		super();
	}

	@Override
	public MercadoriaEntity insert(MercadoriaEntity mercadoria) {
		return save(mercadoria);
	}

	@Override
	public MercadoriaEntity save(MercadoriaEntity mercadoria) {
		ofy().save().entity(mercadoria).now();
		
		return mercadoria;
	}

	@Override
	public void delete(MercadoriaEntity mercadoria) {
		ofy().delete().entity(mercadoria).now();
	}

	@Override
	public MercadoriaEntity findOne(Long id) {
		Key<MercadoriaEntity> k = Key.create(MercadoriaEntity.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<MercadoriaEntity> findAll() {
		List<MercadoriaEntity> entities = ofy().load()
				.type(MercadoriaEntity.class).list();
		return entities;
	}

}
