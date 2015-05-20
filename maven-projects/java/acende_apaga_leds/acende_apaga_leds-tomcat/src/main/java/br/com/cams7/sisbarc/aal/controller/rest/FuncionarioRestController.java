package br.com.cams7.sisbarc.aal.controller.rest;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cams7.app.BaseRestController;
import br.com.cams7.sisbarc.aal.domain.FuncionarioEntity;
import br.com.cams7.sisbarc.aal.service.FuncionarioService;

/**
 * Handles requests for the Funcionario service.
 */
@RestController
@RequestMapping("/funcionario")
public class FuncionarioRestController extends
		BaseRestController<FuncionarioService, FuncionarioEntity, String> {

	public FuncionarioRestController() {
		super();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody FuncionarioEntity getFuncionario(
			@PathVariable("id") String id) {
		getLog().info("Start getFuncionario(id = " + id + ")");

		FuncionarioEntity funcionario = getService().findOne(id);
		return funcionario;
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public @ResponseBody List<FuncionarioEntity> getFuncionarios() {
		getLog().info("Start getFuncionarios");

		List<FuncionarioEntity> funcionarios = getService().findAll();
		return funcionarios;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody FuncionarioEntity createFuncionario(
			@RequestBody FuncionarioEntity funcionario) {
		getLog().info("Start createFuncionario");

		funcionario = getService().insert(funcionario);
		return funcionario;
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public @ResponseBody FuncionarioEntity updateFuncionario(
			@PathVariable("id") String id,
			@RequestBody FuncionarioEntity funcionario) {
		getLog().info("Start updateFuncionario");

		funcionario.setId(id);
		funcionario = getService().save(funcionario);
		return funcionario;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<?> deleteFuncionario(
			@PathVariable("id") String id) {
		getLog().info("Start deleteFuncionario");

		getService().delete(id);

		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "O funcionario id '" + id
				+ "' foi excluido com sucesso");
		ResponseEntity<?> response = new ResponseEntity<>(headers,
				HttpStatus.OK);

		return response;
	}

}