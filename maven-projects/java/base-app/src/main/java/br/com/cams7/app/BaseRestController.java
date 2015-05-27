package br.com.cams7.app;

import java.util.logging.Level;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cams7.domain.BaseEntity;
import br.com.cams7.util.AppException;

public abstract class BaseRestController<S extends BaseService<E>, E extends BaseEntity>
		extends BaseController<S, E> {

	public BaseRestController() {
		super();
	}

	@ExceptionHandler({ AppException.class })
	public @ResponseBody ResponseEntity<?> handleException(
			AppException exception) {
		String errorMessage = exception.getMessage();

		ResponseEntity<?> response = new ResponseEntity<>(
				getHeaders(errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
		getLog().log(Level.SEVERE, errorMessage, exception);

		return response;
	}

	protected HttpHeaders getHeaders(String message) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("errorMessage", message);
		return headers;
	}

}
