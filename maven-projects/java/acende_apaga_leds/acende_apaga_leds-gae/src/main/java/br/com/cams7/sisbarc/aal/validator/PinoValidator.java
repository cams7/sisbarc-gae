/**
 * 
 */
package br.com.cams7.sisbarc.aal.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.cams7.sisbarc.aal.domain.Pino;
import br.com.cams7.sisbarc.aal.domain.PinoKey;

/**
 * @author cams7
 *
 */
@Component
public class PinoValidator implements Validator {

	/**
	 * 
	 */
	public PinoValidator() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return Pino.class.isAssignableFrom(clazz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		Pino pino = (Pino) target;
		PinoKey key = pino.getPino();

		if (key.getTipo() == null)
			errors.rejectValue("pino.tipo", "label.pino.tipo.requiredMessage",
					"Field pin type is required");

		final String PINO_FIELD = "pino.codigo";

		if (errors.getFieldErrorCount(PINO_FIELD) == 0
				&& key.getCodigo() == null)
			errors.rejectValue(PINO_FIELD, "label.pino.codigo.requiredMessage",
					"Field pin is required");

	}

}
