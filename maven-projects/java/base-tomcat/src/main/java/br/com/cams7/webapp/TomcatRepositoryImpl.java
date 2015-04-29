package br.com.cams7.webapp;

import java.io.Serializable;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.cams7.app.AbstractBase;
import br.com.cams7.domain.BaseEntity;
import br.com.cams7.util.AppUtil;
import br.com.cams7.webapp.domain.SortOrderField;
//import java.util.Calendar;
//import java.util.Date;

public abstract class TomcatRepositoryImpl<E extends BaseEntity<ID>, ID extends Serializable>
		extends AbstractBase<E> implements TomcatRepository<E, ID> {

	public TomcatRepositoryImpl() {
		super();
	}

	@Override
	public void save(E entity) {
		getEntityManager().persist(entity);
	}

	@Override
	public E update(E entity) {
		return getEntityManager().merge(entity);
	}

	// public void update(List<E> entities) {
	// for (E entity : entities)
	// update(entity);
	// }

	@Override
	public E remove(E entity) {
		entity = update(entity);
		getEntityManager().remove(entity);
		return entity;
	}

	@Override
	public E remove(ID id) {
		E entity = findOne(id);
		getEntityManager().remove(entity);
		return entity;
	}

	@Override
	public E findOne(ID id) {
		E entity = getEntityManager().find(getEntityType(), id);
		return entity;
	}

	@Override
	public List<E> findAll() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<E> cq = cb.createQuery(getEntityType());

		Root<E> from = cq.from(getEntityType());
		cq.select(from);

		TypedQuery<E> query = getEntityManager().createQuery(cq);

		List<E> entities = query.getResultList();
		return entities;
	}

	// public List<E> findRange(int[] range) {
	// CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
	// CriteriaQuery<E> cq = cb.createQuery(getEntityType());
	//
	// Root<E> from = cq.from(getEntityType());
	// cq.select(from);
	//
	// TypedQuery<E> query = getEntityManager().createQuery(cq);
	// query.setMaxResults(range[1] - range[0]);
	// query.setFirstResult(range[0]);
	//
	// List<E> entities = query.getResultList();
	// return entities;
	// }

	private Path<?> getPath(Root<E> from, String field) {
		Path<?> path = from.get(field);
		return path;
	}

	private void addOrderBy(CriteriaBuilder cb, CriteriaQuery<?> cq,
			Root<E> from, String sortField, SortOrderField sortOrder) {
		// Sorting
		if (sortField != null && !sortField.isEmpty()) {
			Path<?> path = getPath(from, sortField);

			if (path != null)
				switch (sortOrder) {
				case ASCENDING:
					cq.orderBy(cb.asc(path));
					break;
				case DESCENDING:
					cq.orderBy(cb.desc(path));
					break;
				default:
					break;
				}
		}
	}

	// private Predicate getEqual(CriteriaBuilder cb, Expression<Date>
	// expression,
	// String functionName, Short value) {
	// if (value == null)
	// return null;
	//
	// Expression<Short> function = cb.function(functionName, Short.class,
	// expression);
	// return cb.equal(function, value);
	// }

	@SuppressWarnings("unchecked")
	private void addWhere(CriteriaBuilder cb, CriteriaQuery<?> cq,
			Root<E> from, Map<String, Object> filters) {
		// Filtering
		if (filters == null || filters.isEmpty())
			return;

		List<Predicate> and = new ArrayList<Predicate>();
		for (Map.Entry<String, Object> filter : filters.entrySet()) {
			Object value = filter.getValue();

			if (value == null)
				continue;

			Object[] values = null;

			if (value instanceof Object[]) {
				if (((Object[]) value).length > 0)
					values = (Object[]) value;
			} else if (!(value instanceof String)
					|| !((String) value).isEmpty()) {
				values = new Object[1];
				values[0] = value;
			}

			if (values != null) {
				Path<?> path = getPath(from, filter.getKey());

				if (path != null) {
					Class<?> type = path.getJavaType();

					final String WILD_CARD = "%";

					Predicate predicate = null;

					if (AppUtil.isBoolean(type)) {
						List<Predicate> or = new ArrayList<Predicate>();
						for (Object objectValue : values) {
							Boolean booleanValue = (Boolean) objectValue;

							Expression<Boolean> expression = (Expression<Boolean>) path;
							or.add((booleanValue == Boolean.TRUE) ? cb
									.isTrue(expression) : cb
									.isFalse(expression));

						}
						if (!or.isEmpty())
							predicate = cb.or(or.toArray(new Predicate[0]));
						// TODO Corrigir ERRO
						// else
						// predicate = cb.isNull(path);
					} else if (AppUtil.isEnum(type)) {
						List<Predicate> or = new ArrayList<Predicate>();
						for (Object objectValue : values)
							or.add(cb.equal(path, objectValue));

						if (!or.isEmpty())
							predicate = cb.or(or.toArray(new Predicate[0]));
						// TODO Corrigir ERRO
						// else
						// predicate = cb.isNull(path);

						// TODO Corrigir ERRO
						// } else if (AppUtil.isDate(type)) {
						// List<Predicate> or = new ArrayList<Predicate>();
						// for (Object objectValue : values) {
						// String stringValue = (String) objectValue;
						// Map<Integer, Short>[] between = AppUtil
						// .getDate(stringValue);
						// if (between != null) {
						// Expression<Date> expression = (Expression<Date>)
						// path;
						//
						// if (between.length == 2) {
						// Calendar[] dates = AppUtil.getDate(between);
						// Date date1 = dates[0].getTime();
						// Date date2 = dates[1].getTime();
						//
						// or.add(cb.between(expression, date1, date2));
						//
						// final DateFormat DF = new SimpleDateFormat(
						// "dd/MM/yyyy HH:mm:ss");
						// getLog().info(
						// "from: " + DF.format(date1)
						// + ", to: "
						// + DF.format(date2));
						// } else {
						// List<Predicate> andDateValue = new
						// ArrayList<Predicate>();
						//
						// Predicate equal = getEqual(cb, expression,
						// "year",
						// between[0].get(Calendar.YEAR));
						//
						// if (equal != null)
						// andDateValue.add(equal);
						//
						// equal = getEqual(cb, expression, "month",
						// between[0].get(Calendar.MONTH));
						//
						// if (equal != null)
						// andDateValue.add(equal);
						//
						// equal = getEqual(cb, expression, "day",
						// between[0]
						// .get(Calendar.DAY_OF_MONTH));
						//
						// if (equal != null)
						// andDateValue.add(equal);
						//
						// equal = getEqual(cb, expression, "hour",
						// between[0]
						// .get(Calendar.HOUR_OF_DAY));
						//
						// if (equal != null)
						// andDateValue.add(equal);
						//
						// equal = getEqual(cb, expression, "minute",
						// between[0].get(Calendar.MINUTE));
						//
						// if (equal != null)
						// andDateValue.add(equal);
						//
						// equal = getEqual(cb, expression, "second",
						// between[0].get(Calendar.SECOND));
						//
						// if (equal != null)
						// andDateValue.add(equal);
						//
						// or.add(cb.and(andDateValue
						// .toArray(new Predicate[0])));
						// }
						// }
						// }
						// if (!or.isEmpty())
						// predicate = cb.or(or.toArray(new Predicate[0]));

						// TODO Corrigir ERRO
						// else
						// predicate = cb.isNull(path);
					} else {
						List<Predicate> or = new ArrayList<Predicate>();
						for (Object objectValue : values) {

							Expression<?> expression = (Expression<?>) path;

							Expression<String> stringExpression;

							String stringValue;
							if (type.equals(String.class)) {
								stringValue = ((String) objectValue)
								/* .toLowerCase() */+ WILD_CARD;

								stringExpression = (Expression<String>) expression;

								or.add(cb
										.like(/* cb.lower( */stringExpression/* ) */,
												stringValue));
							} else if (AppUtil.isNumber(type)) {
								stringValue = ((Number) objectValue)
										+ WILD_CARD;

								stringExpression = (Expression<String>) expression
										.as(String.class);

								or.add(cb.like(stringExpression, stringValue));
							}

						}
						if (!or.isEmpty())
							predicate = cb.or(or.toArray(new Predicate[0]));
						// TODO Corrigir ERRO
						// else
						// predicate = cb.isNull(path);

					}

					if (predicate != null)
						and.add(predicate);

				}

			}
		}

		cq.where(and.toArray(new Predicate[0]));
	}

	private List<E> search(boolean findAll, short first, byte pageSize,
			String sortField, SortOrderField sortOrder,
			Map<String, Object> filters) {

		getLog().info(
				"search(" + first + ", " + pageSize + ", " + sortField + ", "
						+ sortOrder + ", " + filters + ")\n");

		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<E> cq = cb.createQuery(getEntityType());

		Root<E> from = cq.from(getEntityType());
		CriteriaQuery<E> select = cq.select(from);

		// Sorting
		if (!findAll)
			addOrderBy(cb, cq, from, sortField, sortOrder);

		// Filtering
		addWhere(cb, cq, from, filters);

		TypedQuery<E> query = getEntityManager().createQuery(select);

		if (!findAll) {
			query.setFirstResult(first);
			query.setMaxResults(pageSize);
		}

		List<E> entities = query.getResultList();
		return entities;
	}

	public List<E> search(short first, byte pageSize, String sortField,
			SortOrderField sortOrder, Map<String, Object> filters) {
		return search(false, first, pageSize, sortField, sortOrder, filters);
	}

	public long count(Map<String, Object> filters) {

		// CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		// CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		//
		// Root<E> from = cq.from(getEntityType());
		// cq.select(cb.count(from));

		// Filtering
		// addWhere(cb, cq, from, filters);

		// TypedQuery<Long> query = getEntityManager().createQuery(cq);
		//
		// long count = (Long)query.getSingleResult();

		// TODO Altera a forma de contar os registros
		List<E> list = search(true, (short) 0, (byte) 0, null, null, filters);
		long count = list.size();

		return count;
	}

	public long count() {
		long count = count(null);
		return count;
	}

	/**
	 * Exige a definição do <code>EntityManager</code> responsável pelas
	 * operações de persistência.
	 */
	protected abstract EntityManager getEntityManager();

}
