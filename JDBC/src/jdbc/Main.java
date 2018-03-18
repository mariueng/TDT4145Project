package jdbc;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		InputHandler handler = new InputHandler(System.out);
		Scanner scanner = new Scanner(System.in);
		String lastCommand = "";
		System.out.println("Write 'help' for more information");
		while((!lastCommand.equals("exit")) && (scanner.hasNextLine())) {
			lastCommand = scanner.nextLine();
			handler.handleInput(lastCommand);
		}
		handler.flush();
		scanner.close();

	}
}
