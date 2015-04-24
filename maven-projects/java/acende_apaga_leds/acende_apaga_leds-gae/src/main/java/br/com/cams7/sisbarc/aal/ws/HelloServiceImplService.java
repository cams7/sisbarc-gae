package br.com.cams7.sisbarc.aal.ws;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

import br.com.cams7.teste.ws.HelloService;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.2.8 Generated source
 * version: 2.2
 * 
 */
@WebServiceClient(name = HelloServiceImplService.WEBSERVICECLIENT_NAME, targetNamespace = HelloService.WEBSERVICE_TARGETNAMESPACE, wsdlLocation = HelloServiceImplService.WEBSERVICECLIENT_WSDLLOCATION)
public class HelloServiceImplService extends Service {

	public static final String WEBSERVICEIMPL_NAME = HelloService.WEBSERVICE_NAME
			+ "Impl";

	public static final String WEBSERVICECLIENT_NAME = WEBSERVICEIMPL_NAME
			+ "Service";
	public static final String WEBSERVICECLIENT_WSDLLOCATION = "http://localhost:8080/acende_apaga_leds/sayhello?wsdl";

	private final static URL HELLOSERVICEIMPLSERVICE_WSDL_LOCATION;
	private final static WebServiceException HELLOSERVICEIMPLSERVICE_EXCEPTION;
	private final static QName HELLOSERVICEIMPLSERVICE_QNAME = new QName(
			HelloService.WEBSERVICE_TARGETNAMESPACE,
			HelloServiceImplService.WEBSERVICECLIENT_NAME);

	static {
		URL url = null;
		WebServiceException e = null;
		try {
			url = new URL(WEBSERVICECLIENT_WSDLLOCATION);
		} catch (MalformedURLException ex) {
			e = new WebServiceException(ex);
		}
		HELLOSERVICEIMPLSERVICE_WSDL_LOCATION = url;
		HELLOSERVICEIMPLSERVICE_EXCEPTION = e;
	}

	public HelloServiceImplService() {
		super(__getWsdlLocation(), HELLOSERVICEIMPLSERVICE_QNAME);
	}

	public HelloServiceImplService(WebServiceFeature... features) {
		super(__getWsdlLocation(), HELLOSERVICEIMPLSERVICE_QNAME, features);
	}

	public HelloServiceImplService(URL wsdlLocation) {
		super(wsdlLocation, HELLOSERVICEIMPLSERVICE_QNAME);
	}

	public HelloServiceImplService(URL wsdlLocation,
			WebServiceFeature... features) {
		super(wsdlLocation, HELLOSERVICEIMPLSERVICE_QNAME, features);
	}

	public HelloServiceImplService(URL wsdlLocation, QName serviceName) {
		super(wsdlLocation, serviceName);
	}

	public HelloServiceImplService(URL wsdlLocation, QName serviceName,
			WebServiceFeature... features) {
		super(wsdlLocation, serviceName, features);
	}

	/**
	 * 
	 * @return returns HelloService
	 */
	@WebEndpoint(name = WEBSERVICEIMPL_NAME + "Port")
	public HelloService getHelloServiceImplPort() {
		return super.getPort(new QName(HelloService.WEBSERVICE_TARGETNAMESPACE,
				WEBSERVICEIMPL_NAME + "Port"), HelloService.class);
	}

	/**
	 * 
	 * @param features
	 *            A list of {@link javax.xml.ws.WebServiceFeature} to configure
	 *            on the proxy. Supported features not in the
	 *            <code>features</code> parameter will have their default
	 *            values.
	 * @return returns HelloService
	 */
	@WebEndpoint(name = WEBSERVICEIMPL_NAME + "Port")
	public HelloService getHelloServiceImplPort(WebServiceFeature... features) {
		return super.getPort(new QName(HelloService.WEBSERVICE_TARGETNAMESPACE,
				WEBSERVICEIMPL_NAME + "Port"), HelloService.class, features);
	}

	private static URL __getWsdlLocation() {
		if (HELLOSERVICEIMPLSERVICE_EXCEPTION != null) {
			throw HELLOSERVICEIMPLSERVICE_EXCEPTION;
		}
		return HELLOSERVICEIMPLSERVICE_WSDL_LOCATION;
	}

}
