/**
 * 
 */
package br.com.cams7.sisbarc.aal.domain.entity;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.mongodb.core.mapping.Document;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import br.com.cams7.arduino.ArduinoPinType;
import br.com.cams7.sisbarc.aal.domain.Pino;

/**
 * @author cams7
 *
 */
@XmlRootElement
@Entity
@Index
@Document(collection = "potenciometro")
public class PotenciometroEntity extends Pino {

	private static final long serialVersionUID = 1L;

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
