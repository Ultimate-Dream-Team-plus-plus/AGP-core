package persistence.apiBDe.request;

import java.util.Iterator;

/**
 * This interface allow the user to execute all his SQL request and get the
 * response as an Iterator
 * 
 * @author nico
 *
 * @param <E>
 */
public interface RequestManager<E> {

	/**
	 * Execute an SQL Code and get the response as an Iterator
	 * 
	 * @param request : String - The SQL Request
	 * @return Iterator
	 */
	public Iterator<E> request(String request);

}
