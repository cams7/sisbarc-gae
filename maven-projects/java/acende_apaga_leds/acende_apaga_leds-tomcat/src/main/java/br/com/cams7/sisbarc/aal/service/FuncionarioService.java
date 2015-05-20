package br.com.cams7.sisbarc.aal.service;

import br.com.cams7.sisbarc.aal.domain.FuncionarioEntity;
import br.com.cams7.sisbarc.aal.repository.FuncionarioRepository;
import br.com.cams7.webapp.AppService;

public interface FuncionarioService extends
		AppService<FuncionarioEntity, String>, FuncionarioRepository {

}
