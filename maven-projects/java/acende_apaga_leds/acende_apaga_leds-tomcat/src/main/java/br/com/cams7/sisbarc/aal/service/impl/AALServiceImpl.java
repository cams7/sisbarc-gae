package br.com.cams7.sisbarc.aal.service.impl;

import java.util.List;
import java.util.concurrent.Future;
import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

import br.com.cams7.arduino.ArduinoException;
import br.com.cams7.domain.BaseEntity;
import br.com.cams7.sisbarc.aal.domain.Pino;
import br.com.cams7.sisbarc.aal.domain.Pino.Evento;
import br.com.cams7.sisbarc.aal.domain.Pino.Intervalo;
import br.com.cams7.sisbarc.aal.domain.PinoKey;
import br.com.cams7.sisbarc.aal.service.AALService;
import br.com.cams7.sisbarc.aal.task.AppArduinoScheduler;
import br.com.cams7.sisbarc.aal.ws.AppArduinoService;
import br.com.cams7.webapp.AppRepository;
import br.com.cams7.webapp.AppServiceImpl;

/**
 * @author cams7
 *
 */
public abstract class AALServiceImpl<R extends AppRepository<E>, E extends BaseEntity>
		extends AppServiceImpl<R, E> implements AALService<E> {

	@Autowired
	@Qualifier(AppArduinoScheduler.COMPONENT_NAME)
	private AppArduinoService scheduler;

	public AALServiceImpl() {
		super();
	}

	protected PinoKey[] getIDs(List<E> entidades) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.sisbarc.aal.ejb.service.AALService#atualizaPino(br.com.cams7
	 * .sisbarc.aal.jpa.domain.AbstractPino)
	 */
	@Async
	public Future<Boolean> atualizaPino(E entidade) throws ArduinoException {
		Pino pino = (Pino) entidade;

		PinoKey id = pino.getPino();
		Evento evento = pino.getEvento();
		Intervalo intervalo = pino.getIntervalo();

		evento = scheduler.alteraEvento(id, evento, intervalo);

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.sisbarc.aal.ejb.service.AALService#sincronizaEventos(java
	 * .util.List)
	 */
	@Async
	public Future<Boolean> sincronizaEventos(List<E> entidades)
			throws ArduinoException {

		Pino[] pinos = scheduler.buscaDados(getIDs(entidades));

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.sisbarc.aal.ejb.service.AALService#alteraEventos(java.util
	 * .List)
	 */
	@Async
	public Future<Boolean> alteraEventos(List<E> entidades)
			throws ArduinoException {

		Pino[] pinos = scheduler.alteraEventos(getPinos(entidades));

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

	protected AppArduinoService getScheduler() {
		return scheduler;
	}

}
