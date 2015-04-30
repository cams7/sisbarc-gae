package br.com.cams7.sisbarc.aal.repository;

import org.springframework.stereotype.Repository;

import br.com.cams7.sisbarc.aal.domain.EmployeeEntity;
import br.com.cams7.webapp.TomcatRepository;

@Repository
public interface EmployeeRepository extends TomcatRepository<EmployeeEntity, String> {

}
