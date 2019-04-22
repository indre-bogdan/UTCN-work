package exceptions;

/**
 * used when a player wants to begin a game without a name
 */
public class InvalidNameException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidNameException() {
	}

	public InvalidNameException(String msg) {
		super(msg);
	}
}
