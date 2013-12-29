package ca.kanoa.brainfuck;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static boolean numbers;
	public static final String DOC = "\n"
			+ "\t\t\tKanoa's brainfuck Interpreter\n"
			+ "Commands:\n"
			+ "\t -file (-f) : Loads code from file\n"
			+ "\t -help (help ?) : Displays help (this)\n"
			+ "Usage:\n"
			+ "\tbrainfuck.jar code\n"
			+ "\tbrainfuck.jar -file FileWithCode.b\n"
			+ "\tbrainfuck.jar -help\n"
			+ "\n"
			+ "\t\t\t\tBrainfuck Docs\n"
			+ "\n"
			+ "Any character not \"><+-.,[]\" (excluding quotation marks) is ignored.\n"
			+ ""
			+ "Brainfuck is represented by an array with 30,000 cells initialized to zero and a data pointer pointing at the current cell.\n"
			+ "\n"
			+ "There are eight commands:\n"
			+ "+ : Increments the value at the current cell by one.\n"
			+ "- : Decrements the value at the current cell by one.\n"
			+ "> : Moves the data pointer to the next cell (cell on the right).\n"
			+ "< : Moves the data pointer to the previous cell (cell on the left).\n"
			+ ". : Prints the ASCII value at the current cell (i.e. 65 = 'A').\n"
			+ ", : Reads a single input character into the current cell.\n"
			+ "[ : If the value at the current cell is zero, skips to the corresponding ] . Otherwise, move to the next instruction.\n"
			+ "] : If the value at the current cell is zero, move to the next instruction. Otherwise, move backwards in the instructions to the corresponding [ .\n"
			+ "\n"
			+ "[ and ] form a while loop. Obviously, they must be balanced.\n"
			+ "\n"
			+ "Have fun making programs!\n";
	
	private static String code;
	private static Interpreter interpreter;
	
	public static void main(String[] args) {
		if (Arrays.asList(args).contains("-f") || 
				Arrays.asList(args).contains("-file")) {
			for (String str : args) {
				if (!str.equalsIgnoreCase("-f") && !str.equalsIgnoreCase("-file")) {
					System.out.println("Loading code from " + str);
					code = loadTextFile(str);
					System.out.println("Code loaded:\n" + code);
					break;
				}
			}
		} else if (Arrays.asList(args).contains("?") || 
				Arrays.asList(args).contains("-help") || 
				Arrays.asList(args).contains("help")) {
			System.out.print(Main.DOC);
			System.exit(0);
		} else {
			StringBuilder builder = new StringBuilder();
			for (String str : args) {
				builder.append(str).append(" ");
			}
			code = builder.toString().replace("-n", "").replace("-numbers", "");
		}
		
		numbers = Arrays.asList(args).contains("-numbers") || Arrays.asList(args).contains("-n");
		System.out.println("Running program using " + (numbers ? "numbers" : "ASCII") + "...\n");
		interpreter = new Interpreter(new BufferedReader(new InputStreamReader(System.in)));
		if (interpreter.execute(code)) {
			System.out.println("\nCode completed successfully");
		} else {
			System.out.println("\nCode did not complete successfully");
		}
		
		System.exit(0);
	
	}
	
	public static String loadTextFile(String fileName) {
		File file = new File(fileName);
		if (file.isDirectory()) {
			return "";
		}

		StringBuilder builder = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String s;
			while ((s = reader.readLine()) != null) {
				builder.append(s).append("\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return builder.toString();
	}

}
