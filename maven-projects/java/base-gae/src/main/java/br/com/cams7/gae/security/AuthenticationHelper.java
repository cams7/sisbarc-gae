/**
 * 
 */
package br.com.cams7.gae.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.cams7.app.domain.entity.UserEntity;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;

/**
 * @author cams7
 *
 */
public final class AuthenticationHelper {

	public static Authentication getAuthentication() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication auth = securityContext.getAuthentication();
		return auth;
	}

	public static UserEntity getCurrentUser() {
		return (UserEntity) getAuthentication().getPrincipal();
	}

	public static Ref<UserEntity> getRefUser(Long id) {
		Key<UserEntity> key = Key.create(UserEntity.class, id);
		return Ref.create(key);
	}

	public static Ref<UserEntity> getRefUser() {
		return getRefUser(getCurrentUser().getId());
	}

	public static void changeAuthentication(UserEntity user) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication auth = securityContext.getAuthentication();

		// Update the context with the full authentication
		securityContext.setAuthentication(new UserAuthentication(user, auth
				.getDetails()));
	}

}
