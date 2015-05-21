/**
 * 
 */
package br.com.cams7.sisbarc.aal.faces.convert;

import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

import org.springframework.stereotype.Component;

import br.com.cams7.sisbarc.aal.domain.entity.LEDEntity.CorLED;

/**
 * @author cams7
 *
 */
@Component(CorLEDConvert.CONVERT_NAME)
@FacesConverter(CorLEDConvert.CONVERT_NAME)
public class CorLEDConvert extends EnumConverter {

	public final static String CONVERT_NAME = "corLEDConvert";

	public CorLEDConvert() {
		super(CorLED.class);
	}

}
