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
import br.com.cams7.sisbarc.aal.ws.HelloService;
import br.com.cams7.sisbarc.aal.ws.HelloServiceImpl;

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
		HelloService port = getPort();
		Map<String, Object> context = ((BindingProvider) port)
				.getRequestContext();

		String appPort = PORT;

		switch (name.toLowerCase()) {
		case "leandro":
			appPort = "8383";
			break;
		case "vbox":
			appPort = "8081";
			break;
		default:
			break;
		}

		String path = "http://" + HOST + ":" + appPort
				+ "/acende_apaga_leds/sayhello";

		context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, path);

		Map<String, List<String>> headers = new HashMap<String, List<String>>();
		headers.put("username", Collections.singletonList("ceanma@gmail.com"));
		headers.put("password", Collections.singletonList("teste@12345"));
		context.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

		String message = port.printMessage(name);
		return message;
	}

	public HelloService getPort() {
		HelloService port = (new HelloServiceImpl()).getHelloServiceImplPort();

		// Map<String, Object> context = ((BindingProvider) port)
		// .getRequestContext();
		//
		// final String WSDL_LOCATION =
		// "http://localhost:8080/acende_apaga_leds/sayhello";

		// context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
		// WSDL_LOCATION);

		return port;
	}

}
