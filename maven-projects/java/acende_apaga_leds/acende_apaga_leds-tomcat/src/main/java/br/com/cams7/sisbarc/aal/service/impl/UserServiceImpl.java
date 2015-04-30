package br.com.cams7.sisbarc.aal.service.impl;

import org.springframework.stereotype.Service;

import br.com.cams7.sisbarc.aal.domain.UserEntity;
import br.com.cams7.sisbarc.aal.repository.UserRepository;
import br.com.cams7.sisbarc.aal.service.UserService;
import br.com.cams7.webapp.TomcatServiceImpl;

@Service
public class UserServiceImpl extends
		TomcatServiceImpl<UserRepository, UserEntity, String> implements
		UserService {

	public UserServiceImpl() {
		super();
	}

}
