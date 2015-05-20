/**
 * 
 */
package br.com.cams7.arduino;

/**
 * @author cams7
 *
 */
public interface ArduinoService {
	public void connect();

	public void close();

	public boolean isInitialized();
}
