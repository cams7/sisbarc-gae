package br.com.cams7.gae;

import java.util.List;

import br.com.cams7.domain.BaseEntity;

/**
 * Define uma estrutura para armazenar e fornecer dados para um
 * <code>View</code>, mantido na camada server.
 * 
 * @author YaW Tecnologia
 *
 * @param <T>
 *            Tipo da informação mantida no <code>DataSource</code>, normalmente
 *            um <code>Model</code>.
 */
public interface DataSource<E extends BaseEntity> extends GaeRepository<E> {

	/**
	 * Adiciona elemento ao <code>DataSource</code>.
	 * 
	 * @param t
	 */
	// void add(T t);

	/**
	 * Atualiza elemento ao <code>DataSource</code>.
	 * 
	 * @param t
	 */
	// void update(T t);

	/**
	 * Remove elemento ao <code>DataSource</code>.
	 * 
	 * @param t
	 */
	// void remove(T t);

	/**
	 * Sincroniza todos os elementos do <code>DataSource</code> com as
	 * instâncias da coleção.
	 * 
	 * @param entities
	 */
	void synch(List<E> entities);

	/**
	 * @return <code>List</code> com todos os elementos contidos no
	 *         <code>DataSource</code>.
	 */
	// List<T> getAll();

	boolean isEmpty();

}
