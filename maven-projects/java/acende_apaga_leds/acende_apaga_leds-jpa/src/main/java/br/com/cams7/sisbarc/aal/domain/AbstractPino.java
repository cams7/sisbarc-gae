package br.com.cams7.sisbarc.aal.domain;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import br.com.cams7.arduino.ArduinoPinType;
import br.com.cams7.domain.BaseEntity;
import br.com.cams7.sisbarc.aal.domain.entity.LEDEntity;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "abstractPino", propOrder = { "id", "pino", "evento",
		"alteraEvento", "intervalo", "alteraIntervalo" })
@XmlSeeAlso({ LEDEntity.class })
public abstract class AbstractPino extends BaseEntity<String> {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@NotNull
	// @Indexed(unique = true)
	private Pino pino;

	@NotNull
	private Evento evento;

	private boolean alteraEvento;

	@NotNull
	private Intervalo intervalo;

	private boolean alteraIntervalo;

	public AbstractPino() {
		super();
	}

	public AbstractPino(String id) {
		super(id);
	}

	public AbstractPino(ArduinoPinType pinType, Short pino) {
		setPino(new Pino(pinType, pino));
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	// @XmlJavaTypeAdapter(PinIDAdapter.class)
	public Pino getPino() {
		return pino;
	}

	/**
	 * @param pin
	 *            the id to set
	 */
	public void setPino(Pino pino) {
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
	public boolean isAlteraEvento() {
		return alteraEvento;
	}

	/**
	 * @param alteraEvento
	 *            the alteraEvento to set
	 */
	public void setAlteraEvento(boolean alteraEvento) {
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
	public boolean isAlteraIntervalo() {
		return alteraIntervalo;
	}

	/**
	 * @param alteraIntervalo
	 *            the alteraIntervalo to set
	 */
	public void setAlteraIntervalo(boolean alteraIntervalo) {
		this.alteraIntervalo = alteraIntervalo;
	}

	@XmlType(name = "evento")
	@XmlEnum
	public enum Evento {
		ACENDE_APAGA, // Acende ou apaga
		PISCA_PISCA, // Pisca-pisca
		FADE, // Acende ao poucos
		NENHUM; // Não faz nada

		public String value() {
			return name();
		}

		public static Evento fromValue(String value) {
			return valueOf(value);
		}
	}

	@XmlType(name = "intervalo")
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

		public static Intervalo fromValue(String value) {
			return valueOf(value);
		}
	}

}
