/**
 * 
 */
package br.com.cams7.sisbarc.aal.faces.convert;

import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

import org.springframework.stereotype.Component;

import br.com.cams7.app.domain.entity.UserEntity.Role;

/**
 * @author cams7
 *
 */
@Component(RoleConvert.CONVERT_NAME)
@FacesConverter(RoleConvert.CONVERT_NAME)
public class RoleConvert extends EnumConverter {

	public final static String CONVERT_NAME = "roleConvert";

	public RoleConvert() {
		super(Role.class);
	}

}
