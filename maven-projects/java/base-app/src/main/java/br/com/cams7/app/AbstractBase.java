/**
 * 
 */
package br.com.cams7.app;

//import org.apache.log4j.Logger;

import java.util.logging.Logger;

import br.com.cams7.app.domain.AbstractEntity;
import br.com.cams7.util.AppHelper;

/**
 * @author cesar
 *
 */
public abstract class AbstractBase<E extends AbstractEntity> {

	private final byte ENTITY_ARGUMENT_NUMBER = 0;

	private Logger log;

	private Class<E> entityType;

	@SuppressWarnings("unchecked")
	public AbstractBase() {
		super();

		log = Logger.getLogger(this.getClass().getName());
		entityType = (Class<E>) AppHelper
				.getType(this, getEntityArgumentNumber());
	}

	protected Logger getLog() {
		return log;
	}

	protected byte getEntityArgumentNumber() {
		return ENTITY_ARGUMENT_NUMBER;
	}

	protected Class<E> getEntityType() {
		return entityType;
	}

}
