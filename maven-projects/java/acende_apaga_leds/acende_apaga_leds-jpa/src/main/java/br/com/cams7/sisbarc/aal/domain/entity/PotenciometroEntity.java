/**
 * 
 */
package br.com.cams7.sisbarc.aal.domain.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.cams7.arduino.ArduinoPinType;
import br.com.cams7.sisbarc.aal.domain.Pino;

import com.googlecode.objectify.annotation.Entity;

/**
 * @author cams7
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Entity(name = PotenciometroEntity.ENTITY_NAME)
@Document(collection = PotenciometroEntity.ENTITY_NAME)
public class PotenciometroEntity extends Pino {

	private static final long serialVersionUID = 1L;

	public static final String ENTITY_NAME = "potenciometro";

	public PotenciometroEntity() {
		super();
	}

	public PotenciometroEntity(Long id) {
		super(id);
	}

	/**
	 * @param tipo
	 * @param pino
	 */
	public PotenciometroEntity(ArduinoPinType tipo, Byte pino) {
		super(tipo, pino);
	}

}
