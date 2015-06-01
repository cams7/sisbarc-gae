/**
 * 
 */
package br.com.cams7.webapp.repository;

/**
 * @author cams7
 *
 */
public interface SequenceRepository {
	long getNextId(String className);
}
