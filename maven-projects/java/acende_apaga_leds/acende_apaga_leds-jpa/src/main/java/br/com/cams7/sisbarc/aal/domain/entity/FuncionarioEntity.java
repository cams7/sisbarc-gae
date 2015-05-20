package br.com.cams7.sisbarc.aal.domain.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.cams7.domain.BaseEntity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

@Document(collection = "funcionario")
public class FuncionarioEntity extends BaseEntity<String> {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	/**
	 * Nome do funcionario
	 */
	@NotBlank
	@Indexed
	private String nome;

	/**
	 * Data de admissao do funcionario
	 */
	@NotNull
	private Date admissao;

	/**
	 * Salario do funcionario
	 */
	@NotNull
	private Double salario;

	/**
	 * Data de demissao do funcionario
	 */
	private Date demissao;

	private boolean ativo;

	// @Version
	// private long version;

	public FuncionarioEntity() {
		super();
	}

	public FuncionarioEntity(String id) {
		super(id);
	}

	@Override
	public String toString() {
		return this.getClass().getName() + "[id = " + getId() + ", nome = "
				+ getNome() + ", admissao = " + getAdmissao() + ", salario = "
				+ getSalario() + ", demissao = " + getDemissao() + ", ativo = "
				+ isAtivo() + "]";
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

	@JsonSerialize(using = DateSerializer.class)
	public Date getAdmissao() {
		return admissao;
	}

	public void setAdmissao(Date admissao) {
		this.admissao = admissao;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	@JsonSerialize(using = DateSerializer.class)
	public Date getDemissao() {
		return demissao;
	}

	public void setDemissao(Date demissao) {
		this.demissao = demissao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
