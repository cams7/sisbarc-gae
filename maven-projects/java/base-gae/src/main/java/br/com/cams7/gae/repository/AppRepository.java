/**
 * 
 */
package br.com.cams7.gae.repository;

import java.util.List;

import br.com.cams7.app.domain.AbstractEntity;
import br.com.cams7.app.repository.BaseRepository;

/**
 * @author cams7
 *
 */
public interface AppRepository<E extends AbstractEntity> extends BaseRepository<E> {
	
	/**
	 * @param id
	 *            filtro da pesquisa.
	 * @return Entidade com filtro no id
	 * @throws <code>RuntimeException</code> se algum problema ocorrer.
	 */
	public E findOne(Long id);

	/**
	 * @return Lista com todas as entidades cadastradas na base de dados.
	 * @throws <code>RuntimeException</code> se algum problema ocorrer.
	 */
	public List<E> findAll();

	/**
	 * Exclui o registro da Entidade na base de dados
	 * 
	 * @param Entidade
	 * @return Entidade removida
	 * @throws <code>RuntimeException</code> se algum problema ocorrer.
	 */
	public void delete(E entity);

	/**
	 * Faz a inserção ou atualização da Entidade na base de dados.
	 * 
	 * @param Entidade
	 * @throws <code>RuntimeException</code> se algum problema ocorrer.
	 */
	public E insert(E entity);

	public E save(E entity);

}
