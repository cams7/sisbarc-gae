/**
 * 
 */
package br.com.cams7.sisbarc.aal.controller.rest;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.cams7.app.controller.AbstractRestController;
import br.com.cams7.arduino.ArduinoException;
import br.com.cams7.arduino.ArduinoPinType;
import br.com.cams7.sisbarc.aal.domain.PinoKey;
import br.com.cams7.sisbarc.aal.domain.entity.LEDEntity;
import br.com.cams7.sisbarc.aal.domain.entity.LEDEntity.EstadoLED;
import br.com.cams7.sisbarc.aal.service.LEDService;
import br.com.cams7.util.AppException;

/**
 * @author cams7
 *
 */
@RestController
@RequestMapping("/rest")
public class AppArduinoRestController extends
		AbstractRestController<LEDService, LEDEntity> {

	public AppArduinoRestController() {
		super();
	}

	// LED Amarela - /rest/led/DIGITAL/11/ACESO
	// LED Verde - /rest/led/DIGITAL/10/APAGADO
	// LED Vermelha - /rest/led/DIGITAL/9/APAGADO
	@RequestMapping(value = "/led/{tipo_pino}/{pino}/{estado}", method = RequestMethod.GET)
	public LEDEntity alteraLEDEstado(
			@PathVariable("tipo_pino") String stringTipo,
			@PathVariable("pino") String stringPino,
			@PathVariable("estado") String stringEstado) throws AppException {

		ArduinoPinType tipo = ArduinoPinType.valueOf(stringTipo);
		Byte pino = Byte.valueOf(stringPino);

		LEDEntity led = getService().findOne(new PinoKey(tipo, pino));

		if (led == null)
			throw new AppException("O LED '" + tipo + " " + pino
					+ "' nao foi cadastrado");

		led.setEstado(EstadoLED.valueOf(stringEstado));

		try {
			Future<LEDEntity> call = getService().alteraLEDEstado(led);
			return call.get();
		} catch (InterruptedException | ExecutionException
				| NullPointerException | ArduinoException e) {
			getLog().log(Level.SEVERE, e.getMessage());
			throw new AppException(e.getMessage(), e.getCause());
		}
	}

	// LEDs ativado por botao - /rest/led/ativado_por_botao
	@RequestMapping(value = "/led/ativado_por_botao", method = RequestMethod.GET)
	public List<LEDEntity> getLEDs() throws AppException {
		try {
			Future<List<LEDEntity>> call = getService()
					.getLEDsAtivadoPorBotao();
			return call.get();
		} catch (InterruptedException | ExecutionException
				| NullPointerException | ArduinoException e) {
			getLog().log(Level.SEVERE, e.getMessage());
			throw new AppException(e.getMessage(), e.getCause());
		}
	}

}
