/**
 * 
 */
package br.com.cams7.sisbarc.aal.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.ws.BindingProvider;
//import javax.xml.ws.WebServiceRef;
import javax.xml.ws.handler.MessageContext;

import org.springframework.stereotype.Service;

import br.com.cams7.sisbarc.aal.service.GreeterService;
import br.com.cams7.sisbarc.aal.ws.HelloServiceImplService;
import br.com.cams7.teste.ws.HelloService;

/**
 * @author cams7
 *
 */
@Service
public class GreeterServiceImpl implements GreeterService {

	private final String HOST = "localhost";
	private final String PORT = "8080";

	// @WebServiceRef(value = HelloServiceImplService.class)
	// private HelloService port;

	public GreeterServiceImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.sisbarc.aal.service.GreeterService#sayHello(java.lang.String
	 * )
	 */
	@Override
	public String sayHello(String name) {
		// String message = getPort().printMessage(name);
		String message = "Ola " + name + ", seja bem vindo...";
		return message;
	}

	private HelloService getPort() {
		HelloService service = (new HelloServiceImplService())
				.getHelloServiceImplPort();

		Map<String, Object> context = ((BindingProvider) service)
				.getRequestContext();

		final String WSDL_LOCATION = HOST + ":" + PORT
				+ "/acende_apaga_leds/sayhello?wsdl";

		context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, WSDL_LOCATION);

		Map<String, List<String>> headers = new HashMap<String, List<String>>();
		headers.put("username", Collections.singletonList("ceanma@gmail.com"));
		headers.put("password", Collections.singletonList("teste@12345"));
		context.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

		return service;
	}

}
