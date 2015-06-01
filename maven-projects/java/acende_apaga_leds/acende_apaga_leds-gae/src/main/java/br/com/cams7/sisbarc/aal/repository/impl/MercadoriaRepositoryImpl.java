package br.com.cams7.sisbarc.aal.repository.impl;

import org.springframework.stereotype.Repository;

import br.com.cams7.gae.repository.AbstractAppRepository;
import br.com.cams7.sisbarc.aal.domain.entity.MercadoriaEntity;
import br.com.cams7.sisbarc.aal.repository.MercadoriaRepository;

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
		AbstractAppRepository<MercadoriaEntity> implements MercadoriaRepository {

	public MercadoriaRepositoryImpl() {
		super();
	}

}
