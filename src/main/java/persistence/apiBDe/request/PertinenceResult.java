package persistence.apiBDe.request;

/**
 * Association with an element and the pertience score
 * 
 * @author Aldric Vitali Silvestre
 * @param <E> the type of the object contained in.
 */
public class PertinenceResult<E> {

	private E result;
	
	private double score;
	
	public PertinenceResult(E result, double score) {
		super();
		this.result = result;
		this.score = score;
	}

	public E getResult() {
		return result;
	}

	public void setResult(E result) {
		this.result = result;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
}
