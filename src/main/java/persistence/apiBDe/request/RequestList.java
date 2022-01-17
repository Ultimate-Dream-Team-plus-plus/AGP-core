package persistence.apiBDe.request;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Implementation of Iterator that will be used to navigate through database
 * answer
 * 
 * @author nico
 *
 * @param <E>
 */
public class RequestList<E> implements Iterable<E> {
	
	private List<E> list;

	public RequestList(List<E> values) {
		this.list = values;
	}
	
	class RequestIterator<E> implements Iterator<E>{
		int current = 0;

		@Override
		public boolean hasNext() {
			if(current < list.size()) {
				return true;
			}
			return false;
		}

		@SuppressWarnings("unchecked")
		@Override
		public E next() {
			if (!hasNext()) {
                throw new NoSuchElementException();
            }
			return (E) list.get(current++);
		}
		
	}
	

	@Override
	public Iterator<E> iterator() {
		return new RequestIterator();
	}

}
