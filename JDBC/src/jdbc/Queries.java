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
	
	//Se usecase fra innlevering 1: Gitt en apparatID returnerer denne spørringen hvor mange ganger apparatet har blitt brukt den siste måneden
	public static String GET_APPARAT_USAGE_LAST_MONTH(int apparatId) {
		return "SELECT COUNT(*) FROM apparat INNER JOIN ovelsefastmontert ON apparat.apparatID = ovelsefastmontert.apparatID INNER JOIN treningsoktovelse ON"
				+ "ovelsefastmontert.ovelseID = treningsoktovelse.ovelseID INNER JOIN treningsoekt ON treningsoktovelse.oektID = treningsoekt.oektID WHERE apparat.apparatID LIKE "
				+ apparatId + " AND DatoTidspunkt > (CURRENT_DATE() - INTERVAL 1 MONTH)";
	}
	
	/*
	 * =============================== ØVELSER =====================================
	 * Før enhver registrering av øvelse på NEW_OVELSE utføres først for å lage en entitet av superklassen.
	 */
	
	//Henter alle øvelser og returnerer navn
	public static String GET_ALL_OVELSE() {
		return "(SELECT Navn FROM ovelsefastmontert) UNION (SELECT Navn FROM ovelsefriovelse);";
	}
	
	//Lager en ny øvelse, dette er i prinsippet kun et heltall, men tabellen er brukt for å knytte ulike typer øvelser opp mot en superklasse
	public static String NEW_OVELSE() {
		return "INSERT INTO ovelse () VALUES ();";
	}
	
	// Setter inn ny friøvelse gitt navn og beskrivelse. Denne spørringen forutsetter at en ny øvelse er laget på forhånd slik at
	// friøvelsen kan knyttes opp mot denne hovedøvelsen
	public static String INSERT_FRIOVELSE(String navn, String beskrivelse) {
		return "INSERT INTO ovelsefriovelse (OvelseID, Navn, Beskrivelse) VALUES ((SELECT max(OvelseID) FROM Ovelse), \"" + navn + 
				"\", \"" + beskrivelse + "\");";
	}
	
	// Setter inn ny fastmontert øvelse gitt navn, antall kilo, antall sett og apparatId. Dersom apparatId ikke finnes vil SQL kaste en exception
	// Denne spørringen forutsetter at en ny øvelse er laget på forhånd slik at den fastmonterte øvelsen kan knyttes mot superøvelsen
	public static String INSERT_FASTMONTERT_OVELSE(String navn, double kg, int sett, int apparatId) {
		return "INSERT INTO ovelsefastmontert (OvelseID, Navn, Kilogram, Sett, ApparatID) VALUES"
				+ "((SELECT max(OvelseID) FROM Ovelse), \"" + navn + "\", \"" + kg + "\", \"" + sett + "\", " + "\" " + apparatId +"\");";
	}
	
	//Lager en ny kategori
	public static String INSERT_KATEGORI(String navn, String beskrivelse) {
		return "INSERT INTO kategori (Navn, Beskrivelse) VALUES (\"" + navn + "\",\"" + beskrivelse + "\");";
	}
	
	//Knytte en øvelse til en kategori
	public static String CONNECT_OVELSE_KATEGORI(int ovelseId, String kategorinavn) {
		return "INSERT INTO ovelseinngaarikategori (OvelseID, Navn) VALUES (" + ovelseId + ",\"" + kategorinavn + "\");";
	}
	
	//Spør etter alle øvelser som inngår i gitt kategori, returnerer navnet på de øvelsene som inngår i gitt kategori
	public static String GET_OVELSE_BY_KATEGORI(String kategorinavn) {
		return "(SELECT ovelsefastmontert.navn AS Navn FROM ovelsefastmontert NATURAL JOIN ovelse INNER JOIN ovelseinngaarikategori ON ovelse.ovelseID = ovelseinngaarikategori.ovelseID"
				+ " WHERE ovelseinngaarikategori.navn LIKE \"" + kategorinavn + "\") UNION (SELECT ovelsefriovelse.navn AS Navn FROM ovelsefriovelse NATURAL JOIN ovelse "
				+ "INNER JOIN ovelseinngaarikategori ON ovelse.ovelseID = ovelseinngaarikategori.ovelseID WHERE ovelseinngaarikategori.navn LIKE \"" + kategorinavn + "\")";
	}
	
	/*
	 * ================================= TRENINGSØKTER ===================================
	 */
	
	//Henter alle treningsøkter returnerer oektID og Dato
	public static String GET_ALL_TRENINGSOEKT() {
		return "SELECT oektID, DatoTidspunkt, Informasjon FROM treningsoekt";
	}
	
	
	//Setter inn en ny treningsøkt i databasen gitt tidspunkt, varighet, informasjom form og prestasjon
	public static String INSERT_TRENINGSOKT(Timestamp datoTid, int varighetMin, String informasjon, int form, int prestasjon) {
		return "INSERT INTO treningsoekt (DatoTidspunkt, Varighetminutter, Informasjon, Form, Prestasjon) VALUES"
				+ "(\'" + datoTid + "\', " + varighetMin + ", " + "\"" + informasjon + "\", " + form + ", " + prestasjon +");";
	}
	
	//Knytter en øvelse til en økt slik det kan utforskes hvilke øvelser som har inngått i hvilke treningsøkter
	public static String CONNECT_TRENINGSOKT_OVELSE(int treningsoktId, int ovelseId) {
		return "INSERT INTO treningsoktovelse (OektID, OvelseID) VALUES (" + treningsoktId + "," + ovelseId + ");";
	}
	
	
	//Henter N siste treningsøkter, med informasjon
	public static String GET_N_LAST_TRENINGSOKT(int n) {
		return "SELECT * FROM treningsoekt ORDER BY DatoTidspunkt DESC LIMIT " + n + ";";
		
	}
	
	//Se oppgave 3: Gitt et tidsintervall og en øvelse returnerer denne alle treningsøktene som innehar denne øvelsen
	public static String GET_OKT_BY_OVELSE_AND_INTERVAL(int ovelseId, Timestamp intervalStart, Timestamp intervalSlutt) {
		return "SELECT treningsoekt.* FROM treningsoekt INNER JOIN treningsoktovelse ON treningsoekt.oektid = treningsoktovelse.oektID "
				+ "WHERE DatoTidspunkt > '" +intervalStart.toString() + "' AND DatoTidspunkt < '" + intervalSlutt.toString() + "' AND treningsoktovelse.oektID = "
						+ ovelseId + ";";

	}
	
	
}