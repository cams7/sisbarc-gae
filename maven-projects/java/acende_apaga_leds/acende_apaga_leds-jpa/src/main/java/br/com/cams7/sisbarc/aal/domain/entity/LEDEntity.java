/**
 * 
 */
package br.com.cams7.sisbarc.aal.domain.entity;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.cams7.arduino.ArduinoPinType;
import br.com.cams7.sisbarc.aal.domain.Pino;

/**
 * @author cams7
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ledEntity", propOrder = { "cor", "ativo", "ativadoPorBotao",
		"estado" })
@Document(collection = "led")
public class LEDEntity extends Pino {

	private static final long serialVersionUID = 1L;

	@NotNull
	private CorLED cor;

	@NotNull
	private Boolean ativo;

	@NotNull
	private Boolean ativadoPorBotao;

	@Transient
	private EstadoLED estado;

	public LEDEntity() {
		super();
	}

	public LEDEntity(String id) {
		super(id);
	}

	public LEDEntity(ArduinoPinType tipo, Byte pino) {
		super(tipo, pino);
	}

	@Override
	public String toString() {
		return super.toString() + "[cor = " + getCor() + ", ativo = "
				+ getAtivo() + ", ativadoPorBotao = " + getAtivadoPorBotao()
				+ ", estado = " + getEstado() + "]";
	}

	public CorLED getCor() {
		return cor;
	}

	public void setCor(CorLED cor) {
		this.cor = cor;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Boolean getAtivadoPorBotao() {
		return ativadoPorBotao;
	}

	public void setAtivadoPorBotao(Boolean ativadoPorBotao) {
		this.ativadoPorBotao = ativadoPorBotao;
	}

	public EstadoLED getEstado() {
		return estado;
	}

	public void setEstado(EstadoLED estado) {
		this.estado = estado;
	}

	@XmlType(name = "corLED")
	@XmlEnum
	public enum CorLED {
		AMARELO, // LED Amarela
		VERDE, // LED Verde
		VERMELHO; // LED Vermelha

		public String value() {
			return name();
		}

		public static CorLED fromValue(String value) {
			return valueOf(value);
		}
	}

	@XmlType(name = "estadoLED")
	@XmlEnum
	public enum EstadoLED {
		ACESO, // Acende
		APAGADO;// Apaga

		public String value() {
			return name();
		}

		public static EstadoLED fromValue(String value) {
			return valueOf(value);
		}
	}

}
