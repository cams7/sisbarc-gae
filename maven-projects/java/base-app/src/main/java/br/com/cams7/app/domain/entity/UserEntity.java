package br.com.cams7.app.domain.entity;

import java.util.Set;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import br.com.cams7.app.domain.AbstractEntity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.condition.IfNotEmpty;
import com.googlecode.objectify.condition.IfNotNull;

@Entity(name = UserEntity.ENTITY_NAME)
@Document(collection = UserEntity.ENTITY_NAME)
public class UserEntity extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	public static final String ENTITY_NAME = "user";

	@com.googlecode.objectify.annotation.Id
	@org.springframework.data.annotation.Id
	private Long id;

	@Indexed
	@Index({ IfNotNull.class, IfNotEmpty.class })
	private String googleId;

	// @NotBlank(message = "{NotBlank.user.username}")
	// @Indexed(unique = true)
	private String username;

	@NotBlank(message = "{NotBlank.user.email}")
	@Email
	@Indexed(unique = true)
	private String email;

	@Ignore
	private String password;

	private boolean enabled;

	private Set<Role> authorities;

	@Transient
	private String ip;

	@Transient
	private Short port;

	public UserEntity() {
		super();
	}

	@Override
	public String toString() {
		return this.getClass().getName() + "[id = " + getId() + ", googleId = "
				+ getGoogleId() + ", username = " + getUsername()
				+ ", email = " + getEmail() + ", password = " + getPassword()
				+ ", enabled = " + isEnabled() + ", authorities = "
				+ getAuthorities() + ", ip = " + getIp() + ", port = "
				+ getPort() + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGoogleId() {
		return googleId;
	}

	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Role> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Role> authorities) {
		this.authorities = authorities;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Short getPort() {
		return port;
	}

	public void setPort(Short port) {
		this.port = port;
	}

	public String getAddress() {
		return "http://" + getIp() + ":" + getPort();
	}

	public enum Role implements GrantedAuthority {
		ROLE_USER, ROLE_NEWUSER, ROLE_ADMIN;

		@Override
		public String getAuthority() {
			return toString();
		}
	}

}
