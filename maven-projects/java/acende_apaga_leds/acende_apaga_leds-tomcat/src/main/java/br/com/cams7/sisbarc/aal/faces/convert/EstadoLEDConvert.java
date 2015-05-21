package br.com.cams7.sisbarc.aal.faces.convert;

import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

import org.springframework.stereotype.Component;

import br.com.cams7.sisbarc.aal.domain.entity.LEDEntity.EstadoLED;

@Component(EstadoLEDConvert.CONVERT_NAME)
@FacesConverter(EstadoLEDConvert.CONVERT_NAME)
public class EstadoLEDConvert extends EnumConverter {

	public final static String CONVERT_NAME = "estadoLEDConvert";

	public EstadoLEDConvert() {
		super(EstadoLED.class);
	}

}
