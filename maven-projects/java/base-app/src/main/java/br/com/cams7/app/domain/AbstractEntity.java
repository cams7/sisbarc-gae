/**
 * 
 */
package br.com.cams7.app.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * @author cams7
 *
 * @param <PK>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public abstract class AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public AbstractEntity() {
		super();
	}

	/**
	 * @param id
	 */
	public AbstractEntity(Long id) {
		this();

		setId(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Entity of type %s with id: %s", this.getClass()
				.getName(), getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object entity) {

		if (null == entity) {
			return false;
		}

		if (this == entity) {
			return true;
		}

		if (!getClass().equals(entity.getClass())) {
			return false;
		}

		AbstractEntity e = (AbstractEntity) entity;

		return null == this.getId() ? false : this.getId().equals(e.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		int hashCode = 17;

		hashCode += null == getId() ? 0 : getId().hashCode() * 31;

		return hashCode;
	}

	@XmlTransient
	// @XmlJavaTypeAdapter(SerializableAdapter.class)
	public abstract Long getId();

	public abstract void setId(Long id);

}
