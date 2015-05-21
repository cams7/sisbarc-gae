/**
 * 
 */
package br.com.cams7.sisbarc.aal.faces.convert;

import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

import org.springframework.stereotype.Component;

import br.com.cams7.arduino.ArduinoPinType;

/**
 * @author cams7
 *
 */
@Component(TipoPinoConvert.CONVERT_NAME)
@FacesConverter(TipoPinoConvert.CONVERT_NAME)
public class TipoPinoConvert extends EnumConverter {

	public final static String CONVERT_NAME = "tipoPinoConvert";

	/**
	 * 
	 */
	public TipoPinoConvert() {
		super(ArduinoPinType.class);
	}

}
