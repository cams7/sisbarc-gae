package br.com.cams7.sisbarc.aal.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cams7.sisbarc.aal.domain.UserEntity;
import br.com.cams7.sisbarc.aal.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserRestController {

	@Autowired
	private UserRepository repository;

	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public @ResponseBody List<UserEntity> findAll() {
		List<UserEntity> users = repository.findAll();

		// users = repository.findAll(org.springframework.data.domain.Sort);

		// org.springframework.data.domain.Page<UserEntity> page =
		// repository.findAll(org.springframework.data.domain.Pageable);

		// Iterable<UserEntity> iterable = repository.findAll(Iterable);

		return users;
	}

	@RequestMapping(value = "/findOne/{id}", method = RequestMethod.GET)
	public @ResponseBody UserEntity findOne(@PathVariable("id") String userId) {
		UserEntity user = repository.findOne(userId);
		return user;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public @ResponseBody UserEntity insert(@RequestBody UserEntity user) {
		user = repository.insert(user);

		// List<UserEntity> users = insert(Iterable<UserEntity>)
		return user;
	}

	@RequestMapping(value = "/save/{id}", method = RequestMethod.PUT)
	public @ResponseBody UserEntity save(@PathVariable("id") String userId,
			@RequestBody UserEntity user) {
		user.setId(userId);
		user = repository.save(user);

		// List<UserEntity> users = save(Iterable<UserEntity>)
		return user;
	}

	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Map<String, Long>> count() {
		long count = repository.count();

		Map<String, Long> body = new HashMap<String, Long>();
		body.put("count", count);

		ResponseEntity<Map<String, Long>> response = new ResponseEntity<Map<String, Long>>(
				body, HttpStatus.OK);

		return response;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<?> delete(
			@PathVariable("id") String userId) {
		repository.delete(userId);

		// repository.delete(UserEntity);
		// repository.delete(Iterable<UserEntity>);

		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "O usuario id '" + userId
				+ "' foi excluido com sucesso");
		ResponseEntity<?> response = new ResponseEntity<>(headers,
				HttpStatus.OK);

		return response;
	}

	@RequestMapping(value = "/deleteAll", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<?> deleteAll() {
		repository.deleteAll();

		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Todos os usuarios foram excluidos com sucesso");
		ResponseEntity<?> response = new ResponseEntity<>(headers,
				HttpStatus.OK);

		return response;
	}

	@RequestMapping(value = "/exists/{id}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Map<String, Boolean>> exists(
			@PathVariable("id") String userId) {
		boolean exists = repository.exists(userId);

		Map<String, Boolean> body = new HashMap<String, Boolean>();
		body.put("exists", exists);

		ResponseEntity<Map<String, Boolean>> response = new ResponseEntity<Map<String, Boolean>>(
				body, HttpStatus.OK);

		return response;
	}

}
