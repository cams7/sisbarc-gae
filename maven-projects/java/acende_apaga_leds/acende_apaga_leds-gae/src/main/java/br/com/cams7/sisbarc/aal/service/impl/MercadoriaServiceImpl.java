package br.com.cams7.sisbarc.aal.service.impl;

import org.springframework.stereotype.Service;

import br.com.cams7.gae.AppServiceImpl;
import br.com.cams7.sisbarc.aal.domain.entity.MercadoriaEntity;
import br.com.cams7.sisbarc.aal.ds.MercadoriaDS;
import br.com.cams7.sisbarc.aal.repository.MercadoriaRepository;
import br.com.cams7.sisbarc.aal.service.MercadoriaService;

@Service
public class MercadoriaServiceImpl
		extends
		AppServiceImpl<MercadoriaRepository, MercadoriaDS, MercadoriaEntity>
		implements MercadoriaService {

	public MercadoriaServiceImpl() {
		super();
	}

}
