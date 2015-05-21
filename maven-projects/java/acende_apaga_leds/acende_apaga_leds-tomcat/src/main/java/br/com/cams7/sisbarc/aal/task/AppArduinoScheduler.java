/**
 * 
 */
package br.com.cams7.sisbarc.aal.task;

//import java.util.Hashtable;
import java.util.logging.Level;

import org.springframework.stereotype.Component;

import br.com.cams7.arduino.ArduinoException;
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
import br.com.cams7.arduino.ArduinoPinType;
import br.com.cams7.sisbarc.aal.domain.AbstractPino;
//import br.com.cams7.sisbarc.aal.ejb.service.AppWildflyService;
import br.com.cams7.sisbarc.aal.domain.AbstractPino.Evento;
import br.com.cams7.sisbarc.aal.domain.AbstractPino.Intervalo;
import br.com.cams7.sisbarc.aal.domain.Pino;
import br.com.cams7.sisbarc.aal.domain.entity.LEDEntity;
import br.com.cams7.sisbarc.aal.domain.entity.LEDEntity.EstadoLED;
import br.com.cams7.sisbarc.aal.ws.AppArduinoService;
import br.com.cams7.sisbarc.arduino.ArduinoScheduler;
import br.com.cams7.sisbarc.arduino.vo.Arduino;
import br.com.cams7.sisbarc.arduino.vo.Arduino.ArduinoEvent;
import br.com.cams7.sisbarc.arduino.vo.Arduino.ArduinoStatus;
import br.com.cams7.sisbarc.arduino.vo.ArduinoEEPROM;
import br.com.cams7.sisbarc.arduino.vo.ArduinoUSART;
import br.com.cams7.sisbarc.arduino.vo.EEPROMData;

/**
 * @author cams7
 *
 */
