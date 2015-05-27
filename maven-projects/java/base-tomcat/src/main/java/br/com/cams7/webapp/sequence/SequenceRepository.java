/**
 * 
 */
package br.com.cams7.webapp.sequence;

/**
 * @author cams7
 *
 */
public interface SequenceRepository {
	long getNextId(String className);
}
