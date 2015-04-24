package br.com.cams7.app;

import java.io.Serializable;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cams7.domain.BaseEntity;

public abstract class BaseRestController<S extends BaseService<E, ID>, E extends BaseEntity<ID>, ID extends Serializable>
		extends BaseController<S, E, ID> {

	public BaseRestController() {
		super();
	}

	@ExceptionHandler({ Exception.class })
	public @ResponseBody ResponseEntity<?> handleException(Exception exception) {
		String errorMessage = exception.getMessage();

		ResponseEntity<?> response = new ResponseEntity<>(
				getHeaders(errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
		getLog().error(errorMessage, exception);

		return response;
	}

	protected HttpHeaders getHeaders(String message) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("errorMessage", message);
		return headers;
	}

}
