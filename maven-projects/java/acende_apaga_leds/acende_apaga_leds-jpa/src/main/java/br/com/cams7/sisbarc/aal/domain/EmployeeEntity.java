package br.com.cams7.sisbarc.aal.domain;

import java.util.Date;

//import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;

import br.com.cams7.domain.BaseEntity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

@Entity
@NoSql(dataType = "funcionario", dataFormat = DataFormatType.MAPPED)
@NamedQueries({ @NamedQuery(name = "Employee.findAll", query = "SELECT employee FROM EmployeeEntity employee") })
public class EmployeeEntity extends BaseEntity<String> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Field(name = MONGODB_ID)
	private String id;

	/**
	 * Nome do funcionario
	 */
	@NotNull
	@Size(min = 3, max = 30)
	// @Basic
	@Field(name = "nome")
	private String name;

	/**
	 * Data de admissão do funcionario
	 */
	// @Basic
	@Field(name = "admissao")
	private Date hireDate;

	/**
	 * Salario do funcionario
	 */
	@NotNull
	// @Basic
	@Field(name = "salario")
	private Double salary;

	/**
	 * Data de demissão do funcionario
	 */
	// @Basic
	@Field(name = "demissao")
	private Date resignationDate;

	@NotNull
	// @Basic
	@Enumerated(EnumType.ORDINAL)
	@Field(name = "situacao")
	private Status status;

	@NotNull
	// @Basic
	@Field(name = "ativo")
	private Boolean active;

	@Version
	private long version;

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
				+ ", status = " + getStatus() + ", active = " + getActive()
				+ "]";
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

	public Date getResignationDate() {
		return resignationDate;
	}

	public void setResignationDate(Date resignationDate) {
		this.resignationDate = resignationDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public long getVersion() {
		return version;
	}

	// public void setVersion(long version) {
	// this.version = version;
	// }

	public enum Status {
		HIRED, // Contratado
		FIRED// Demitido
	}

}
