package jdbc;

import java.io.PrintStream;
import java.util.Scanner;

public class InputHandler {
	
	private Driver driver;
	private PrintStream stream;
	
	public InputHandler(PrintStream stream) {
		try {
			driver = new Driver();
			this.stream = stream;
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void handleInput(String input) {
		String[] token = input.split("_");
		if (token[0].equals("add")) {
			addHandler(token);
		} else if (token[0].equals("search")) {
			searchHandler(token);
		} else if (token[0].equals("make")) {
			makeHandler(token);
		} else if (token[0].equals("show")) {
			
		} else if (token[0].equals("help")) {
			
		} else if (token[0].equals("exit")) {
			
		} else {
			print("Type 'help' for more information");
		}
	}
	
	private void print(String string) {
		stream.println(string);
		
	}
	
	
	private void makeHandler(String[] token) {
		if (token[1].equals("kategori")) {
			//handler.handle(navn,beskrivelse)
		}
		
	}

	private void searchHandler(String[] token) {
		if (token[1].equals("treningsokt")) {
			//handler.handle(n siste treningsøkter)
		} else if (token[1].equals("ovelse")) {
			//handler.handle(min,max)
		} else if (token[1].equals("kategori")) {
			print(driver.getApparat(token[2]));
		} else {
			print("Type 'help' for more information");
		}
		
	}

	private void addHandler(String[] token) {
		if (token[1].equals("apparat")) {
			print(driver.addApparat(token[2], token[3]));
		} else if (token[1].equals("friovelse")) {
			print(driver.addFriOvelse(token[2], token[3]));
		} else if (token[1].equals("fastovelse")) {
			print(driver.addFastMontert(token[2], token[3], token[4], token[5]));
		} else if (token[1].equals("treningsokt")) {
			print(driver.addTreningsOkt(token[2], token[3], token[4], token[5], token[6]));
		} else {
			print("Type 'help' for more information");
		}
	}
}
