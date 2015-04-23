package br.com.cams7.sisbarc.aal.repository;

import br.com.cams7.gae.GaeRepository;
import br.com.cams7.sisbarc.aal.domain.MercadoriaEntity;

/**
 * Contrato de persistência para a entidade <code>Mercadoria</code>.
 * 
 * <p>
 * Define as operações basicas de cadastro (CRUD), seguindo o design pattern
 * <code>Data Access Object</code>.
 * </p>
 * 
 * @author YaW Tecnologia
 */
public interface MercadoriaRepository extends GaeRepository<MercadoriaEntity, Long> {

}
