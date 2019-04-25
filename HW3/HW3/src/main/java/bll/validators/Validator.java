package bll.validators;

/**
 * Interface to for each validator
 * 
 * @author IndreBogdan
 *
 * @param <T>
 */
public interface Validator<T> {

	public void validate(T t);
}
