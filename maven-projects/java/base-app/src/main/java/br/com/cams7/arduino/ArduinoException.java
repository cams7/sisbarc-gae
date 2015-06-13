/**
 * 
 */
package br.com.cams7.arduino;

import javax.xml.ws.WebFault;

import br.com.cams7.ws.ServiceFault;

/**
 * @author cesar
 *
 */

@WebFault(name = ArduinoException.WEBFAULT_NAME, targetNamespace = ArduinoException.WEBFAULT_TARGET_NAMESPACE)
public class ArduinoException extends Exception {

	public static final String WEBFAULT_NAME = "ArduinoFault";
	public static final String WEBFAULT_TARGET_NAMESPACE = "http://arduino.cams7.com.br";

	public static final String WEBFAULT_PACKAGE = "br.com.cams7.arduino";

	private static final long serialVersionUID = 1L;

	private ServiceFault faultInfo;

	/**
	 * 
	 * @param message
	 * @param faultInfo
	 */
	public ArduinoException(String message, ServiceFault faultInfo) {
		super(message);
		this.faultInfo = faultInfo;
	}

	/**
	 * 
	 * @param message
	 * @param faultInfo
	 * @param cause
	 */
	public ArduinoException(String message, ServiceFault faultInfo,
			Throwable cause) {
		super(message, cause);
		this.faultInfo = faultInfo;
	}

	/**
	 * 
	 */
	public ArduinoException() {
		super();
	}

	/**
	 * @param message
	 */
	public ArduinoException(String message) {
		this(message, new ServiceFault(message));
	}

	/**
	 * @param message
	 */
	public ArduinoException(String code, String message) {
		this(message, new ServiceFault(code, message));
	}

	/**
	 * @param cause
	 */
	public ArduinoException(Throwable cause) {
		this(cause.getMessage(), new ServiceFault(cause.getMessage()), cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ArduinoException(String message, Throwable cause) {
		this(message, new ServiceFault(message), cause);
	}

	/**
	 * 
	 * @return
	 */
	public ServiceFault getFaultInfo() {
		return faultInfo;
	}

}
