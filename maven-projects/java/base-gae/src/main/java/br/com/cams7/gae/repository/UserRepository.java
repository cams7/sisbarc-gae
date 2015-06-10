/**
 * 
 */
package br.com.cams7.gae.repository;

import br.com.cams7.app.domain.entity.UserEntity;

/**
 * @author cams7
 *
 */
public interface UserRepository extends AppRepository<UserEntity> {
	public UserEntity findByGoogleId(String email);
}
