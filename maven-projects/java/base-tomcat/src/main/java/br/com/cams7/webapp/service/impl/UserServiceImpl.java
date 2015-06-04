package br.com.cams7.webapp.service.impl;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.cams7.app.domain.entity.UserEntity;
import br.com.cams7.webapp.repository.UserRepository;
import br.com.cams7.webapp.service.AbstractAppService;
import br.com.cams7.webapp.service.UserService;

@Service("userService")
public class UserServiceImpl extends
		AbstractAppService<UserRepository, UserEntity> implements UserService,
		UserDetailsService {

	public UserServiceImpl() {
		super();
	}

	@Override
	public UserEntity findByUsername(String username) {
		return getRepository().findByUsername(username);
	}

	@Override
	public <S extends UserEntity> S insert(S user) {
		String hashedPassword = getHashedPassword(user.getPassword());

		user.setPassword(hashedPassword);

		return super.insert(user);
	}

	@Override
	public <S extends UserEntity> S save(S user) {
		String hashedPassword = getHashedPassword(user.getPassword());

		user.setPassword(hashedPassword);

		return super.save(user);
	}

	private String getHashedPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}

	@Override
	public UserDetails loadUserByUsername(final String username)
			throws UsernameNotFoundException {

		if (username == null || "".equals(username))
			throw new UsernameNotFoundException("username is empty or null");

		UserEntity user = getRepository().findByUsername(username);

		return buildUserForAuthentication(user, user.getAuthorities());
	}

	// Converts com.mkyong.users.model.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(UserEntity user,
			Set<? extends GrantedAuthority> authorities) {
		String username = user.getUsername();
		String password = user.getPassword();
		boolean enabled = user.isEnabled();

		return new User(username, password, enabled, true, true, true,
				authorities);
	}

}
