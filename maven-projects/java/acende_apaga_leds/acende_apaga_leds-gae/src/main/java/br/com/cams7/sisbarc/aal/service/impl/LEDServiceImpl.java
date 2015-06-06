/**
 * 
 */
package br.com.cams7.sisbarc.aal.service.impl;

import org.springframework.stereotype.Service;

import br.com.cams7.gae.service.AbstractAppService;
import br.com.cams7.sisbarc.aal.domain.entity.LEDEntity;
import br.com.cams7.sisbarc.aal.ds.LEDDS;
import br.com.cams7.sisbarc.aal.repository.LEDRepository;
import br.com.cams7.sisbarc.aal.service.LEDService;

/**
 * @author cams7
 *
 */
@Service
public class LEDServiceImpl extends
		AbstractAppService<LEDRepository, LEDDS, LEDEntity> implements
		LEDService {

	public LEDServiceImpl() {
		super();
	}

}
