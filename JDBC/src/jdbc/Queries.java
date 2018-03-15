package jdbc;

public final class Queries {
	
	/*
	 * Prepared statements
	 */
	public static final String GET_ALL_APPARAT = "SELECT * FROM apparat";
	
	
	/*
	 * Functional statements
	 */
	public static final String GET_APPARAT_BY_ID(int id) {
		return "SELECT * FROM apparat WHERE ApparatID LIKE " + id;
	}
	
	//Registrere apparat
	public static String INSERT_APPARAT(String navn, String beskrivelse) {
		return "INSERT INTO apparat (Navn, Beskrivelse) VALUES (\"" + navn + "\", \"" + beskrivelse +"\")";
	}
}
