/**
 * 
 */
package br.com.cams7.sisbarc.aal.service;

import br.com.cams7.gae.service.AppService;
import br.com.cams7.sisbarc.aal.domain.entity.MercadoriaEntity;
import br.com.cams7.sisbarc.aal.repository.MercadoriaRepository;

/**
 * @author cams7
 *
 */
public interface MercadoriaService extends AppService<MercadoriaEntity>,
		MercadoriaRepository {

}
