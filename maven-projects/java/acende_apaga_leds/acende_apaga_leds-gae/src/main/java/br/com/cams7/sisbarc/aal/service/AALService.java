/**
 * 
 */
package br.com.cams7.sisbarc.aal.service;

import java.util.List;
import java.util.concurrent.Future;

import br.com.cams7.app.domain.AbstractEntity;
import br.com.cams7.arduino.ArduinoException;
import br.com.cams7.gae.service.AppService;
import br.com.cams7.sisbarc.aal.repository.AALRepository;

/**
 * @author cams7
 *
 */
public interface AALService<E extends AbstractEntity> extends AppService<E>,
		AALRepository<E> {

	public Future<Boolean> atualizaPino(E entidade) throws ArduinoException;

	public Future<Boolean> sincronizaEventos(List<E> entidades)
			throws ArduinoException;

	public Future<Boolean> alteraEventos(List<E> entidades)
			throws ArduinoException;

}
