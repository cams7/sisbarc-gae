/**
 * 
 */
package br.com.cams7.sisbarc.aal.repository;

import java.util.List;

import com.googlecode.objectify.Key;

import br.com.cams7.app.domain.entity.UserEntity;
import br.com.cams7.sisbarc.aal.domain.PinoKey;
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
public interface LEDRepository extends AALRepository<LEDEntity> {
	public List<LEDEntity> buscaLEDsAtivadoPorBotao(Key<UserEntity> user);

	public LEDEntity findOne(Key<UserEntity> user, PinoKey key);
}
