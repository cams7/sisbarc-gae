package br.com.cams7.sisbarc.aal.rest;

import java.util.Map;
import java.util.HashMap;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cams7.sisbarc.aal.service.GreeterService;

@RestController
@RequestMapping("/rest")
public class GreeterRestController {

	@Autowired
	private GreeterService service;

	public GreeterRestController() {
		super();
	}

	@RequestMapping(value = "/hello/{usuario}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> sayHello(
			@PathVariable("usuario") String usuario) {

		String mensagem = service.sayHello(usuario);

		Map<String, String> map = new HashMap<String, String>();
		map.put("mensagem", mensagem);

		ResponseEntity<?> response = new ResponseEntity<>(map, HttpStatus.OK);

		return response;
	}

}
