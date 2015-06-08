/**
 * 
 */
package br.com.cams7.sisbarc.aal.service.impl;

import java.util.List;
import java.util.concurrent.Future;
import java.util.logging.Level;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

import br.com.cams7.app.domain.AbstractEntity;
import br.com.cams7.arduino.ArduinoException;
import br.com.cams7.gae.ds.AbstractDS;
import br.com.cams7.gae.repository.AppRepository;
import br.com.cams7.gae.service.AbstractAppService;
import br.com.cams7.sisbarc.aal.domain.Pino;
import br.com.cams7.sisbarc.aal.domain.Pino.Evento;
import br.com.cams7.sisbarc.aal.domain.Pino.Intervalo;
import br.com.cams7.sisbarc.aal.domain.PinoKey;
import br.com.cams7.sisbarc.aal.service.AALService;
import br.com.cams7.sisbarc.aal.ws.AppArduinoService;
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
		AppArduinoService service = (new AppArduinoServiceImpl())
				.getAppArduinoServicePort();

		// Map<String, Object> context = ((BindingProvider) service)
		// .getRequestContext();
		//
		// Authentication auth = SecurityContextHolder.getContext()
		// .getAuthentication();
		// UserEntity currentUser = (UserEntity) auth.getPrincipal();

		// final String ADDRESS = currentUser.getAddress()
		// + "/acende_apaga_leds/arduino?wsdl";

		// context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, ADDRESS);

		// Map<String, List<String>> headers = new HashMap<String,
		// List<String>>();
		// headers.put("email",
		// Collections.singletonList(currentUser.getEmail()));
		// headers.put("username",
		// Collections.singletonList(currentUser.getUsername()));
		// context.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

		return service;
	}

}
