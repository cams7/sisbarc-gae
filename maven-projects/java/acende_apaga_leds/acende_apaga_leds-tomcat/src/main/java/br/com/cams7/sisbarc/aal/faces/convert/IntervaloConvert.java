/**
 * 
 */
package br.com.cams7.sisbarc.aal.faces.convert;

import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

import org.springframework.stereotype.Component;

import br.com.cams7.sisbarc.aal.domain.AbstractPino.Intervalo;

/**
 * @author cams7
 *
 */
@Component(IntervaloConvert.CONVERT_NAME)
@FacesConverter(IntervaloConvert.CONVERT_NAME)
public class IntervaloConvert extends EnumConverter {

	public final static String CONVERT_NAME = "intervaloConvert";

	/**
	 * 
	 */
	public IntervaloConvert() {
		super(Intervalo.class);
	}

}
