/**
 * 
 */
package br.com.cams7.gae.service.impl;

import org.springframework.stereotype.Service;

import br.com.cams7.app.domain.entity.UserEntity;
import br.com.cams7.gae.ds.UserDS;
import br.com.cams7.gae.repository.UserRepository;
import br.com.cams7.gae.service.AbstractAppService;
import br.com.cams7.gae.service.UserService;

/**
 * @author cams7
 *
 */
@Service("userService")
public class UserServiceImpl extends
		AbstractAppService<UserRepository, UserDS, UserEntity> implements
		UserService {

	public UserServiceImpl() {
		super();
	}

	@Override
	public UserEntity findByGoogleId(String email) {
		return getRepository().findByGoogleId(email);
	}

}
