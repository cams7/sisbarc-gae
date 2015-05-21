/**
 * 
 */
package br.com.cams7.sisbarc.aal.repository;

import org.springframework.stereotype.Repository;

import br.com.cams7.sisbarc.aal.domain.entity.LEDEntity;
import br.com.cams7.webapp.AppRepository;

/**
 * @author cams7
 *
 */
@Repository
public interface LEDRepository extends AppRepository<LEDEntity, String> {

}
