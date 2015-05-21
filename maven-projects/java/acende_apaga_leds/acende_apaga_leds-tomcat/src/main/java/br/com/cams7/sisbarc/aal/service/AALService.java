package br.com.cams7.sisbarc.aal.service;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Future;

import br.com.cams7.arduino.ArduinoException;
import br.com.cams7.domain.BaseEntity;
import br.com.cams7.webapp.AppService;

/**
 * @author cams7
 *
 */
public interface AALService<E extends BaseEntity<ID>, ID extends Serializable>
		extends AppService<E, ID> {

	public Future<Boolean> atualizaPino(E entidade) throws ArduinoException;

	public Future<Boolean> sincronizaEventos(List<E> entidades)
			throws ArduinoException;

	public Future<Boolean> alteraEventos(List<E> entidades)
			throws ArduinoException;

}
