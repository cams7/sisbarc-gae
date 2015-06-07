/**
 * 
 */
package br.com.cams7.gae.repository.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import br.com.cams7.app.domain.entity.UserEntity;
import br.com.cams7.app.domain.entity.UserEntity.Role;
import br.com.cams7.gae.repository.AbstractAppRepository;
import br.com.cams7.gae.repository.UserRepository;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

/**
 * @author cams7
 *
 */
@Repository
public class UserRepositoryImpl extends AbstractAppRepository<UserEntity>
		implements UserRepository {
	// private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 
	 */
	public UserRepositoryImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.app.repository.BaseUsuarioRepository#findByUsername(java
	 * .lang.String)
	 */
	@Override
	public UserEntity findByEmail(String email) {
		// Get the Datastore Service
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Filter emailFilter = new FilterPredicate("email", FilterOperator.EQUAL,
				email);

		// Use class Query to assemble a query
		Query query = new Query(UserEntity.class.getSimpleName())
				.setFilter(emailFilter);

		// Use PreparedQuery interface to retrieve results
		PreparedQuery pq = datastore.prepare(query);

		Entity entity = pq.asSingleEntity();

		if (entity == null)
			return null;

		@SuppressWarnings("unchecked")
		Collection<String> roles = (Collection<String>) entity
				.getProperty("authorities");

		Set<Role> authorities = new HashSet<Role>();
		for (String role : roles)
			authorities.add((Role) Enum.valueOf(Role.class, role));

		UserEntity user = new UserEntity();
		user.setId(entity.getKey().getId());
		user.setUsername((String) entity.getProperty("username"));
		user.setEmail((String) entity.getProperty("email"));
		user.setEnabled((Boolean) entity.getProperty("enabled"));
		user.setAuthorities(authorities);
		user.setIp((String) entity.getProperty("ip"));
		user.setPort((Short) entity.getProperty("port"));

		return user;

	}

}
