package jdbc;

import java.io.PrintStream;

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
			showHandler(token);
		} else if (token[0].equals("help")) {
			helpHandler();
		} else if (token[0].equals("exit")) {
			
		} else {
			print("Error type corrct command");
			print("Type 'help' for command information");
		}
	}
	
	private void showHandler(String[] token) {
		if (token[1].equals("apparat")) {
			driver.getApparat();
		} else {
			print("Error type correct make command");
			print("Type 'help' for command information");
		}
		
	}

	private void helpHandler() {
		print("Use '_' for seperating commands");
		print("Write the whole command in a single line using the correct commands");
		print("Main commands: 'add', 'search', 'make', 'show', 'exit'");
		print("------------------------------------------------------------------------------");
		print("add commands:");
		print("apparat: navn, beskrivelse");
		print("friovelse: navn, beskrivelse");
		print("fastovelse: navn, kg, sett, apparat");
		print("treningsokt: , varighet i min, informasjon, form mellom 0 og 10, presentasjon mellom 0 og 10");
		print("------------------------------------------------------------------------------");
		print("search commands:");
		print("treningsøkt: ");
		print("ovelse: ");
		print("kategori: ");
		
	}

	private void print(String string) {
		stream.println(string);
		
	}
	
	
	private void makeHandler(String[] token) {
		if (token[1].equals("kategori")) {
			//handler.handle(navn,beskrivelse)
		} else {
			print("Error type correct make command");
			print("Type 'help' for command information");
		}
		
	}

	private void searchHandler(String[] token) {
		if (token[1].equals("treningsokt")) {
			//handler.handle(n siste treningsøkter)
		} else if (token[1].equals("ovelse")) {
			//handler.handle(min,max)
		} else if (token[1].equals("kategori")) {
			//
		} else {
			print("Error type correct add command");
			print("Type 'help' for command information");
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
			print("Error type correct add command");
			print("Type 'help' for command information");
		}
	}
}
