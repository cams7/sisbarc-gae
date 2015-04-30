package br.com.cams7.app;

import java.io.Serializable;
import java.util.List;

import br.com.cams7.domain.BaseEntity;

public interface BaseRepository<E extends BaseEntity<ID>, ID extends Serializable> {

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
