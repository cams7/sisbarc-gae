package br.com.cams7.sisbarc.aal.domain;

import java.util.Date;

//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.Version;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import br.com.cams7.domain.BaseEntity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

//import javax.persistence.Basic;

@Document(collection = "funcionario")
public class EmployeeEntity extends BaseEntity<String> {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	/**
	 * Nome do funcionario
	 */
	// @NotNull
	// @Size(min = 3, max = 30)
	// @Basic
	@Field("nome")
	private String name;

	/**
	 * Data de admissão do funcionario
	 */
	// @Basic
	@Field("admissao")
	private Date hireDate;

	/**
	 * Salario do funcionario
	 */
	// @NotNull
	// @Basic
	@Field("salario")
	private Double salary;

	/**
	 * Data de demissão do funcionario
	 */
	// @Basic
	@Field("demissao")
	private Date resignationDate;

	// @NotNull
	// @Basic
	// @Enumerated(EnumType.ORDINAL)
	// @Field("situacao")
	// private Status status;

	// @NotNull
	// @Basic
	@Field("ativo")
	private Boolean active;

	// @Version
	// private long version;

	public EmployeeEntity() {
		super();
	}

	public EmployeeEntity(String id) {
		super(id);
	}

	@Override
	public String toString() {
		return this.getClass().getName() + "[id = " + getId() + ", name = "
				+ getName() + ", hireDate = " + getHireDate() + ", salary = "
				+ getSalary() + ", resignationDate = " + getResignationDate()
				+ ", active = " + getActive() + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonSerialize(using = DateSerializer.class)
	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	@JsonSerialize(using = DateSerializer.class)
	public Date getResignationDate() {
		return resignationDate;
	}

	public void setResignationDate(Date resignationDate) {
		this.resignationDate = resignationDate;
	}

	// public Status getStatus() {
	// return status;
	// }
	//
	// public void setStatus(Status status) {
	// this.status = status;
	// }

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	// public long getVersion() {
	// return version;
	// }

	// public void setVersion(long version) {
	// this.version = version;
	// }

	public enum Status {
		HIRED, // Contratado
		FIRED// Demitido
	}

}
