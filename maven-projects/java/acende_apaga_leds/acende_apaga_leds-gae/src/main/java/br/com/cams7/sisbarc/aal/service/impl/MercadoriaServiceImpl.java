package br.com.cams7.sisbarc.aal.service.impl;

import org.springframework.stereotype.Service;

import br.com.cams7.gae.GaeServiceImpl;
import br.com.cams7.sisbarc.aal.domain.entity.MercadoriaEntity;
import br.com.cams7.sisbarc.aal.ds.MercadoriaDataSource;
import br.com.cams7.sisbarc.aal.repository.MercadoriaRepository;
import br.com.cams7.sisbarc.aal.service.MercadoriaService;

@Service
public class MercadoriaServiceImpl
		extends
		GaeServiceImpl<MercadoriaRepository, MercadoriaDataSource, MercadoriaEntity>
		implements MercadoriaService {

	public MercadoriaServiceImpl() {
		super();
	}

}
