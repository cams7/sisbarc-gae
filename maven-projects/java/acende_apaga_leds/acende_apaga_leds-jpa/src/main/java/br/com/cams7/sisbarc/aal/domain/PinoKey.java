/**
 * 
 */
package br.com.cams7.sisbarc.aal.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.googlecode.objectify.annotation.Index;

import br.com.cams7.arduino.ArduinoPinType;

/**
 * @author cams7
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Index
public class PinoKey implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "{NotNull.pinoKey.tipo}")
	private ArduinoPinType tipo;

	@NotNull(message = "{NotNull.pinoKey.codigo}")
	private Byte codigo;

	/**
	 * 
	 */
	public PinoKey() {
		super();
	}

	public PinoKey(ArduinoPinType tipo, Byte codigo) {
		this();

		this.tipo = tipo;
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "[tipo = " + getTipo()
				+ ", codigo = " + getCodigo() + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (!(object instanceof PinoKey)) {
			return false;
		}
		PinoKey pino = (PinoKey) object;
		return this.tipo.equals(pino.tipo) && this.codigo.equals(pino.codigo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.tipo.hashCode();
		hash = hash * prime + this.codigo.hashCode();

		return hash;
	}

	public ArduinoPinType getTipo() {
		return tipo;
	}

	public void setTipo(ArduinoPinType tipo) {
		this.tipo = tipo;
	}

	public Byte getCodigo() {
		return codigo;
	}

	public void setCodigo(Byte codigo) {
		this.codigo = codigo;
	}

}
