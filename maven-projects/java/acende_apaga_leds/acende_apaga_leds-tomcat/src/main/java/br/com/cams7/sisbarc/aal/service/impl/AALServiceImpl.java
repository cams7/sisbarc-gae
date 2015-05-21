package br.com.cams7.sisbarc.aal.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Future;
import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

import br.com.cams7.arduino.ArduinoException;
import br.com.cams7.domain.BaseEntity;
import br.com.cams7.sisbarc.aal.domain.AbstractPino;
import br.com.cams7.sisbarc.aal.domain.AbstractPino.Evento;
import br.com.cams7.sisbarc.aal.domain.AbstractPino.Intervalo;
import br.com.cams7.sisbarc.aal.domain.Pino;
import br.com.cams7.sisbarc.aal.service.AALService;
import br.com.cams7.sisbarc.aal.task.AppArduinoScheduler;
import br.com.cams7.webapp.AppRepository;
import br.com.cams7.webapp.AppServiceImpl;

/**
 * @author cams7
 *
 */
public abstract class AALServiceImpl<R extends AppRepository<E, ID>, E extends BaseEntity<ID>, ID extends Serializable>
		extends AppServiceImpl<R, E, ID> implements AALService<E, ID> {

	@Autowired
	private AppArduinoScheduler scheduler;

	public AALServiceImpl() {
		super();
	}

	protected Pino[] getIDs(List<E> entidades) {
		Pino[] ids = new Pino[entidades.size()];

		for (byte i = 0; i < entidades.size(); i++)
			ids[i] = ((AbstractPino) entidades.get(i)).getPino();

		return ids;
	}

	protected AbstractPino[] getPinos(List<E> entidades) {
		AbstractPino[] pinos = new AbstractPino[entidades.size()];

		for (byte i = 0; i < entidades.size(); i++)
			pinos[i] = (AbstractPino) entidades.get(i);

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
		AbstractPino pino = (AbstractPino) entidade;

		Pino id = pino.getPino();
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

		AbstractPino[] pinos = scheduler.buscaDados(getIDs(entidades));

		Boolean arduinoRun = Boolean.TRUE;

		for (AbstractPino pino : pinos) {
			if (pino.getEvento() == null && pino.getIntervalo() == null) {
				arduinoRun = Boolean.FALSE;
				break;
			}

			Pino id = pino.getPino();
			Evento evento = pino.getEvento();
			Intervalo intervalo = pino.getIntervalo();

			for (E entidade : entidades) {
				AbstractPino p = (AbstractPino) entidade;
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

		AbstractPino[] pinos = scheduler.alteraEventos(getPinos(entidades));

		Boolean arduinoRun = Boolean.TRUE;

		for (AbstractPino pino : pinos)
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

	protected AppArduinoScheduler getScheduler() {
		return scheduler;
	}

}
