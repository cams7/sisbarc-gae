/**
 * 
 */
package br.com.cams7.gae.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.cams7.app.domain.entity.UserEntity;

/**
 * @author cams7
 *
 */
public class UserValidator implements Validator {

	private final String IPADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	public UserValidator() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return UserEntity.class.isAssignableFrom(clazz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		final String IP_FIELD = "ip";

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, IP_FIELD,
				"label.usuario.ip.requiredMessage", "Field IP is required.");

		ValidationUtils
				.rejectIfEmptyOrWhitespace(errors, "port",
						"label.usuario.port.requiredMessage",
						"Field port is required.");

		UserEntity user = (UserEntity) target;

		String ip = user.getIp();

		if (errors.getFieldErrorCount(IP_FIELD) == 0
				&& !ip.matches(IPADDRESS_PATTERN))
			errors.rejectValue(IP_FIELD, "label.usuario.ip.invalidMessage",
					"IP invalid");

	}

}
