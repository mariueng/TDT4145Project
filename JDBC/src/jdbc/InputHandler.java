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
		String[] token = input.split("_");
		if (token[0].equals("add")) {
			addHandler(token);
		} else if (token[0].equals("search")) {
			searchHandler(token);
		} else if (token[0].equals("make")) {
			makeHandler(token);
		} else if (token[0].equals("show")) {
			
		} else if (token[0].equals("help")) {
			
		} else {
			print("Type 'help' for more information");
		}
	}
	
	private void print(String string) {
		stream.println(string);
		
	}

	private void makeHandler(String[] token) {
		//handler.handle(navn, beskrivelse) //se på kategori
		
	}

	private void searchHandler(String[] token) {
		if (token[1].equals("treningsokt")) {
			//handler.handle(n siste treningsøkter)
		} else if (token[1].equals("ovelse")) {
			//handler.handle(min,max)
		} else if (token[1].equals("kategori")) {
			//handler.handle(navn)
		}
		
	}

	private void addHandler(String[] token) {
		if (token[1].equals("apparat")) {
			//
			System.out.println("Apparat");
		} else if (token[1].equals("friovelse")) {
			//
		} else if (token[1].equals("fastovelse")) {
			//
		} else if (token[1].equals("treningsokt")) {
			//
		}
		
	}
	
}
