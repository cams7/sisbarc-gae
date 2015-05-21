/**
 * 
 */
package br.com.cams7.sisbarc.aal.controller.ws;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.cams7.arduino.ArduinoException;
import br.com.cams7.sisbarc.aal.domain.AbstractPino;
import br.com.cams7.sisbarc.aal.domain.AbstractPino.Evento;
import br.com.cams7.sisbarc.aal.domain.AbstractPino.Intervalo;
import br.com.cams7.sisbarc.aal.domain.Pino;
import br.com.cams7.sisbarc.aal.domain.entity.LEDEntity;
import br.com.cams7.sisbarc.aal.domain.entity.LEDEntity.EstadoLED;
import br.com.cams7.sisbarc.aal.task.AppArduinoScheduler;
import br.com.cams7.sisbarc.aal.ws.AppArduinoService;

/**
 * @author cams7
 *
 */
@Controller("arduinoWS")
@WebService(endpointInterface = "br.com.cams7.sisbarc.aal.ws.AppArduinoService")
public class AppArduinoWSController implements AppArduinoService {

	@Autowired
	private AppArduinoScheduler scheduler;

	@Override
	public void openConnection() throws ArduinoException {
		scheduler.openConnection();
	}

	@Override
	public void closeConnection() throws ArduinoException {
		scheduler.closeConnection();
	}

	@Override
	public String getSerialPort() {
		return scheduler.getSerialPort();
	}

	@Override
	public int getSerialBaudRate() {
		return scheduler.getSerialBaudRate();
	}

	@Override
	public int getThreadInterval() {
		return scheduler.getThreadInterval();
	}

	@Override
	public boolean isConnected() {
		return scheduler.isConnected();
	}

	@Override
	public EstadoLED alteraEstadoLED(Pino pino, EstadoLED estado) {
		return scheduler.alteraEstadoLED(pino, estado);
	}

	@Override
	public LEDEntity[] buscaEstadoLEDs(Pino[] pinos) {
		return scheduler.buscaEstadoLEDs(pinos);
	}

	@Override
	public Evento alteraEvento(Pino pino, Evento evento, Intervalo intervalo) {
		return scheduler.alteraEvento(pino, evento, intervalo);
	}

	@Override
	public AbstractPino[] alteraEventos(AbstractPino[] pinos) {
		return scheduler.alteraEventos(pinos);
	}

	@Override
	public AbstractPino[] buscaDados(Pino[] pinos) {
		return scheduler.buscaDados(pinos);
	}

}
