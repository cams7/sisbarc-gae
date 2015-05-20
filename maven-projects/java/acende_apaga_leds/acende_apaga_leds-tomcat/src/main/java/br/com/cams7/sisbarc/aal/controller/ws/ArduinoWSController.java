/**
 * 
 */
package br.com.cams7.sisbarc.aal.controller.ws;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.cams7.sisbarc.aal.domain.AbstractPino;
import br.com.cams7.sisbarc.aal.domain.AbstractPino.Evento;
import br.com.cams7.sisbarc.aal.domain.AbstractPino.Intervalo;
import br.com.cams7.sisbarc.aal.domain.Pino;
import br.com.cams7.sisbarc.aal.domain.entity.LEDEntity;
import br.com.cams7.sisbarc.aal.domain.entity.LEDEntity.EstadoLED;
import br.com.cams7.sisbarc.aal.task.ArduinoScheduler;
import br.com.cams7.sisbarc.aal.ws.AppArduinoService;

/**
 * @author cams7
 *
 */
@Controller("arduinoWS")
@WebService(endpointInterface = "br.com.cams7.sisbarc.aal.ws.AppArduinoService")
public class ArduinoWSController implements AppArduinoService {

	@Autowired
	private ArduinoScheduler monitor;

	@Override
	public void connect() {
		monitor.connect();
	}

	@Override
	public void close() {
		monitor.close();
	}

	@Override
	public boolean isInitialized() {
		return monitor.isInitialized();
	}

	@Override
	public EstadoLED alteraEstadoLED(Pino pino, EstadoLED estado) {
		return monitor.alteraEstadoLED(pino, estado);
	}

	@Override
	public LEDEntity[] buscaEstadoLEDs(Pino[] pinos) {
		return monitor.buscaEstadoLEDs(pinos);
	}

	@Override
	public Evento alteraEvento(Pino pino, Evento evento, Intervalo intervalo) {
		return monitor.alteraEvento(pino, evento, intervalo);
	}

	@Override
	public AbstractPino[] alteraEventos(AbstractPino[] pinos) {
		return monitor.alteraEventos(pinos);
	}

	@Override
	public AbstractPino[] buscaDados(Pino[] pinos) {
		return monitor.buscaDados(pinos);
	}

}
