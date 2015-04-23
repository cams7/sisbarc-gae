package br.com.cams7.sisbarc.aal.service.impl;

import org.springframework.stereotype.Service;

import br.com.cams7.gae.service.BaseServiceImpl;
import br.com.cams7.sisbarc.aal.ds.MercadoriaDataSource;
import br.com.cams7.sisbarc.aal.repository.MercadoriaRepository;
import br.com.cams7.sisbarc.aal.service.MercadoriaService;
import br.com.yaw.spgae.model.Mercadoria;

@Service
public class MercadoriaServiceImpl
		extends
		BaseServiceImpl<MercadoriaRepository, MercadoriaDataSource, Mercadoria, Long>
		implements MercadoriaService {

	public MercadoriaServiceImpl() {
		super();
	}

}
