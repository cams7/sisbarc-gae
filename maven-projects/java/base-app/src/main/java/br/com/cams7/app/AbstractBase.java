/**
 * 
 */
package br.com.cams7.app;

//import java.util.logging.Logger;
import org.apache.log4j.Logger;

import br.com.cams7.domain.BaseEntity;
import br.com.cams7.util.AppUtil;

/**
 * @author cesar
 *
 */
public abstract class AbstractBase<E extends BaseEntity<?>> {

	private final byte ENTITY_ARGUMENT_NUMBER = 0;

	private Logger log;

	private Class<E> entityType;

	@SuppressWarnings("unchecked")
	public AbstractBase() {
		super();

		log = Logger.getLogger(this.getClass());
		entityType = (Class<E>) AppUtil
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
