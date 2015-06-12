/**
 * 
 */
package br.com.cams7.sisbarc.aal.service.impl;

import java.net.URL;
import java.util.List;
import java.util.concurrent.Future;
import java.util.logging.Level;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

import com.googlecode.objectify.Key;

import br.com.cams7.app.domain.AbstractEntity;
import br.com.cams7.app.domain.entity.UserEntity;
import br.com.cams7.arduino.AppArduinoService;
import br.com.cams7.arduino.ArduinoException;
import br.com.cams7.gae.ds.AbstractDS;
import br.com.cams7.gae.repository.AppRepository;
import br.com.cams7.gae.security.AuthenticationHelper;
import br.com.cams7.gae.service.AbstractAppService;
import br.com.cams7.sisbarc.aal.domain.Pino;
import br.com.cams7.sisbarc.aal.domain.Pino.Evento;
import br.com.cams7.sisbarc.aal.domain.Pino.Intervalo;
import br.com.cams7.sisbarc.aal.domain.PinoKey;
import br.com.cams7.sisbarc.aal.repository.AALRepository;
import br.com.cams7.sisbarc.aal.service.AALService;
import br.com.cams7.sisbarc.aal.ws.AppArduinoServiceImpl;

/**
 * @author cams7
 *
 */
public abstract class AbstractAALService<R extends AppRepository<E>, D extends AbstractDS<E>, E extends AbstractEntity>
		extends AbstractAppService<R, D, E> implements AALService<E> {

	public AbstractAALService() {
		super();
	}

	@Override
	public List<E> findAll(Key<UserEntity> user) {
		AALRepository<E> repository = (AALRepository<E>) getRepository();
		List<E> entities = repository.findAll(user);
		return entities;
	}

	// @Override
	// public List<E> findAll() {
	// return findAll(AuthenticationHelper.getKeyUser());
	// }

	@Override
	public E findOne(Key<UserEntity> user, Long id) {
		AALRepository<E> repository = (AALRepository<E>) getRepository();
		E entity = repository.findOne(user, id);
		return entity;
	}

	@Override
	public E findOne(Long id) {
		return findOne(AuthenticationHelper.getKeyUser(), id);
	}

	@Override
	public void synch() {
		List<E> entities = findAll(AuthenticationHelper.getKeyUser());
		getDataSource().synch(entities);
	}

	@Override
	protected D getDataSource() {
		List<E> entities = findAll(AuthenticationHelper.getKeyUser());
		return getDataSource(entities);
	}

	protected PinoKey[] getKeys(List<E> entidades) {
		PinoKey[] ids = new PinoKey[entidades.size()];

		for (byte i = 0; i < entidades.size(); i++)
			ids[i] = ((Pino) entidades.get(i)).getPino();

		return ids;
	}

	protected Pino[] getPinos(List<E> entidades) {
		Pino[] pinos = new Pino[entidades.size()];

		for (byte i = 0; i < entidades.size(); i++)
			pinos[i] = (Pino) entidades.get(i);

		return pinos;
	}

	@Override
	@Async
	public Future<Boolean> atualizaPino(E entidade) throws ArduinoException {
		Pino pino = (Pino) entidade;

		PinoKey key = pino.getPino();
		Evento evento = pino.getEvento();
		Intervalo intervalo = pino.getIntervalo();

		AppArduinoService service = getPort();

		evento = service.alteraEvento(key, evento, intervalo);

		Boolean arduinoRun = Boolean.FALSE;

		if (evento != null) {
			pino.setEvento(evento);
			save(entidade);
			arduinoRun = Boolean.TRUE;

			getLog().info(
					"O evento do PINO '" + pino.getId() + "' foi alterado '"
							+ pino.getEvento() + "'");
		} else
			getLog().log(
					Level.WARNING,
					"Ocorreu um erro ao tenta buscar o EVENTO do PINO '"
							+ pino.getId() + "'");

		return new AsyncResult<Boolean>(arduinoRun);
	}

	@Override
	@Async
	public Future<Boolean> sincronizaEventos(List<E> entidades)
			throws ArduinoException {
		AppArduinoService service = getPort();

		PinoKey[] keys = getKeys(entidades);
		Pino[] pinos = service.buscaDados(keys);

		Boolean arduinoRun = Boolean.TRUE;

		for (Pino pino : pinos) {
			if (pino.getEvento() == null && pino.getIntervalo() == null) {
				arduinoRun = Boolean.FALSE;
				break;
			}

			PinoKey id = pino.getPino();
			Evento evento = pino.getEvento();
			Intervalo intervalo = pino.getIntervalo();

			for (E entidade : entidades) {
				Pino p = (Pino) entidade;
				if (id.equals(p.getPino())) {
					p.setEvento(evento);
					p.setIntervalo(intervalo);
					break;
				}
			}

		}

		if (arduinoRun) {
			save(entidades);
			getLog().info("Os EVENTOs dos PINOs foram sincronizados");
		} else
			getLog().log(Level.WARNING,
					"Ocorreu um erro ao tenta buscar os DADOs dos PINOs");

		return new AsyncResult<Boolean>(arduinoRun);
	}

	@Override
	@Async
	public Future<Boolean> alteraEventos(List<E> entidades)
			throws ArduinoException {
		AppArduinoService service = getPort();

		Pino[] pinos = getPinos(entidades);
		pinos = service.alteraEventos(pinos);

		Boolean arduinoRun = Boolean.TRUE;

		for (Pino pino : pinos)
			if (pino.getEvento() == null) {
				arduinoRun = Boolean.FALSE;
				break;
			}

		if (arduinoRun)
			getLog().info("Os EVENTOs dos PINOs foram alterados");
		else
			getLog().log(Level.WARNING,
					"Ocorreu um erro ao tenta buscar os EVENTOs dos PINOs");

		return new AsyncResult<Boolean>(arduinoRun);
	}

	protected AppArduinoService getPort() {
		final URL WSDL_LOCATION = AppArduinoServiceImpl.getWsdlLocation();
		AppArduinoService service = (new AppArduinoServiceImpl(WSDL_LOCATION))
				.getAppArduinoServicePort();

		return service;
	}

}