@Component("arduinoScheduler")
public class AppArduinoScheduler extends ArduinoScheduler implements
		AppArduinoService {

	private final byte D13_LED_PISCA = 13; // Pino 13 Digital

	private final byte D11_LED_AMARELO = 11; // Pino 11 PWM
	private final byte D10_LED_VERDE = 10; // Pino 10 PWM
	private final byte D09_LED_VERMELHO = 9; // Pino 09 PWM
	private final byte D06_LED_AMARELO = 6; // Pino 06 PWM
	private final byte D05_LED_VERDE = 5; // Pino 05 PWM
	private final byte D04_LED_VERMELHO = 4; // Pino 04 Digital

	private final byte A0_POTENCIOMETRO = 0; // Pino 0 Analogico

	public AppArduinoScheduler() {
		super();
	}

	@Override
	protected void receiveExecute(ArduinoPinType pinType, byte pin,
			short pinValue) {
		switch (pinType) {
		case DIGITAL: {
			switch (pin) {
			case D11_LED_AMARELO:
			case D10_LED_VERDE:
			case D09_LED_VERMELHO:
			case D06_LED_AMARELO:
			case D05_LED_VERDE:
			case D04_LED_VERMELHO: {
				getLog().info(
						"EXECUTE -> LED ("
								+ pin
								+ "): "
								+ (pinValue == 0x0001 ? EstadoLED.ACESO
										: EstadoLED.APAGADO));
				break;
			}
			case D13_LED_PISCA: {
				getLog().info(
						"USART -> LED Pisca ("
								+ pin
								+ "): "
								+ (pinValue == 0x0001 ? EstadoLED.ACESO
										: EstadoLED.APAGADO));
				break;
			}
			default:
				break;
			}
			break;
		}
		case ANALOG: {
			switch (pin) {
			case A0_POTENCIOMETRO: {
				getLog().info(
						"EXECUTE -> Potenciometro (" + pin + "): " + pinValue);
				break;
			}
			default:
				break;
			}
			break;
		}
		default:
			break;
		}

	}

	@Override
	protected void receiveMessage(ArduinoPinType pinType, byte pin,
			short pinValue) {
		switch (pinType) {
		case DIGITAL: {
			switch (pin) {
			case D13_LED_PISCA:
			case D11_LED_AMARELO:
			case D10_LED_VERDE:
			case D09_LED_VERMELHO:
			case D06_LED_AMARELO:
			case D05_LED_VERDE:
			case D04_LED_VERMELHO: {
				getLog().info("MESSAGE -> LED (" + pin + "): " + pinValue);
				break;
			}
			default:
				break;
			}
			break;
		}
		case ANALOG: {
			switch (pin) {
			case A0_POTENCIOMETRO: {
				getLog().info(
						"MESSAGE -> Potenciometro (" + pin + "): " + pinValue);
				break;
			}
			default:
				break;
			}
			break;
		}
		default:
			break;
		}

	}

	@Override
	protected void receiveWrite(ArduinoPinType pinType, byte pin,
			byte threadInterval, byte actionEvent) {
		switch (pinType) {
		case DIGITAL: {
			switch (pin) {
			case D13_LED_PISCA:
			case D11_LED_AMARELO:
			case D10_LED_VERDE:
			case D09_LED_VERMELHO:
			case D06_LED_AMARELO:
			case D05_LED_VERDE:
			case D04_LED_VERMELHO: {
				getLog().info(
						"WRITE -> LED (" + pin + "): threadInterval = "
								+ threadInterval + ", actionEvent = "
								+ actionEvent);
				break;
			}
			default:
				break;
			}
			break;
		}
		case ANALOG: {
			switch (pin) {
			case A0_POTENCIOMETRO: {
				getLog().info(
						"WRITE -> Potenciometro (" + pin
								+ "): threadInterval = " + threadInterval
								+ ", actionEvent = " + actionEvent);
				break;
			}
			default:
				break;
			}
			break;
		}
		default:
			break;
		}

	}

	@Override
	protected void receiveRead(ArduinoPinType pinType, byte pin,
			byte threadInterval, byte actionEvent) {
		switch (pinType) {
		case DIGITAL: {
			switch (pin) {
			case D13_LED_PISCA:
			case D11_LED_AMARELO:
			case D10_LED_VERDE:
			case D09_LED_VERMELHO:
			case D06_LED_AMARELO:
			case D05_LED_VERDE:
			case D04_LED_VERMELHO: {
				getLog().info(
						"READ -> LED (" + pin + "): threadInterval = "
								+ threadInterval + ", actionEvent = "
								+ actionEvent);
				break;
			}
			default:
				break;
			}
			break;
		}
		case ANALOG: {
			switch (pin) {
			case A0_POTENCIOMETRO: {
				getLog().info(
						"READ -> Potenciometro (" + pin
								+ "): threadInterval = " + threadInterval
								+ ", actionEvent = " + actionEvent);
				break;
			}
			default:
				break;
			}
			break;
		}
		default:
			break;
		}

	}

	@Override
	protected short sendResponse(ArduinoPinType pinType, byte pin,
			short pinValue) {
		switch (pinType) {
		case DIGITAL: {
			switch (pin) {
			case D11_LED_AMARELO:
			case D10_LED_VERDE:
			case D09_LED_VERMELHO:
			case D06_LED_AMARELO:
			case D05_LED_VERDE:
			case D04_LED_VERMELHO: {
				pinValue = acendeOuApagaLEDPorBotao(pin, pinValue);
				break;
			}
			default:
				break;
			}
			break;
		}
		case ANALOG: {
			break;
		}
		default:
			break;
		}
		return pinValue;
	}

	@Override
	public EstadoLED alteraEstadoLED(Pino pino, EstadoLED estado)
			throws ArduinoException {
		ArduinoPinType tipoPino = pino.getTipo();
		byte pinoLED = pino.getCodigo().byteValue();

		boolean estadoLED = (estado == EstadoLED.ACESO);

		if (tipoPino == ArduinoPinType.DIGITAL) {
			sendPinDigitalUSART(ArduinoStatus.SEND_RESPONSE, pinoLED, estadoLED);
			serialThreadInterval();

			return getEstadoLED(pino, ArduinoEvent.EXECUTE);
		}

		return null;
	}

	@Override
	public LEDEntity[] buscaEstadoLEDs(Pino[] pinos) throws ArduinoException {
		for (Pino pino : pinos) {
			ArduinoPinType tipoPino = pino.getTipo();
			byte pinoLED = pino.getCodigo().byteValue();

			if (tipoPino == ArduinoPinType.DIGITAL)
				sendPinDigitalUSARTMessage(ArduinoStatus.SEND_RESPONSE, pinoLED);
		}

		serialThreadInterval();

		LEDEntity[] leds = new LEDEntity[pinos.length];
		for (short i = 0; i < pinos.length; i++) {
			Pino pino = pinos[i];
			EstadoLED estado = getEstadoLED(pino, ArduinoEvent.MESSAGE);

			leds[i] = new LEDEntity(pino.getTipo(), pino.getCodigo());
			leds[i].setEstado(estado);
		}
		return leds;
	}

	@Override
	public Evento alteraEvento(Pino pinoId, Evento evento, Intervalo intervalo)
			throws ArduinoException {

		sendEEPROMWrite(ArduinoStatus.SEND_RESPONSE, pinoId.getTipo(), pinoId
				.getCodigo().byteValue(), (byte) intervalo.ordinal(),
				(byte) evento.ordinal());

		serialThreadInterval();

		return getEvento(pinoId);
	}

	@Override
	public AbstractPino[] alteraEventos(AbstractPino[] pinos)
			throws ArduinoException {

		for (AbstractPino pino : pinos) {
			Pino id = pino.getPino();
			sendEEPROMWrite(ArduinoStatus.SEND_RESPONSE, id.getTipo(), id
					.getCodigo().byteValue(), (byte) pino.getIntervalo()
					.ordinal(), (byte) pino.getEvento().ordinal());
		}

		serialThreadInterval();

		for (AbstractPino pino : pinos)
			pino.setEvento(getEvento(pino.getPino()));

		return pinos;
	}

	@Override
	public AbstractPino[] buscaDados(Pino[] ids) throws ArduinoException {
		for (Pino id : ids)
			sendEEPROMRead(ArduinoStatus.SEND_RESPONSE, id.getTipo(), id
					.getCodigo().byteValue());

		serialThreadInterval();

		AbstractPino[] pinos = new AbstractPino[ids.length];

		for (short i = 0; i < ids.length; i++) {
			Pino id = ids[i];
			pinos[i] = new AbstractPino(id.getTipo(), id.getCodigo()) {
				private static final long serialVersionUID = 1L;
			};

			EEPROMData data = getDados(id);

			if (data != null) {
				pinos[i].setEvento(Evento.values()[data.getActionEvent()]);
				pinos[i].setIntervalo(Intervalo.values()[data
						.getThreadInterval()]);
			}
		}

		return pinos;
	}

	private Arduino getArduinoResponse(ArduinoEvent event, Pino pinoId) {
		ArduinoPinType pinType = pinoId.getTipo();
		byte pin = pinoId.getCodigo().byteValue();

		String key = getKeyCurrentStatus(event, pinType, pin);

		if (getCurrentStatus().isEmpty()
				|| !getCurrentStatus().containsKey(key))
			return null;

		Arduino arduino = getCurrentStatus().get(key);

		if (arduino.getTransmitter() != Arduino.ArduinoTransmitter.ARDUINO)
			return null;

		if (arduino.getStatus() != ArduinoStatus.RESPONSE)
			return null;

		return arduino;
	}

	/**
	 * Obtem o ESTADO atual do LED informado,
	 * 
	 * Obs.: Os dados sao recebidos pela SERIAL do ARDUINO
	 * 
	 * @param PINO
	 *            do LED - Numero do PINO DIGITAL
	 * @return ESTADO do LED
	 */
	private EstadoLED getEstadoLED(Pino pinoId, ArduinoEvent arduinoEvent) {
		if (arduinoEvent != ArduinoEvent.EXECUTE
				&& arduinoEvent != ArduinoEvent.MESSAGE)
			return null;

		Arduino arduino = getArduinoResponse(arduinoEvent, pinoId);
		if (arduino == null)
			return null;

		short estadoLED = ((ArduinoUSART) arduino).getPinValue();
		EstadoLED estado = null;
		switch (estadoLED) {
		case 0x0000:
			estado = EstadoLED.APAGADO;
			break;
		case 0x0001:
			estado = EstadoLED.ACESO;
			break;
		default:
			break;
		}
		return estado;
	}

	/**
	 * Obtem o EVENTO, os dados sao recebidos pela SERIAL do ARDUINO
	 * 
	 * @param PINO
	 *            - Numero do PINO DIGITAL/ANALOG
	 * @return EVENTO do LED
	 */
	private Evento getEvento(Pino pino) {
		Arduino arduino = getArduinoResponse(Arduino.ArduinoEvent.WRITE, pino);
		if (arduino == null)
			return null;

		return Evento.values()[((ArduinoEEPROM) arduino).getActionEvent()];
	}

	/**
	 * Obtem os DADOS da EEPROM no ARDUINO
	 * 
	 * Obs.: Os dados sao recebidos pela SERIAL do ARDUINO
	 * 
	 * @param PINO
	 *            - Numero do PINO DIGITAL/ANALOGICO
	 * @return DADOS da EEPROM no ARDUINO
	 */
	private EEPROMData getDados(Pino pino) {
		Arduino arduino = getArduinoResponse(Arduino.ArduinoEvent.READ, pino);
		if (arduino == null)
			return null;

		return (EEPROMData) arduino;
	}

	private short acendeOuApagaLEDPorBotao(byte pinoLED, short estadoPino) {
		if (estadoPino == 0x0000)
			return estadoPino;

		estadoPino = 0x0000;

		// try {
		// AppWildflyService service = lookupAppWildflyService();
		//
		// EstadoLED estado = service.getEstadoLEDAtivadoPorBotao(pinoLED);
		//
		// if (estado != null && estado == EstadoLED.ACESO)
		// estadoPino = (short) 0x0001;
		//
		// } catch (NamingException e) {
		// getLog().log(Level.SEVERE, e.getMessage());
		// }

		return estadoPino;
	}

	// private AppWildflyService lookupAppWildflyService() throws
	// NamingException {
	// final Hashtable<String, String> jndiProperties = new Hashtable<String,
	// String>();
	// jndiProperties.put(Context.URL_PKG_PREFIXES,
	// "org.jboss.ejb.client.naming");
	// final Context context = new InitialContext(jndiProperties);
	//
	// return (AppWildflyService) context
	// .lookup("ejb:sisbarc/acende_apaga_leds-ejb//LEDServiceImpl!"
	// + AppWildflyService.class.getName());
	//
	// }

	private void serialThreadInterval() {
		try {
			Thread.sleep(getSerialThreadInterval());
		} catch (InterruptedException e) {
			getLog().log(Level.WARNING, e.getMessage());
		}
	}

}
