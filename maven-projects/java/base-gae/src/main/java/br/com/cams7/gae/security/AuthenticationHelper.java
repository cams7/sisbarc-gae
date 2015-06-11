/**
 * 
 */
package br.com.cams7.gae.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.cams7.app.domain.entity.UserEntity;

import com.googlecode.objectify.Key;

/**
 * @author cams7
 *
 */
public final class AuthenticationHelper {

	public static Authentication getAuthentication() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		return auth;
	}

	public static UserEntity getCurrentUser() {
		return (UserEntity) getAuthentication().getPrincipal();
	}

	public static Key<UserEntity> getKeyUser(Long id) {
		Key<UserEntity> key = Key.create(UserEntity.class, id);
		return key;
	}

	public static Key<UserEntity> getKeyUser() {
		return getKeyUser(getCurrentUser().getId());
	}

	public static void changeAuthentication(UserEntity user) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		// Update the context with the full authentication
		SecurityContextHolder.getContext().setAuthentication(
				new UserAuthentication(user, auth.getDetails()));
	}

}
