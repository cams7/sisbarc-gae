package br.com.cams7.webapp.service;

import br.com.cams7.app.domain.entity.UserEntity;
import br.com.cams7.webapp.repository.UserRepository;

public interface UserService extends AppService<UserEntity>, UserRepository {

}
