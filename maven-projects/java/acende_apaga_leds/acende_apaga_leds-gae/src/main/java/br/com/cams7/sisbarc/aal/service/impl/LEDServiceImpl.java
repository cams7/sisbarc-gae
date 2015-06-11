/**
 * 
 */
package br.com.cams7.sisbarc.aal.service.impl;

import java.util.List;
import java.util.concurrent.Future;
import java.util.logging.Level;

import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.googlecode.objectify.Key;

import br.com.cams7.app.domain.entity.UserEntity;
import br.com.cams7.arduino.ArduinoException;
import br.com.cams7.gae.security.AuthenticationHelper;
import br.com.cams7.sisbarc.aal.domain.PinoKey;
import br.com.cams7.sisbarc.aal.domain.entity.LEDEntity;
import br.com.cams7.sisbarc.aal.domain.entity.LEDEntity.EstadoLED;
import br.com.cams7.sisbarc.aal.ds.LEDDS;
import br.com.cams7.sisbarc.aal.repository.LEDRepository;
import br.com.cams7.sisbarc.aal.service.LEDService;
import br.com.cams7.sisbarc.aal.ws.AppArduinoService;

/**
 * @author cams7
 *
 */
@Service
public class LEDServiceImpl extends
		AbstractAALService<LEDRepository, LEDDS, LEDEntity> implements
		LEDService {

	public LEDServiceImpl() {
		super();
	}

	@Override
	public List<LEDEntity> buscaLEDsAtivadoPorBotao(Key<UserEntity> user) {
		List<LEDEntity> leds = getRepository().buscaLEDsAtivadoPorBotao(user);
		return leds;
	}

	@Override
	public LEDEntity findOne(Key<UserEntity> user, PinoKey key) {
		LEDEntity led = getRepository().findOne(user, key);
		return led;
	}

	@Override
	public Future<LEDEntity> alteraLEDEstado(LEDEntity led)
			throws ArduinoException {
		if (led.getEstado() == EstadoLED.ACESO && !led.getAtivo()) {
			led.setEstado(EstadoLED.APAGADO);
		} else {
			AppArduinoService service = getPort();
			EstadoLED estado = service.alteraEstadoLED(led.getPino(),
					led.getEstado());

			led.setEstado(estado);

			if (estado != null) {
				getLog().info(
						"LED '" + led.getId() + "' esta '" + led.getEstado()
								+ "'");
			} else
				getLog().log(
						Level.WARNING,
						"Ocorreu um erro ao tenta buscar o ESTADO do LED '"
								+ led.getId() + "'");
		}

		return new AsyncResult<LEDEntity>(led);
	}

	@Override
	public Future<List<LEDEntity>> getLEDsAtivadoPorBotao()
			throws ArduinoException {
		List<LEDEntity> leds = buscaLEDsAtivadoPorBotao(AuthenticationHelper
				.getKeyUser());

		AppArduinoService service = getPort();
		LEDEntity[] array = service.buscaEstadoLEDs(getKeys(leds));

		for (LEDEntity led : array) {
			PinoKey id = led.getPino();
			EstadoLED estado = led.getEstado();

			for (LEDEntity l : leds)
				if (id.equals(l.getPino())) {
					l.setEstado(estado);
					break;
				}
		}

		return new AsyncResult<List<LEDEntity>>(leds);
	}

}
