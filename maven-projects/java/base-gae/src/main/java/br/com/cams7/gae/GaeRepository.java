/**
 * 
 */
package br.com.cams7.gae;

import java.io.Serializable;

import br.com.cams7.app.BaseRepository;
import br.com.cams7.domain.BaseEntity;

/**
 * @author cams7
 *
 */
public interface GaeRepository<E extends BaseEntity<ID>, ID extends Serializable>
		extends BaseRepository<E, ID> {

	/**
	 * Faz a inserção ou atualização da Entidade na base de dados.
	 * 
	 * @param Entidade
	 * @throws <code>RuntimeException</code> se algum problema ocorrer.
	 */
	public E insert(E entity);

	public E save(E entity);

}
