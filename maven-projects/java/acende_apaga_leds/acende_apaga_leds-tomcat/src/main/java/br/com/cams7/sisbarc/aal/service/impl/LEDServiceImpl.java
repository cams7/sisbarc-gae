/**
 * 
 */
package br.com.cams7.sisbarc.aal.service.impl;

import java.util.List;
import java.util.concurrent.Future;
import java.util.logging.Level;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import br.com.cams7.arduino.ArduinoException;
import br.com.cams7.sisbarc.aal.domain.Pino;
import br.com.cams7.sisbarc.aal.domain.entity.LEDEntity;
import br.com.cams7.sisbarc.aal.domain.entity.LEDEntity.EstadoLED;
import br.com.cams7.sisbarc.aal.repository.LEDRepository;
import br.com.cams7.sisbarc.aal.service.LEDService;

/**
 * @author cams7
 *
 */
@Service
public class LEDServiceImpl extends
		AALServiceImpl<LEDRepository, LEDEntity, String> implements LEDService {

	/**
	 * 
	 */
	public LEDServiceImpl() {
		super();
	}

	@Override
	public List<LEDEntity> buscaLEDsAtivadoPorBotao() {
		return getRepository().buscaLEDsAtivadoPorBotao();
	}

	@Async
	public Future<LEDEntity> alteraLEDEstado(LEDEntity led)
			throws ArduinoException {

		if (led.getEstado() == EstadoLED.ACESO && !led.getAtivo()) {
			led.setEstado(EstadoLED.APAGADO);
		} else {
			EstadoLED estado = getScheduler().alteraEstadoLED(led.getPino(),
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

	@Async
	public Future<List<LEDEntity>> getLEDsAtivadoPorBotao()
			throws ArduinoException {

		List<LEDEntity> leds = buscaLEDsAtivadoPorBotao();

		LEDEntity[] array = getScheduler().buscaEstadoLEDs(getIDs(leds));

		for (LEDEntity l : array) {
			Pino id = l.getPino();
			EstadoLED estado = l.getEstado();

			for (LEDEntity led : leds)
				if (id.equals(led.getId())) {
					led.setEstado(estado);
					break;
				}
		}

		return new AsyncResult<List<LEDEntity>>(leds);
	}

	@Override
	public EstadoLED getEstadoLEDAtivadoPorBotao(byte pin) {
		// CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		// CriteriaQuery<Boolean> criteria = builder.createQuery(Boolean.class);
		//
		// Root<LEDEntity> root = criteria.from(getEntityType());
		//
		// criteria.select(root.get(LEDEntity_.ativo));
		//
		// Predicate isActiveButton = builder.isTrue(root
		// .get(LEDEntity_.ativadoPorBotao));
		// Predicate equalPin = builder.equal(root.get(LEDEntity_.id), new
		// PinPK(
		// ArduinoPinType.DIGITAL, Short.valueOf(pin)));
		//
		// Predicate and = builder.and(isActiveButton, equalPin);
		//
		// criteria.where(and);
		//
		// TypedQuery<Boolean> query = getEntityManager().createQuery(criteria);
		// Boolean active = query.getSingleResult();
		// if (active == Boolean.TRUE)
		// return EstadoLED.ACESO;

		return EstadoLED.APAGADO;
	}

}
