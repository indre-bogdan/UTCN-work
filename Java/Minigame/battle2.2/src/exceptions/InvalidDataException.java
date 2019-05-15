package exceptions;

/**
 * used when a player wants to begin a game without any soldiers
 */
public class InvalidDataException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidDataException() {
	}

	public InvalidDataException(String msg) {
		super(msg);
	}
}
