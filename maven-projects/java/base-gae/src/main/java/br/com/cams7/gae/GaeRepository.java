/**
 * 
 */
package br.com.cams7.gae;

import br.com.cams7.app.BaseRepository;
import br.com.cams7.domain.BaseEntity;

/**
 * @author cams7
 *
 */
public interface GaeRepository<E extends BaseEntity> extends BaseRepository<E> {

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
