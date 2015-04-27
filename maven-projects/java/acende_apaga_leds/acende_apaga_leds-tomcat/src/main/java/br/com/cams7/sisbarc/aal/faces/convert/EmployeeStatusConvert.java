/**
 * 
 */
package br.com.cams7.sisbarc.aal.faces.convert;

import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

import org.springframework.stereotype.Component;

import br.com.cams7.sisbarc.aal.domain.EmployeeEntity;

/**
 * @author cams7
 *
 */
@Component(EmployeeStatusConvert.CONVERT_NAME)
@FacesConverter(EmployeeStatusConvert.CONVERT_NAME)
public class EmployeeStatusConvert extends EnumConverter {

	public final static String CONVERT_NAME = "employeeStatusConvert";

	public EmployeeStatusConvert() {
		super(EmployeeEntity.Status.class);
	}

}
