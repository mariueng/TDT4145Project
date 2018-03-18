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
		} catch(Exception e){}
	}
	
	public void handleInput(String input) {
		String[] token = input.split(" ");
		if (token[0].equals("add")) {
			addHandler(token);
		} else if (token[0].equals("search")) {
			searchHandler(token);
		} else if (token[0].equals("make")) {
			makeHandler(token);
		}
		
	}

	private void makeHandler(String[] token) {
		//handler.handle(navn, beskrivelse) //se på kategori
		
	}

	private void searchHandler(String[] token) {
		if (token[1].equals("treningsokt")) {
			//handler.handle(n siste treningsøkter)
		} else if (token[1].equals("ovelse")) {
			//handler.handle(min,max)
		} else if (token[1].equals("")) {
			
		}
		
	}

	private void addHandler(String[] token) {
		if (token[1].equals("apparat")) {
			//
		} else if (token[1].equals("friovelse")) {
			//
		} else if (token[1].equals("fastovelse")) {
			//
		} else if (token[1].equals("treningsokt")) {
			//
		}
		
	}
	
}
