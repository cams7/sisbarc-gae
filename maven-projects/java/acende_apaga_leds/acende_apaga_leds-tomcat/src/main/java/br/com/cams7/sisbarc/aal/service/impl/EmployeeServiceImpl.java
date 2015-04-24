package br.com.cams7.sisbarc.aal.service.impl;

import org.springframework.stereotype.Service;

import br.com.cams7.sisbarc.aal.repository.EmployeeRepository;
import br.com.cams7.sisbarc.aal.service.EmployeeService;
import br.com.cams7.webapp.TomcatServiceImpl;

import br.com.cams7.sisbarc.aal.domain.EmployeeEntity;

@Service
public class EmployeeServiceImpl extends
		TomcatServiceImpl<EmployeeRepository, EmployeeEntity, String> implements
		EmployeeService {

	public EmployeeServiceImpl() {
		super();
	}

}
