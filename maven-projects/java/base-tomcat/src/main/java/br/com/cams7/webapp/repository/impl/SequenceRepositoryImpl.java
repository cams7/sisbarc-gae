/**
 * 
 */
package br.com.cams7.webapp.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import br.com.cams7.webapp.domain.entity.SequenceId;
import br.com.cams7.webapp.repository.SequenceRepository;

/**
 * @author cams7
 *
 */
@Repository
public class SequenceRepositoryImpl implements SequenceRepository {

	@Autowired
	private MongoOperations mongoOperation;

	/**
	 * 
	 */
	public SequenceRepositoryImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.webapp.sequence.SequenceRepository#getNextSequenceId(java
	 * .lang.String)
	 */
	@Override
	public long getNextId(String className) {
		// get sequence id
		Query query = new Query(Criteria.where("_id").is(className));

		// increase sequence id by 1
		Update update = new Update();
		update.inc("sequence", 1);

		// return new increased id
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);

		// this is the magic happened.
		SequenceId sequenceId = mongoOperation.findAndModify(query, update,
				options, SequenceId.class);

		// if no id, throws SequenceException
		// optional, just a way to tell user when the sequence id is failed to
		// generate.
		if (sequenceId == null)
			return 0;

		return sequenceId.getSequence();
	}

}
