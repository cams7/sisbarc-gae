/**
 * 
 */
package br.com.cams7.sisbarc.aal.service;

import java.util.List;
import java.util.concurrent.Future;

import br.com.cams7.arduino.ArduinoException;
import br.com.cams7.sisbarc.aal.domain.entity.LEDEntity;
import br.com.cams7.sisbarc.aal.domain.entity.LEDEntity.EstadoLED;
import br.com.cams7.sisbarc.aal.repository.LEDRepository;

/**
 * @author cams7
 *
 */
public interface LEDService extends AALService<LEDEntity, String>,
		LEDRepository {

	public Future<LEDEntity> alteraLEDEstado(LEDEntity led)
			throws ArduinoException;

	public Future<List<LEDEntity>> getLEDsAtivadoPorBotao()
			throws ArduinoException;

	public EstadoLED getEstadoLEDAtivadoPorBotao(byte pin);

}
