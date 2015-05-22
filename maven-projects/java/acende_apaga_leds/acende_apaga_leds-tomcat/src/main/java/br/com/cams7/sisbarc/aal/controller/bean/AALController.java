/**
 * 
 */
package br.com.cams7.sisbarc.aal.controller.bean;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import br.com.cams7.arduino.ArduinoException;
import br.com.cams7.arduino.ArduinoPinType;
import br.com.cams7.domain.BaseEntity;
import br.com.cams7.sisbarc.aal.domain.Pino;
import br.com.cams7.sisbarc.aal.domain.Pino.Evento;
import br.com.cams7.sisbarc.aal.domain.Pino.Intervalo;
import br.com.cams7.sisbarc.aal.domain.PinoKey;
import br.com.cams7.sisbarc.aal.service.AALService;
import br.com.cams7.webapp.AppController;
import br.com.cams7.webapp.AppService;

/**
 * @author cams7
 *
 */
public abstract class AALController<S extends AppService<E, ID>, E extends BaseEntity<ID>, ID extends Serializable>
		extends AppController<S, E, ID> {

	private static final long serialVersionUID = 1L;

	public AALController() {
		super();
	}

	@Override
	public void includeNewEntity() {
		super.includeNewEntity();

		Pino pino = (Pino) getSelectedEntity();
		pino.setPino(new PinoKey());
	}

	public void atualizaPino(ActionEvent event) {
		E entidade = getSelectedEntity();

		Pino pino = (Pino) entidade;

		final String MSG_ERROR_UPDATE = getMessageFromI18N(
				"error.msg.pin.update", pino.getPino().getTipo().name(), pino
						.getPino().getCodigo());

		RequestContext context = RequestContext.getCurrentInstance();

		try {

			Future<Boolean> call = ((AALService<E, ?>) getService())
					.atualizaPino(entidade);

			boolean arduinoRun = call.get();

			if (arduinoRun) {
				String summary = getMessageFromI18N("info.msg.pin.update.ok");// Resumo
				String detail = getMessageFromI18N("info.msg.pin.update", pino
						.getPino().getTipo().name(), pino.getPino().getCodigo());// Detalhes

				addINFOMessage(summary, detail);
				context.addCallbackParam(PARAM_CHANGED, true);
			} else {
				addMessageArduinoNotRun(MSG_ERROR_UPDATE);
				context.addCallbackParam(PARAM_CHANGED, false);
			}
		} catch (InterruptedException | ExecutionException e) {
			addERRORMessage(MSG_ERROR_UPDATE, e.getMessage());
			context.addCallbackParam(PARAM_CHANGED, false);
		} catch (NullPointerException | ArduinoException e) {
			addMessageMonitorNotRun(MSG_ERROR_UPDATE);
			context.addCallbackParam(PARAM_CHANGED, false);
		}

	}

	public void atualizaPinos(ActionEvent event) {
		final String MSG_ERROR_UPDATE = getMessageFromI18N("error.msg.pins.update");

		List<E> pinos = getService().findAll();
		try {
			Future<Boolean> call = ((AALService<E, ?>) getService())
					.alteraEventos(pinos);

			boolean arduinoRun = call.get();

			if (arduinoRun) {
				String summary = getMessageFromI18N("info.msg.pins.update.ok");// Resumo
				String detail = getMessageFromI18N("info.msg.pins.update");// Detalhes

				addINFOMessage(summary, detail);
			} else
				addMessageArduinoNotRun(MSG_ERROR_UPDATE);

		} catch (InterruptedException | ExecutionException e) {
			addERRORMessage(MSG_ERROR_UPDATE, e.getMessage());
		} catch (NullPointerException | ArduinoException e) {
			addMessageMonitorNotRun(MSG_ERROR_UPDATE);
		}

	}

	public void sincronizaPinos(ActionEvent event) {
		final String MSG_ERROR_SYNCHRONIZE = getMessageFromI18N("error.msg.pins.synchronize");

		List<E> pinos = getService().findAll();
		try {
			Future<Boolean> call = ((AALService<E, ?>) getService())
					.sincronizaEventos(pinos);

			boolean arduinoRun = call.get();

			if (arduinoRun) {
				String summary = getMessageFromI18N("info.msg.pins.synchronize.ok");// Resumo
				String detail = getMessageFromI18N("info.msg.pins.synchronize");// Detalhes

				addINFOMessage(summary, detail);
			} else
				addMessageArduinoNotRun(MSG_ERROR_SYNCHRONIZE);

		} catch (InterruptedException | ExecutionException e) {
			addERRORMessage(MSG_ERROR_SYNCHRONIZE, e.getMessage());
		} catch (NullPointerException | ArduinoException e) {
			addMessageMonitorNotRun(MSG_ERROR_SYNCHRONIZE);
		}

	}

	protected void addMessageMonitorNotRun(String detail) {
		String summary = getMessageFromI18N("error.msg.monitor.not.run");// Resumo
		addWARNMessage(summary, detail);
	}

	protected void addMessageArduinoNotRun(String detail) {
		String summary = getMessageFromI18N("error.msg.arduino.not.run");// Resumo
		addWARNMessage(summary, detail);
	}

	/**
	 * @return
	 */
	public ArduinoPinType[] getTipos() {
		return ArduinoPinType.values();
	}

	/**
	 * @return
	 */
	public Evento[] getEventos() {
		return Evento.values();
	}

	/**
	 * @return
	 */
	public Intervalo[] getIntervalos() {
		return Intervalo.values();
	}

}
