/**
 * 
 */
package br.com.cams7.sisbarc.aal.faces.convert;

import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

import org.springframework.stereotype.Component;

import br.com.cams7.sisbarc.aal.domain.AbstractPino.Evento;

/**
 * @author cams7
 *
 */
@Component(EventoConvert.CONVERT_NAME)
@FacesConverter(EventoConvert.CONVERT_NAME)
public class EventoConvert extends EnumConverter {

	public final static String CONVERT_NAME = "eventoConvert";

	/**
	 * 
	 */
	public EventoConvert() {
		super(Evento.class);
	}

}
