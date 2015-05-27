/**
 * 
 */
package br.com.cams7.webapp.sequence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author cams7
 *
 */
@Document(collection = "sequence")
public class SequenceId {

	@Id
	private String id;

	private long sequence;

	/**
	 * 
	 */
	public SequenceId() {
		super();
	}

	/**
	 * @param id
	 */
	public SequenceId(String id) {
		this();
		this.id = id;
	}

	@Override
	public String toString() {
		return this.getClass().getName() + "[id = " + getId() + ", sequence = "
				+ getSequence() + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getSequence() {
		return sequence;
	}

	public void setSequence(long sequence) {
		this.sequence = sequence;
	}

}
