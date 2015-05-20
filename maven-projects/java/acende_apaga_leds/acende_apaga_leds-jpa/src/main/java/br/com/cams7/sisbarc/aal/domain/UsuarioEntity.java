package br.com.cams7.sisbarc.aal.domain;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import br.com.cams7.domain.BaseEntity;

@Document(collection = "usuario")
public class UsuarioEntity extends BaseEntity<String> {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@NotBlank
	@Indexed(unique = true)
	@Field("login")
	private String nome;

	@NotBlank
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
