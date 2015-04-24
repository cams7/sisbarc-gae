package br.com.cams7.sisbarc.aal.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.cams7.sisbarc.aal.repository.EmployeeRepository;
import br.com.cams7.webapp.TomcatRepositoryImpl;

import br.com.cams7.sisbarc.aal.domain.EmployeeEntity;

@Repository
public class EmployeeRepositoryImpl extends
		TomcatRepositoryImpl<EmployeeEntity, String> implements
		EmployeeRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public EmployeeRepositoryImpl() {
		super();
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
