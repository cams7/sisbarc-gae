/**
 * 
 */
package br.com.cams7.sisbarc.aal.faces.convert;

import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

import org.springframework.stereotype.Component;

import br.com.cams7.sisbarc.aal.domain.entity.UsuarioEntity.Autorizacao;

/**
 * @author cams7
 *
 */
@Component(AutorizacaoConvert.CONVERT_NAME)
@FacesConverter(AutorizacaoConvert.CONVERT_NAME)
public class AutorizacaoConvert extends EnumConverter {

	public final static String CONVERT_NAME = "autorizacaoConvert";

	public AutorizacaoConvert() {
		super(Autorizacao.class);
	}

}
