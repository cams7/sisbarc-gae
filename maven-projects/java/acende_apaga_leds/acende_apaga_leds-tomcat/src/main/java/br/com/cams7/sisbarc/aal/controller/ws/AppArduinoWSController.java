/**
 * 
 */
package br.com.cams7.sisbarc.aal.controller.ws;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import br.com.cams7.arduino.AppArduinoService;
import br.com.cams7.arduino.ArduinoException;
import br.com.cams7.sisbarc.aal.domain.Pino;
import br.com.cams7.sisbarc.aal.domain.Pino.Evento;
import br.com.cams7.sisbarc.aal.domain.Pino.Intervalo;
import br.com.cams7.sisbarc.aal.domain.PinoKey;
import br.com.cams7.sisbarc.aal.domain.entity.LEDEntity;
import br.com.cams7.sisbarc.aal.domain.entity.LEDEntity.EstadoLED;
import br.com.cams7.sisbarc.aal.task.AppArduinoScheduler;

/**
 * @author cams7
 *
 */
@Controller("arduinoWS")
@WebService(endpointInterface = AppArduinoService.WEBSERVICE_ENDPOINT_INTERFACE)
public class AppArduinoWSController implements AppArduinoService {

	@Autowired
	@Qualifier(AppArduinoScheduler.COMPONENT_NAME)
	private AppArduinoService scheduler;

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
	public int getSerialThreadInterval() {
		return scheduler.getSerialThreadInterval();
	}

	@Override
	public boolean isConnected() {
		return scheduler.isConnected();
	}

	@Override
	public EstadoLED alteraEstadoLED(PinoKey id, EstadoLED estado)
			throws ArduinoException {
		return scheduler.alteraEstadoLED(id, estado);
	}

	@Override
	public LEDEntity[] buscaEstadoLEDs(PinoKey[] ids) throws ArduinoException {
		return scheduler.buscaEstadoLEDs(ids);
	}

	@Override
	public Evento alteraEvento(PinoKey id, Evento evento, Intervalo intervalo)
			throws ArduinoException {
		return scheduler.alteraEvento(id, evento, intervalo);
	}

	@Override
	public Pino[] alteraEventos(Pino[] pinos) throws ArduinoException {
		return scheduler.alteraEventos(pinos);
	}

	@Override
	public Pino[] buscaDados(PinoKey[] ids) throws ArduinoException {
		return scheduler.buscaDados(ids);
	}

}
