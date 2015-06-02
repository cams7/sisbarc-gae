package br.com.cams7.app.domain.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.googlecode.objectify.annotation.Entity;

import br.com.cams7.app.domain.AbstractEntity;

@Entity
@Document(collection = "usuario")
public class UsuarioEntity extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@com.googlecode.objectify.annotation.Id
	@org.springframework.data.annotation.Id
	private Long id;

	@NotBlank(message = "{NotBlank.usuario.nome}")
	@Indexed(unique = true)
	@Field("login")
	private String nome;

	@NotBlank(message = "{NotBlank.usuario.email}")
	@Email
	private String email;

	private String senha;

	private boolean ativo;

	private Autorizacao[] autorizacoes;

	public UsuarioEntity() {
		super();
	}

	@Override
	public String toString() {
		return this.getClass().getName() + "[id = " + getId() + ", nome = "
				+ getNome() + ", senha = " + getSenha() + ", autorizacoes = "
				+ getAutorizacoes() + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Autorizacao[] getAutorizacoes() {
		return autorizacoes;
	}

	public void setAutorizacoes(Autorizacao[] autorizacoes) {
		this.autorizacoes = autorizacoes;
	}

	public enum Autorizacao {
		ROLE_USER, ROLE_ADMIN
	}

}
