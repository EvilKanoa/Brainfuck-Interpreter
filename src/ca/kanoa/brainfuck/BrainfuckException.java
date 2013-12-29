package ca.kanoa.brainfuck;

public class BrainfuckException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public BrainfuckException(String exception, int index, char code) {
		super(exception + " at " + index + " near: " + code);
	}
	
	public BrainfuckException(String exception) {
		super(exception);
	}
	
	public BrainfuckException() {
		super("Unknown exception");
	}

}
