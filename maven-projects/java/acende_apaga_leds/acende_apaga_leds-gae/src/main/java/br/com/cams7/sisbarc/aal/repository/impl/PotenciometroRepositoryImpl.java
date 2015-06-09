/**
 * 
 */
package br.com.cams7.sisbarc.aal.repository.impl;

import org.springframework.stereotype.Repository;

import br.com.cams7.sisbarc.aal.domain.entity.PotenciometroEntity;
import br.com.cams7.sisbarc.aal.repository.PotenciometroRepository;

/**
 * Implementa o contrato de persistencia da entidade <code>Potenciometro</code>.
 * 
 * <p>
 * Nessa aplicacao resolvemos a persistencia utilizando o Objectify, um
 * framework de persistencia para o App Engine.<br/>
 * A proposta do Objetify e denifir uma API mais alto-nivel para manipular dados
 * no <code>DataStore</code> do App Engine.
 * </p>
 * 
 * <p>
 * <code>PotenciometroRepositoryImpl</code> e anotado com
 * <code>@Repository</code>, dessa forma o Spring Framework gerencia o ciclo de
 * vida dos objetos dessa classe, alem de permitir a injecao de dependencia em
 * outros componentes.
 * 
 * @see br.com.cams7.sisbarc.aal.repository.PotenciometroRepository
 * @see com.googlecode.objectify.ObjectifyService.
 * 
 * @author cams7
 *
 */
@Repository
public class PotenciometroRepositoryImpl extends
		AbstractAALRepository<PotenciometroEntity> implements
		PotenciometroRepository {

	public PotenciometroRepositoryImpl() {
		super();
	}

}
