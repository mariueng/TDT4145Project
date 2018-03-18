package jdbc;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		InputHandler handler = new InputHandler(System.out);
		Scanner scanner = new Scanner(System.in);
		String lastCommand = "";
		System.out.println("Type 'help' for command information");
		while((!lastCommand.equals("exit")) && (scanner.hasNextLine())) {
			lastCommand = scanner.nextLine();
			handler.handleInput(lastCommand);
		}
		scanner.close();

	}
}
