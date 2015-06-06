/**
 * 
 */
package br.com.cams7.sisbarc.aal.repository;

import br.com.cams7.gae.repository.AppRepository;
import br.com.cams7.sisbarc.aal.domain.entity.LEDEntity;

/**
 * Contrato de persistencia para a entidade <code>LED</code>.
 * 
 * <p>
 * Define as operacoes basicas de cadastro (CRUD), seguindo o design pattern
 * <code>Data Access Object</code>.
 * </p>
 * 
 * @author cams7
 *
 */
public interface LEDRepository extends AppRepository<LEDEntity> {

}
