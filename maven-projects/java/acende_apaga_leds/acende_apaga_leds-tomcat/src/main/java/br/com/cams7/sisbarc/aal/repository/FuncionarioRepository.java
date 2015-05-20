package br.com.cams7.sisbarc.aal.repository;

import org.springframework.stereotype.Repository;

import br.com.cams7.sisbarc.aal.domain.FuncionarioEntity;
import br.com.cams7.webapp.AppRepository;

@Repository
public interface FuncionarioRepository extends AppRepository<FuncionarioEntity, String> {

}
