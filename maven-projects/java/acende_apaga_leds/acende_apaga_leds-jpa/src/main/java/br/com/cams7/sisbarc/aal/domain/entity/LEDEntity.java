/**
 * 
 */
package br.com.cams7.sisbarc.aal.domain.entity;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlEnum;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.cams7.arduino.ArduinoPinType;
import br.com.cams7.sisbarc.aal.domain.Pino;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.condition.IfNotNull;
import com.googlecode.objectify.condition.IfTrue;

/**
 * @author cams7
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Entity(name = LEDEntity.ENTITY_NAME)
@Document(collection = LEDEntity.ENTITY_NAME)
public class LEDEntity extends Pino {

	private static final long serialVersionUID = 1L;

	public static final String ENTITY_NAME = "led";

	@NotNull(message = "{NotNull.led.cor}")
	private CorLED cor;

	@NotNull(message = "{NotNull.led.ativo}")
	private Boolean ativo;

	@NotNull(message = "{NotNull.led.ativadoPorBotao}")
	@Index({ IfNotNull.class, IfTrue.class })
	private Boolean ativadoPorBotao;

	@Transient
	@Ignore
	private EstadoLED estado;

	public LEDEntity() {
		super();
	}

	public LEDEntity(Long id) {
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

	@XmlEnum
	public enum CorLED {
		AMARELO, // LED Amarela
		VERDE, // LED Verde
		VERMELHO; // LED Vermelha

		public String value() {
			return name();
		}
	}

	@XmlEnum
	public enum EstadoLED {
		ACESO, // Acende
		APAGADO;// Apaga

		public String value() {
			return name();
		}
	}

}
