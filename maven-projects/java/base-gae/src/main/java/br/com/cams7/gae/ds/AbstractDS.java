/**
 * 
 */
package br.com.cams7.gae.ds;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.cams7.app.AbstractBase;
import br.com.cams7.app.domain.AbstractEntity;
import br.com.cams7.gae.repository.AppRepository;

/**
 * Define um <code>DataSource</code> para entidade (<code>Model</code>)
 * 
 * <p>
 * As operações de manipulação de dados no App Engine ocorrem de forma
 * assíncrona. Antes de efetivar a mudança em todas as instâncias uma consulta
 * pode trazer dados "sujos" (eventually consistent), a proposta do datasource é
 * manter uma replica dos dados na sessão web, para refletir as mudanças
 * recentes do usuário.
 * </p>
 * 
 * <p>
 * Mantém as entidades indexadas pelo <code>id</code> (<code>Long</code>) em um
 * <code>HashMap</code>.
 * </p>
 * 
 * @author cams7
 *
 */
public abstract class AbstractDS<E extends AbstractEntity> extends
		AbstractBase<E> implements AppRepository<E>, Serializable {

	private static final long serialVersionUID = 1L;

	private Map<Long, E> data;

	public AbstractDS() {
		super();
		data = new LinkedHashMap<Long, E>();
	}

	@Override
	public E insert(E entity) {
		return save(entity);
	}

	@Override
	public E save(E entity) {
		if (entity != null)
			data.put(entity.getId(), entity);

		updateSession();

		return entity;
	}

	@Override
	public void save(List<E> entities) {
		if (entities != null)
			for (E entity : entities)
				data.put(entity.getId(), entity);

		updateSession();
	}

	@Override
	public void delete(E entity) {
		if (entity != null)
			data.remove(entity.getId());

		updateSession();
	}

	@Override
	public E findOne(Long id) {
		E entity = data.get(id);
		return entity;
	}

	@Override
	public List<E> findAll() {
		List<E> entities = new ArrayList<E>(data.values());
		return entities;
	}

	public void synch(List<E> entities) {
		getLog().log(Level.INFO, "Sincronizando datasource...");
		data.clear();

		if (entities == null)
			return;

		for (E entity : entities)
			if (entity != null)
				data.put(entity.getId(), entity);

		updateSession();
	}

	public boolean isEmpty() {
		boolean isEmpty = data.isEmpty();
		return isEmpty;
	}

	/**
	 * Pede ao App Engine para atualizar a sessão.
	 */
	private void updateSession() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		session.setAttribute("forceGaeSessionSerialization",
				System.currentTimeMillis());
	}

}
