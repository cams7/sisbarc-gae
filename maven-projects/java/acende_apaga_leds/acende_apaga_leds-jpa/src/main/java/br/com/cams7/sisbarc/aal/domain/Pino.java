/**
 * 
 */
package br.com.cams7.sisbarc.aal.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import br.com.cams7.arduino.ArduinoPinType;

/**
 * @author cams7
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pino", propOrder = { "tipo", "codigo" })
public class Pino implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	private ArduinoPinType tipo;

	@NotNull
	private Short codigo;

	/**
	 * 
	 */
	public Pino() {
		super();
	}

	public Pino(ArduinoPinType tipo, Short codigo) {
		this();

		this.tipo = tipo;
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "Pino[tipo = " + getTipo() + ", codigo = " + getCodigo() + "]";
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
		if (!(object instanceof Pino)) {
			return false;
		}
		Pino pino = (Pino) object;
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

	public Short getCodigo() {
		return codigo;
	}

	public void setCodigo(Short codigo) {
		this.codigo = codigo;
	}

}
