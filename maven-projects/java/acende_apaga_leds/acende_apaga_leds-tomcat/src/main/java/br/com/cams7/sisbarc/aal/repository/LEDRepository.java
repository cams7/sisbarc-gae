/**
 * 
 */
package br.com.cams7.sisbarc.aal.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cams7.sisbarc.aal.domain.PinoKey;
import br.com.cams7.sisbarc.aal.domain.entity.LEDEntity;
import br.com.cams7.webapp.AppRepository;

/**
 * @author cams7
 *
 */
@Repository
public interface LEDRepository extends AppRepository<LEDEntity> {

	@Query("{ 'ativadoPorBotao' : true }")
	public List<LEDEntity> buscaLEDsAtivadoPorBotao();

	@Query("{ 'pino' : ?0}")
	public LEDEntity findOne(PinoKey id);

	@Query(value = "{ 'pino' : ?0 }", fields = "{ 'ativo' : 1}")
	public LEDEntity estaAtivo(PinoKey id);
}
