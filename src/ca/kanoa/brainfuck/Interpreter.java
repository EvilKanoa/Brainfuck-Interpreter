package ca.kanoa.brainfuck;

import java.io.BufferedReader;

public class Interpreter {

	private BufferedReader reader;
	private int index = 0;
	private char[] source;

	public Interpreter(BufferedReader reader) {
		this.reader = reader;
	}

	public boolean execute(String code) {
		source = code.replaceAll("[^+-><.,\\[\\]]", "").toCharArray();
		Data data = new Data();

		while (index < source.length) {
			try {
				switch (source[index]) {

				case '+':
					data.increment();
					break;

				case '-':
					data.decrement();
					break;

				case '>':
					data.next();
					break;

				case '<':
					data.prev();
					break;

				case '.':
					data.print();
					break;

				case ',':
					data.read(reader);
					break;
					
				case '[':
					if (data.current()) {
						int depth = 0;
						while (!(source[++index] == ']' && depth == 0)) {
							if (source[index] == '[') {
								depth++;
							} else if (source[index] == ']') {
								depth--;
							}
						}
					} else {
						break;
					}
					
				case ']':
					if (data.current()) {
						break;
					} else {
						int depth = 0;
						while (!(source[--index] == '[' && depth == 0)) {
							if (source[index] == ']') {
								depth++;
							} else if (source[index] == '[') {
								depth--;
							}
						}
					}

				}
			} catch (BrainfuckException ex) {
				ex.printStackTrace();
				return false;
			}

			index++;
		}

		System.out.println();

		return true;
	}

}
