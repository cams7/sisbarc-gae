package br.com.cams7.sisbarc.aal.rest;

import java.util.Date;
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
import br.com.cams7.sisbarc.aal.domain.EmployeeEntity;
import br.com.cams7.sisbarc.aal.service.EmployeeService;

/**
 * Handles requests for the Employee service.
 */
@RestController
@RequestMapping("/employee")
public class EmployeeRestController extends
		BaseRestController<EmployeeService, EmployeeEntity, String> {

	public EmployeeRestController() {
		super();
	}

	@RequestMapping(value = "/dummy", method = RequestMethod.GET)
	public @ResponseBody EmployeeEntity getDummyEmployee() {
		getLog().info("Start getDummyEmployee");

		EmployeeEntity employee = new EmployeeEntity();
		employee.setName("Dummy");
		employee.setSalary(4500.);
		employee.setHireDate(new Date());

		employee = getService().insert(employee);
		return employee;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody EmployeeEntity getEmployee(
			@PathVariable("id") String employeeId) {
		getLog().info("Start getEmployee(id = " + employeeId + ")");

		EmployeeEntity employee = getService().findOne(employeeId);
		return employee;
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public @ResponseBody List<EmployeeEntity> getAllEmployees() {
		getLog().info("Start getAllEmployees");

		List<EmployeeEntity> allEmployees = getService().findAll();
		return allEmployees;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody EmployeeEntity createEmployee(
			@RequestBody EmployeeEntity employee) {
		getLog().info("Start createEmployee");

		employee = getService().insert(employee);
		return employee;
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public @ResponseBody EmployeeEntity updateEmployee(
			@PathVariable("id") String employeeId,
			@RequestBody EmployeeEntity employee) {
		getLog().info("Start updateEmployee");

		employee.setId(employeeId);
		employee = getService().save(employee);
		return employee;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<?> deleteEmployee(
			@PathVariable("id") String employeeId) {
		getLog().info("Start deleteEmployee");

		getService().delete(employeeId);

		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "O funcionario id '" + employeeId
				+ "' foi excluido com sucesso");
		ResponseEntity<?> response = new ResponseEntity<>(headers,
				HttpStatus.OK);

		return response;
	}

}