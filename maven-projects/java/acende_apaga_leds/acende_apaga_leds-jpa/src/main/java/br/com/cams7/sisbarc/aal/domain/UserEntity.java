package br.com.cams7.sisbarc.aal.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.cams7.domain.BaseEntity;

@Document(collection = "users")
public class UserEntity extends BaseEntity<String> {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String firstName;
	private String lastName;

	public UserEntity() {
		super();
	}

	public UserEntity(String id) {
		super(id);
	}

	public UserEntity(String firstName, String lastName) {
		this();

		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
