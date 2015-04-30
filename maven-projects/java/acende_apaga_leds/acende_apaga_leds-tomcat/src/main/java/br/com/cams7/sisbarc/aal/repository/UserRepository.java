package br.com.cams7.sisbarc.aal.repository;

import org.springframework.stereotype.Repository;

import br.com.cams7.sisbarc.aal.domain.UserEntity;
import br.com.cams7.webapp.TomcatRepository;

@Repository
public interface UserRepository extends TomcatRepository<UserEntity, String> {

}
