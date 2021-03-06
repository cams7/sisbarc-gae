package br.com.cams7.sisbarc.aal.domain;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.data.annotation.Transient;

import br.com.cams7.app.domain.AbstractEntity;
import br.com.cams7.app.domain.entity.UserEntity;
import br.com.cams7.arduino.ArduinoPinType;
import br.com.cams7.sisbarc.aal.domain.entity.LEDEntity;
import br.com.cams7.sisbarc.aal.domain.entity.PotenciometroEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.condition.IfNotNull;

import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.Key;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({ LEDEntity.class, PotenciometroEntity.class })
public/* abstract */class Pino extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@com.googlecode.objectify.annotation.Id
	@org.springframework.data.annotation.Id
	private Long id;

	@NotNull
	@Index({ IfNotNull.class })
	private PinoKey pino;

	@NotNull(message = "{NotNull.pino.evento}")
	private Evento evento;

	@NotNull(message = "{NotNull.pino.alteraEvento}")
	private Boolean alteraEvento;

	@NotNull(message = "{NotNull.pino.intervalo}")
	private Intervalo intervalo;

	@NotNull(message = "{NotNull.pino.alteraIntervalo}")
	private Boolean alteraIntervalo;

	@XmlTransient
	@JsonIgnore
	@Transient
	@Parent
	private Key<UserEntity> user;

	public Pino() {
		super();
	}

	public Pino(Long id) {
		super(id);
	}

	public Pino(ArduinoPinType tipo, Byte pino) {
		setPino(new PinoKey(tipo, pino));
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "[id = " + getId()
				+ ", pino = " + getPino() + ", evento = " + getEvento()
				+ ",  alteraEvento = " + getAlteraEvento() + ", intervalo = "
				+ getIntervalo() + ", alteraIntervalo = "
				+ getAlteraIntervalo() + ", user = " + getUser() + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public PinoKey getPino() {
		return pino;
	}

	/**
	 * @param pin
	 *            the id to set
	 */
	public void setPino(PinoKey pino) {
		this.pino = pino;
	}

	/**
	 * @return the evento
	 */
	public Evento getEvento() {
		return evento;
	}

	/**
	 * @param evento
	 *            the evento to set
	 */
	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	/**
	 * @return the alteraEvento
	 */
	public Boolean getAlteraEvento() {
		return alteraEvento;
	}

	/**
	 * @param alteraEvento
	 *            the alteraEvento to set
	 */
	public void setAlteraEvento(Boolean alteraEvento) {
		this.alteraEvento = alteraEvento;
	}

	/**
	 * @return the intervalo
	 */
	public Intervalo getIntervalo() {
		return intervalo;
	}

	/**
	 * @param intervalo
	 *            the intervalo to set
	 */
	public void setIntervalo(Intervalo intervalo) {
		this.intervalo = intervalo;
	}

	/**
	 * @return the alteraIntervalo
	 */
	public Boolean getAlteraIntervalo() {
		return alteraIntervalo;
	}

	/**
	 * @param alteraIntervalo
	 *            the alteraIntervalo to set
	 */
	public void setAlteraIntervalo(Boolean alteraIntervalo) {
		this.alteraIntervalo = alteraIntervalo;
	}

	public Key<UserEntity> getUser() {
		return user;
	}

	public void setUser(Key<UserEntity> user) {
		this.user = user;
	}

	@XmlEnum
	public enum Evento {
		ACENDE_APAGA, // Acende ou apaga
		PISCA_PISCA, // Pisca-pisca
		FADE, // Acende ao poucos
		NENHUM; // Não faz nada

		public String value() {
			return name();
		}
	}

	@XmlEnum
	public enum Intervalo {
		INTERVALO_10MILISEGUNDOS, // 1/100 de segundo
		INTERVALO_50MILISEGUNDOS, // 1/20 de segundo
		INTERVALO_100MILISEGUNDOS, // 1/10 de segundo
		INTERVALO_1SEGUNDO, // 1 segundo
		INTERVALO_3SEGUNDOS, // 3 segundos
		INTERVALO_5SEGUNDOS, // 5 segundos
		INTERVALO_10SEGUNDOS, // 10 segundos
		SEM_INTERVALO; // O evento sera apenas executado quando for chamado

		public String value() {
			return name();
		}
	}

}
