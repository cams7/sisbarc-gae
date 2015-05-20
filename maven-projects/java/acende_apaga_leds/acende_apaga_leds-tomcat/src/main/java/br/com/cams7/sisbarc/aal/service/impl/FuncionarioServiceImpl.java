package br.com.cams7.sisbarc.aal.service.impl;

import org.springframework.stereotype.Service;

import br.com.cams7.sisbarc.aal.repository.FuncionarioRepository;
import br.com.cams7.sisbarc.aal.service.FuncionarioService;
import br.com.cams7.webapp.AppServiceImpl;
import br.com.cams7.sisbarc.aal.domain.entity.FuncionarioEntity;

@Service
public class FuncionarioServiceImpl extends
		AppServiceImpl<FuncionarioRepository, FuncionarioEntity, String>
		implements FuncionarioService {

	public FuncionarioServiceImpl() {
		super();
	}

}
