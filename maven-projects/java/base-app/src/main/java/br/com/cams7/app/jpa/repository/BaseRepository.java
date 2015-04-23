package br.com.cams7.app.jpa.repository;

import java.io.Serializable;
import java.util.List;

import br.com.cams7.jpa.domain.BaseEntity;

public interface BaseRepository<E extends BaseEntity<ID>, ID extends Serializable> {

	/**
	 * Faz a inserção ou atualização da Entidade na base de dados.
	 * 
	 * @param Entidade
	 * @throws <code>RuntimeException</code> se algum problema ocorrer.
	 */
	public void save(E entity);

	public E update(E entity);

	// public void update(List<E> entities);

	/**
	 * Exclui o registro da Entidade na base de dados
	 * 
	 * @param Entidade
	 * @return Entidade removida
	 * @throws <code>RuntimeException</code> se algum problema ocorrer.
	 */
	public E remove(E entity);

	// public E remove(ID id);

	/**
	 * @param id
	 *            filtro da pesquisa.
	 * @return Entidade com filtro no id
	 * @throws <code>RuntimeException</code> se algum problema ocorrer.
	 */
	public E findOne(ID id);

	/**
	 * @return Lista com todas as entidades cadastradas na base de dados.
	 * @throws <code>RuntimeException</code> se algum problema ocorrer.
	 */
	public List<E> findAll();

	// public List<E> findRange(int[] range);

	// public long count();

}
