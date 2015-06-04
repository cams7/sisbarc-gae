/**
 * 
 */
package br.com.cams7.gae.service;

import br.com.cams7.app.domain.entity.UserEntity;
import br.com.cams7.gae.repository.UserRepository;

/**
 * @author cams7
 *
 */
public interface UserService extends AppService<UserEntity>, UserRepository {

}
