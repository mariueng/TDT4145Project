package jdbc;

import java.sql.Timestamp;

public final class Queries {
	
	/*
	 * ================================ APPARATER ===================================
	 */
	
	//Henter alle apparater
	public static String GET_ALL_APPARAT() {
		return "SELECT Navn FROM apparat";
	}
	
	//Henter apparat gitt apparatId
	public static final String GET_APPARAT_BY_ID(int id) {
		return "SELECT * FROM apparat WHERE ApparatID LIKE " + id;
	}
	
	//Henter apparat gitt navn
	public static String GET_APPARAT_BY_NAME(String navn) {
		return "SELECT * FROM apparat WHERE Navn LIKE \"" + navn + "\";";
	}
	
	public static String GET_APPARAT_ID_BY_NAME(String navn) {
		return "SELECT ApparatID FROM apparat WHERE Navn LIKE \"" + navn + "\";";
	}
	
	//Setter inn nytt apparat med gitt navn og beskrivelse i tabellen. Id blir autogenerert i databasen
	public static String INSERT_APPARAT(String navn, String beskrivelse) {
		return "INSERT INTO apparat (Navn, Beskrivelse) VALUES (\"" + navn + "\", \"" + beskrivelse +"\")";
	}
	
	//Se usecase fra innlevering 1: Gitt en apparatID returnerer denne sp�rringen hvor mange ganger apparatet har blitt brukt den siste m�neden
	public static String GET_APPARAT_USAGE_LAST_MONTH(int apparatId) {
		return "SELECT COUNT(*) FROM apparat INNER JOIN ovelsefastmontert ON apparat.apparatID = ovelsefastmontert.apparatID INNER JOIN treningsoktovelse ON"
				+ "ovelsefastmontert.ovelseID = treningsoktovelse.ovelseID INNER JOIN treningsoekt ON treningsoktovelse.oektID = treningsoekt.oektID WHERE apparat.apparatID LIKE "
				+ apparatId + " AND DatoTidspunkt > (CURRENT_DATE() - INTERVAL 1 MONTH)";
	}
	
	/*
	 * =============================== �VELSER =====================================
	 * F�r enhver registrering av �velse p� NEW_OVELSE utf�res f�rst for � lage en entitet av superklassen.
	 */
	
	//Henter alle �velser og returnerer navn
	public static String GET_ALL_OVELSE() {
		return "(SELECT Navn FROM ovelsefastmontert) UNION (SELECT Navn FROM ovelsefriovelse);";
	}
	
	//Lager en ny �velse, dette er i prinsippet kun et heltall, men tabellen er brukt for � knytte ulike typer �velser opp mot en superklasse
	public static String NEW_OVELSE() {
		return "INSERT INTO ovelse () VALUES ();";
	}
	
	// Setter inn ny fri�velse gitt navn og beskrivelse. Denne sp�rringen forutsetter at en ny �velse er laget p� forh�nd slik at
	// fri�velsen kan knyttes opp mot denne hoved�velsen
	public static String INSERT_FRIOVELSE(String navn, String beskrivelse) {
		return "INSERT INTO ovelsefriovelse (OvelseID, Navn, Beskrivelse) VALUES ((SELECT max(OvelseID) FROM Ovelse), \"" + navn + 
				"\", \"" + beskrivelse + "\");";
	}
	
	// Setter inn ny fastmontert �velse gitt navn, antall kilo, antall sett og apparatId. Dersom apparatId ikke finnes vil SQL kaste en exception
	// Denne sp�rringen forutsetter at en ny �velse er laget p� forh�nd slik at den fastmonterte �velsen kan knyttes mot super�velsen
	public static String INSERT_FASTMONTERT_OVELSE(String navn, double kg, int sett, int apparatId) {
		return "INSERT INTO ovelsefastmontert (OvelseID, Navn, Kilogram, Sett, ApparatID) VALUES"
				+ "((SELECT max(OvelseID) FROM Ovelse), \"" + navn + "\", \"" + kg + "\", \"" + sett + "\", " + "\" " + apparatId +"\");";
	}
	
