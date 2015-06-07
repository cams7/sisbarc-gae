/**
 * 
 */
package br.com.cams7.sisbarc.aal.service.impl;

import org.springframework.stereotype.Service;

import br.com.cams7.sisbarc.aal.domain.entity.PotenciometroEntity;
import br.com.cams7.sisbarc.aal.ds.PotenciometroDS;
import br.com.cams7.sisbarc.aal.repository.PotenciometroRepository;
import br.com.cams7.sisbarc.aal.service.PotenciometroService;

/**
 * @author cams7
 *
 */
@Service
public class PotenciometroServiceImpl
		extends
		AbstractAALService<PotenciometroRepository, PotenciometroDS, PotenciometroEntity>
		implements PotenciometroService {

	public PotenciometroServiceImpl() {
		super();
	}

}
