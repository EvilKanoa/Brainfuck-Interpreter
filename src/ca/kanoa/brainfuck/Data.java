package ca.kanoa.brainfuck;

import java.io.BufferedReader;

public class Data {

	// An array to hold the value of the cells
	private long[] array;
	// The current cell
	private int current;
	
	/**
	 * Make a new Data object and set all values in the array to 0
	 */
	public Data() {
		array = new long[30000];
		current = 1;
		
		for (int i = 0; i < array.length; i++) {
			array[i] = 0;
		}
	}
	
	/**
	 * Increments the value at the current cell by one.
	 * @code +
	 * @throws BrainfuckException If the current value is more than the size of the array or less than zero.
	 */
	public void increment() throws BrainfuckException {
		if (current >= array.length || current < 1) {
			throw new BrainfuckException("current value is too extreme");
		} 
		
		array[current]++;
	}
	
	/**
	 * Decrements the value at the current cell by one.
	 * @code -
	 * @throws BrainfuckException If the current value is more than the size of the array or less than zero.
	 */
	public void decrement() throws BrainfuckException {
		if (current >= array.length || current < 1) {
			throw new BrainfuckException("current value is too extreme");
		}
		
		array[current]--;
	}
	
	/**
	 * Moves the data pointer to the next cell (cell on the right).
	 * @code >
	 * @throws BrainfuckException If the current value is already at the max.
	 */
	public void next() throws BrainfuckException {
		if (current >= array.length) {
			throw new BrainfuckException("current value is max");
		}
		
		current++;
	}
	
	/**
	 * Moves the data pointer to the previous cell (cell on the left).
	 * @code <
	 * @throws BrainfuckException If the current value is already as low as it can go.
	 */
	public void prev() throws BrainfuckException {
		if (current <= 1) {
			throw new BrainfuckException("current value is min");
		}
		
		current--;
	}
	
	/**
	 * Prints the ASCII value at the current cell (i.e. 65 = 'A').
	 * @code .
	 * @throws BrainfuckException If the current cell does not hold a valid ASCII value.
	 */
	public void print() throws BrainfuckException {
		if ((array[current] < 0 || array[current] > 127) && !Main.numbers) {
			throw new BrainfuckException("current cell does not hold a valid ASCII value");
		}
		
		try {
			if (Main.numbers) {
				System.out.println(array[current]);
			} else {
				System.out.print((char) array[current]);
			}
		} catch (ClassCastException ex) {
			throw new BrainfuckException("current cell could not be printed");
		}
	}
	
	/**
	 * Reads a single input character into the current cell.
	 * @code ,
	 * @param reader The object to get input from
	 * @throws BrainfuckException If the reader errors or the input character isn't supported.
	 */
	public void read(BufferedReader reader) throws BrainfuckException {
		int ch;
		try {
			ch = reader.readLine().charAt(0);
		} catch (Exception e) {
			throw new BrainfuckException("error reading character");
		}
		if (ch < 0 || ch > 127) {
			throw new BrainfuckException("current input character is not supported");
		}
		
		array[current] = ch;
	}
	
	/**
	 * Checks if the value at the current cell is zero.
	 * @code [ and ]
	 * @return True if the value at the current cell is zero.
	 */
	public boolean current() {
		return array[current] == 0;
	}
	
}
