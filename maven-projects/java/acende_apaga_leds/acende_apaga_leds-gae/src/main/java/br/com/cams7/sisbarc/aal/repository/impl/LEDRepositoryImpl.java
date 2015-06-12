/**
 * 
 */
package br.com.cams7.sisbarc.aal.repository.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.cams7.app.domain.entity.UserEntity;
import br.com.cams7.sisbarc.aal.domain.PinoKey;
import br.com.cams7.sisbarc.aal.domain.entity.LEDEntity;
import br.com.cams7.sisbarc.aal.repository.LEDRepository;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;

/**
 * Implementa o contrato de persistencia da entidade <code>LED</code>.
 * 
 * <p>
 * Nessa aplicacao resolvemos a persistencia utilizando o Objectify, um
 * framework de persistencia para o App Engine.<br/>
 * A proposta do Objetify e denifir uma API mais alto-nivel para manipular dados
 * no <code>DataStore</code> do App Engine.
 * </p>
 * 
 * <p>
 * <code>LEDRepositoryImpl</code> e anotado com <code>@Repository</code>, dessa
 * forma o Spring Framework gerencia o ciclo de vida dos objetos dessa classe,
 * alem de permitir a injecao de dependencia em outros componentes.
 * 
 * @see br.com.cams7.sisbarc.aal.repository.LEDRepository
 * @see com.googlecode.objectify.ObjectifyService.
 * 
 * @author cams7
 *
 */
@Repository
public class LEDRepositoryImpl extends AbstractAALRepository<LEDEntity>
		implements LEDRepository {

	public LEDRepositoryImpl() {
		super();
	}

	@Override
	public List<LEDEntity> buscaLEDsAtivadoPorBotao(Key<UserEntity> user) {
		Query<LEDEntity> query = ofy().load().type(LEDEntity.class);
		query = query.ancestor(user);
		query = query.filter("ativadoPorBotao", Boolean.TRUE);
		// query = query.order("-pino.tipo");
		// query = query.order("-pino.codigo");
		List<LEDEntity> leds = query.list();
		return leds;
	}

	@Override
	public LEDEntity findOne(Key<UserEntity> user, PinoKey key) {
		Query<LEDEntity> query = ofy().load().type(LEDEntity.class);
		query = query.ancestor(user);
		query = query.filter("pino.tipo", key.getTipo());
		query = query.filter("pino.codigo", key.getCodigo());
		LEDEntity led = query.first().now();
		return led;
	}

}
