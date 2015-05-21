/**
 * 
 */
package br.com.cams7.sisbarc.aal.service.impl;

import org.springframework.stereotype.Service;

import br.com.cams7.sisbarc.aal.domain.entity.LEDEntity;
import br.com.cams7.sisbarc.aal.repository.LEDRepository;
import br.com.cams7.sisbarc.aal.service.LEDService;
import br.com.cams7.webapp.AppServiceImpl;

/**
 * @author cams7
 *
 */
@Service
public class LEDServiceImpl extends
		AppServiceImpl<LEDRepository, LEDEntity, String> implements LEDService {

	/**
	 * 
	 */
	public LEDServiceImpl() {
		super();
	}

}
