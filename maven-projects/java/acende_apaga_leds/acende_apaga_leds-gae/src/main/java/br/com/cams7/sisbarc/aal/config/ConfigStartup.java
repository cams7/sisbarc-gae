package br.com.cams7.sisbarc.aal.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.cams7.app.domain.entity.UserEntity;
import br.com.cams7.sisbarc.aal.domain.entity.LEDEntity;
import br.com.cams7.sisbarc.aal.domain.entity.MercadoriaEntity;
import br.com.cams7.sisbarc.aal.domain.entity.PotenciometroEntity;

import com.googlecode.objectify.ObjectifyService;

/**
 * Componente necessário para registrar no Objectify quais são as entidades que
 * ele deve gerenciar.
 * 
 * <p>
 * Código executado durante a inicialização do aplicativo web.
 * </p>
 * 
 * @author YaW Tecnologia
 */
public class ConfigStartup implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ObjectifyService.register(MercadoriaEntity.class);
		ObjectifyService.register(UserEntity.class);
		ObjectifyService.register(LEDEntity.class);
		ObjectifyService.register(PotenciometroEntity.class);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}

}