	//Lager en ny kategori
	public static String INSERT_KATEGORI(String navn, String beskrivelse) {
		return "INSERT INTO kategori (Navn, Beskrivelse) VALUES (\"" + navn + "\",\"" + beskrivelse + "\");";
	}
	
	//Knytte en �velse til en kategori
	public static String CONNECT_OVELSE_KATEGORI(int ovelseId, String kategorinavn) {
		return "INSERT INTO ovelseinngaarikategori (OvelseID, Navn) VALUES (" + ovelseId + ",\"" + kategorinavn + "\");";
	}
	
	//Sp�r etter alle �velser som inng�r i gitt kategori, returnerer navnet p� de �velsene som inng�r i gitt kategori
	public static String GET_OVELSE_BY_KATEGORI(String kategorinavn) {
		return "(SELECT ovelsefastmontert.navn AS Navn FROM ovelsefastmontert NATURAL JOIN ovelse INNER JOIN ovelseinngaarikategori ON ovelse.ovelseID = ovelseinngaarikategori.ovelseID"
				+ " WHERE ovelseinngaarikategori.navn LIKE \"" + kategorinavn + "\") UNION (SELECT ovelsefriovelse.navn AS Navn FROM ovelsefriovelse NATURAL JOIN ovelse "
				+ "INNER JOIN ovelseinngaarikategori ON ovelse.ovelseID = ovelseinngaarikategori.ovelseID WHERE ovelseinngaarikategori.navn LIKE \"" + kategorinavn + "\")";
	}
	
	/*
	 * ================================= TRENINGS�KTER ===================================
	 */
	
	//Henter alle trenings�kter returnerer oektID og Dato
	public static String GET_ALL_TRENINGSOEKT() {
		return "SELECT oektID, DatoTidspunkt, Informasjon FROM treningsoekt";
	}
	
	
	//Setter inn en ny trenings�kt i databasen gitt tidspunkt, varighet, informasjom form og prestasjon
	public static String INSERT_TRENINGSOKT(Timestamp datoTid, int varighetMin, String informasjon, int form, int prestasjon) {
		return "INSERT INTO treningsoekt (DatoTidspunkt, Varighetminutter, Informasjon, Form, Prestasjon) VALUES"
				+ "(\'" + datoTid + "\', " + varighetMin + ", " + "\"" + informasjon + "\", " + form + ", " + prestasjon +");";
	}
	
	//Knytter en �velse til en �kt slik det kan utforskes hvilke �velser som har inng�tt i hvilke trenings�kter
	public static String CONNECT_TRENINGSOKT_OVELSE(int treningsoktId, int ovelseId) {
		return "INSERT INTO treningsoktovelse (OektID, OvelseID) VALUES (" + treningsoktId + "," + ovelseId + ");";
	}
	
	
	//Henter N siste trenings�kter, med informasjon
	public static String GET_N_LAST_TRENINGSOKT(int n) {
		return "SELECT * FROM treningsoekt ORDER BY DatoTidspunkt DESC LIMIT " + n + ";";
		
	}
	
	//Se oppgave 3: Gitt et tidsintervall og en �velse returnerer denne alle trenings�ktene som innehar denne �velsen
	public static String GET_OKT_BY_OVELSE_AND_INTERVAL(int ovelseId, Timestamp intervalStart, Timestamp intervalSlutt) {
		return "SELECT treningsoekt.* FROM treningsoekt INNER JOIN treningsoktovelse ON treningsoekt.oektid = treningsoktovelse.oektID "
				+ "WHERE DatoTidspunkt > '" +intervalStart.toString() + "' AND DatoTidspunkt < '" + intervalSlutt.toString() + "' AND treningsoktovelse.oektID = "
						+ ovelseId + ";";

	}
	
	
}