/**
 * 
 */
package br.com.cams7.sisbarc.aal.service.impl;

import org.springframework.stereotype.Service;

import br.com.cams7.sisbarc.aal.domain.entity.PotenciometroEntity;
import br.com.cams7.sisbarc.aal.repository.PotenciometroRepository;
import br.com.cams7.sisbarc.aal.service.PotenciometroService;
import br.com.cams7.webapp.AppServiceImpl;

/**
 * @author cams7
 *
 */
@Service
public class PotenciometroServiceImpl extends
		AppServiceImpl<PotenciometroRepository, PotenciometroEntity, String>
		implements PotenciometroService {

	/**
	 * 
	 */
	public PotenciometroServiceImpl() {
		super();
	}

}
