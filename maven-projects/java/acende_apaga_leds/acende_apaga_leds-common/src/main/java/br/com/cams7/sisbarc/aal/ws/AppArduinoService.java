/**
 * 
 */
package br.com.cams7.sisbarc.aal.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import br.com.cams7.arduino.ArduinoException;
import br.com.cams7.arduino.ArduinoService;
import br.com.cams7.sisbarc.aal.domain.Pino;
import br.com.cams7.sisbarc.aal.domain.Pino.Evento;
import br.com.cams7.sisbarc.aal.domain.Pino.Intervalo;
import br.com.cams7.sisbarc.aal.domain.PinoKey;
import br.com.cams7.sisbarc.aal.domain.entity.LEDEntity;
import br.com.cams7.sisbarc.aal.domain.entity.LEDEntity.EstadoLED;

/**
 * @author cesar
 *
 */
@WebService(name = AppArduinoService.WEBSERVICE_NAME, targetNamespace = AppArduinoService.WEBSERVICE_TARGETNAMESPACE)
@SOAPBinding(style = SOAPBinding.Style.RPC)
// @SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use =
// SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface AppArduinoService extends ArduinoService {

	public static final String WEBSERVICE_NAME = "AppArduinoService";
	public static final String WEBSERVICE_TARGETNAMESPACE = "http://ws.aal.sisbarc.cams7.com.br/";

	/**
	 * Altera o ESTADO do LED para ACESO ou APAGADO
	 * 
	 * @param PINO
	 *            do LED - Numero do PINO DIGITAL
	 * @param ESTADO
	 *            do LED - ACESO/APAGADO
	 * 
	 * 
	 */
	@WebMethod
	public EstadoLED alteraEstadoLED(PinoKey id, EstadoLED estado)
			throws ArduinoException;

	/**
	 * Busca os ESTADOs dos LEDs, que pode ser ACESO ou APAGADO
	 * 
	 * @param PINOs
	 *            dos LEDs - Numero do PINO DIGITAL
	 */
	@WebMethod
	public LEDEntity[] buscaEstadoLEDs(PinoKey[] ids) throws ArduinoException;

	/**
	 * Altera o EVENTO e o INTERVALO
	 * 
	 * @param PINO
	 *            - Numero do PINO DIGITAL/ANALOGICO
	 * @param EVENTO
	 * 
	 * @param INTERVALO
	 */
	@WebMethod
	public Evento alteraEvento(PinoKey id, Evento evento, Intervalo intervalo)
			throws ArduinoException;

	/**
	 * @param pinos
	 * @return
	 */
	@WebMethod
	public Pino[] alteraEventos(Pino[] pinos) throws ArduinoException;

	/**
	 * Obtem os Dados na EEPROM do ARDUINO
	 * 
	 * @param PINOs
	 *            - Numero do PINO DIGITAL/ANALOGICO
	 */
	@WebMethod
	public Pino[] buscaDados(PinoKey[] ids) throws ArduinoException;

}
