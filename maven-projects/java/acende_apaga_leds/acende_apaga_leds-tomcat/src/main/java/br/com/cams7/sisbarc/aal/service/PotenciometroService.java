/**
 * 
 */
package br.com.cams7.sisbarc.aal.service;

import br.com.cams7.sisbarc.aal.domain.entity.PotenciometroEntity;
import br.com.cams7.sisbarc.aal.repository.PotenciometroRepository;
import br.com.cams7.webapp.AppService;

/**
 * @author cams7
 *
 */
public interface PotenciometroService extends
		AppService<PotenciometroEntity, String>, PotenciometroRepository {

}
