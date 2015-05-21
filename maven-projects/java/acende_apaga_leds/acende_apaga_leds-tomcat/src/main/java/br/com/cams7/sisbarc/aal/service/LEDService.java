/**
 * 
 */
package br.com.cams7.sisbarc.aal.service;

import br.com.cams7.sisbarc.aal.domain.entity.LEDEntity;
import br.com.cams7.sisbarc.aal.repository.LEDRepository;
import br.com.cams7.webapp.AppService;

/**
 * @author cams7
 *
 */
public interface LEDService extends AppService<LEDEntity, String>,
		LEDRepository {

}
