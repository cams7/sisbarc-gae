/**
 * 
 */
package br.com.cams7.sisbarc.aal.domain.entity;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.cams7.arduino.ArduinoPinType;
import br.com.cams7.sisbarc.aal.domain.AbstractPino;

/**
 * @author cams7
 *
 */
@XmlRootElement
@Document(collection = "potenciometro")
public class PotenciometroEntity extends AbstractPino {

	private static final long serialVersionUID = 1L;

	public PotenciometroEntity() {
		super();
	}

	public PotenciometroEntity(String id) {
		super(id);
	}

	/**
	 * @param tipo
	 * @param pino
	 */
	public PotenciometroEntity(ArduinoPinType tipo, Short pino) {
		super(tipo, pino);
	}

}
