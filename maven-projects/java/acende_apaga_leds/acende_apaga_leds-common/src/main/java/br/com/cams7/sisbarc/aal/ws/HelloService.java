package br.com.cams7.sisbarc.aal.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = HelloService.WEBSERVICE_NAME, targetNamespace = HelloService.WEBSERVICE_TARGETNAMESPACE)
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface HelloService {

	public static final String WEBSERVICE_NAME = "HelloService";
	public static final String WEBSERVICE_TARGETNAMESPACE = "http://ws.aal.sisbarc.cams7.com.br/";

	@WebMethod
	public String printMessage(String message);

}