package br.com.cams7.sisbarc.aal.rest;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cams7.sisbarc.aal.service.EmployeeService;
import br.com.cams7.app.BaseRestController;

import br.com.cams7.sisbarc.aal.domain.EmployeeEntity;

/**
 * Handles requests for the Employee service.
 */
@RestController
@RequestMapping("/")
public class EmployeeRestController extends
		BaseRestController<EmployeeService, EmployeeEntity, String> {

	public EmployeeRestController() {
		super();
	}

	@RequestMapping(value = "/employee/dummy", method = RequestMethod.GET)
	public @ResponseBody EmployeeEntity getDummyEmployee() {
		getLog().info("Start getDummyEmployee");

		EmployeeEntity employee = new EmployeeEntity();
		employee.setName("Dummy");
		employee.setSalary(4500);
		employee.setHireDate(new Date());

		getService().save(employee);
		return employee;
	}

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public @ResponseBody EmployeeEntity getEmployee(
			@PathVariable("id") String employeeId) {
		getLog().info("Start getEmployee(id = " + employeeId + ")");

		EmployeeEntity employee = getService().findOne(employeeId);
		return employee;
	}

	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public @ResponseBody List<EmployeeEntity> getAllEmployees() {
		getLog().info("Start getAllEmployees");

		List<EmployeeEntity> allEmployees = getService().findAll();
		return allEmployees;
	}

	@RequestMapping(value = "/employee/create", method = RequestMethod.POST)
	public @ResponseBody EmployeeEntity createEmployee(
			@RequestBody EmployeeEntity employee) {
		getLog().info("Start createEmployee");

		getService().save(employee);
		return employee;
	}

	@RequestMapping(value = "/employee/update/{id}", method = RequestMethod.PUT)
	public @ResponseBody EmployeeEntity updateEmployee(
			@PathVariable("id") String employeeId,
			@RequestBody EmployeeEntity employee) {
		getLog().info("Start updateEmployee");

		employee.setId(employeeId);
		employee = getService().update(employee);
		return employee;
	}

	@RequestMapping(value = "/employee/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody EmployeeEntity deleteEmployee(
			@PathVariable("id") String employeeId) {
		getLog().info("Start deleteEmployee");

		EmployeeEntity employee = getService().remove(employeeId);
		return employee;
	}

}