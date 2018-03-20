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
		} else if (token[0].equals("show")) {
			showHandler(token);
		} else if (token[0].equals("make")) {
			makeHandler(token);
		} else if (token[0].equals("help")) {
			helpHandler();
		} else if (token[0].equals("exit")) {
			
		} else {
			print("Error type corrct command");
			print("Type 'help' for command information");
		}
	}
	
	private void makeHandler(String[] token) {
		if (token[1].equals("treningsovelse")) {
			if (token.length != 4) {
				wrongSizeHandler();
			} else {
				print(driver.makeTreningOvelse(token[2], token[3]));}
		} else if (token[1].equals("ovelseikategori")) {
			if (token.length != 4) {
				wrongSizeHandler();
			} else {
				print(driver.ovelseIKategori(token[2],token[3]));}
		} else {
			print("Error type correct make command");
			print("Type 'help' for command information");
		}
		
	}

	
	private void wrongSizeHandler() {
		// TODO Auto-generated method stub
		
	}

	private void showHandler(String[] token) {
		if (token[1].equals("apparat")) {
			if (token.length != 2) {
				wrongSizeHandler();
			} else {
				print(driver.getApparat());}
		} else if (token[1].equals("treningsokt")) {
			if (token.length != 2) {
				wrongSizeHandler();
			} else {
				print(driver.getTreningsOkt());}
		} else if (token[1].equals("kategori")) {
			if (token.length != 2) {
				wrongSizeHandler();
			} else {
				print(driver.getKategori());}
		} else if (token[1].equals("ovelse")) {
			if (token.length != 2) {
				wrongSizeHandler();
			} else {
				print(driver.getOvelse());}
		} else {
			print("Error type correct show command");
			print("Type 'help' for command information");
		}
		
	}

	private void helpHandler() {
		print("Use '_' for seperating commands");
		print("Write the whole command in a single line using the correct commands");
		print("------------------------------------------------------------------------------");
		print("Main commands: 'add', 'search', 'show', 'exit'");
		print("------------------------------------------------------------------------------");
		print("add commands:");
		print("apparat: navn, beskrivelse");
		print("friovelse: navn, beskrivelse");
		print("fastovelse: navn, kg, sett, apparat");
		print("treningsokt: , varighet i min, informasjon, form mellom 0 og 10, presentasjon mellom 0 og 10");
		print("------------------------------------------------------------------------------");
		print("search commands:");
		print("treningsokt: n siste treningsokter");
		print("kategori: ovelsenavn");
		print("------------------------------------------------------------------------------");
		print("show commands:");
		print("apparat: ");
		print("treningsokt: ");
		print("kategori: ");
		print("ovelse: ");
		print("------------------------------------------------------------------------------");
		print("make commands:");
		print("treningsovelse: oktID, ovelsenavn");
		print("ovelseikategori: ovelsenavn, kategorinavn");
		print("------------------------------------------------------------------------------");
	}

	private void print(String string) {
		stream.println(string);
		
	}

	private void searchHandler(String[] token) {
		if (token[1].equals("treningsokt")) {
			if (token.length != 3) {
				wrongSizeHandler();
			} else {
				print(driver.getNTreningsokt(token[2]));}
		} else if (token[1].equals("kategori")) {
			if (token.length != 3) {
				wrongSizeHandler();
			} else {
				print(driver.findSammeOvelseKategori(token[2]));}
		} else {
			print("Error type correct search command");
			print("Type 'help' for command information");
		}
		
	}

	private void addHandler(String[] token) {
		if (token[1].equals("apparat")) {
			if (token.length != 4) {
				wrongSizeHandler();
			} else {
				print(driver.addApparat(token[2], token[3]));}
		} else if (token[1].equals("friovelse")) {
			if (token.length != 4) {
				wrongSizeHandler();
			} else {
				print(driver.addFriOvelse(token[2], token[3]));}
		} else if (token[1].equals("fastovelse")) {
			if (token.length != 6) {
				wrongSizeHandler();
			} else {
				print(driver.addFastMontert(token[2], token[3], token[4], token[5]));}
		} else if (token[1].equals("treningsokt")) {
			if (token.length != 7) {
				wrongSizeHandler();
			} else {
				print(driver.addTreningsOkt(token[2], token[3], token[4], token[5], token[6]));}
		} else if (token[1].equals("kategori")) {
			if (token.length != 4) {
				wrongSizeHandler();
			} else {
				print(driver.addKategori(token[2],token[3]));}
		} else {
			print("Error type correct add command");
			print("Type 'help' for command information");
		}
	}
}
