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
import br.com.cams7.sisbarc.aal.domain.AbstractPino;

/**
 * @author cams7
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ledEntity", propOrder = { "cor", "ativo", "ativadoPorBotao",
		"estado" })
@Document(collection = "led")
public class LEDEntity extends AbstractPino {

	private static final long serialVersionUID = 1L;

	@NotNull
	private CorLED cor;

	private boolean ativo;

	private boolean ativadoPorBotao;

	@Transient
	private EstadoLED estado;

	public LEDEntity() {
		super();
	}

	public LEDEntity(String id) {
		super(id);
	}

	public LEDEntity(ArduinoPinType tipo, Short pino) {
		super(tipo, pino);
	}

	public CorLED getCor() {
		return cor;
	}

	public void setCor(CorLED cor) {
		this.cor = cor;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean isAtivadoPorBotao() {
		return ativadoPorBotao;
	}

	public void setAtivadoPorBotao(boolean ativadoPorBotao) {
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
