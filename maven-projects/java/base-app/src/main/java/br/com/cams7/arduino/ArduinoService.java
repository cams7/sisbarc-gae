/**
 * 
 */
package br.com.cams7.arduino;

/**
 * @author cams7
 *
 */
public interface ArduinoService {
	public void openConnection() throws ArduinoException;

	public void closeConnection() throws ArduinoException;

	public String getSerialPort();

	public int getSerialBaudRate();

	public int getSerialThreadInterval();

	public boolean isConnected();
}
