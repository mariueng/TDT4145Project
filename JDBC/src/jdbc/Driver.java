package jdbc;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Driver {
		
	private Connection myConn;
	private Statement myStmt;
	private ResultSet myRs;
	
	public Driver() throws SQLException {
		myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prosjekt?autoReconnect=true&useSSL=false", "root", "root");
		myStmt = myConn.createStatement();
	}
	
	
	private Object executeReturnQuery(String query){
		try {
			return this.myStmt.executeQuery(query);
		}
		catch (Exception err) {
			return "Unsuccesful";
		}
	}

	private Object executeUpdateQuery(String query) {
		try {
			this.myStmt.executeUpdate(query);
		return "Successful";
		}
		catch (Exception e) {
			return "Unsuccesful";
		}
		
	}
	private Object executeInsertQuery(String query){
		try {
			this.myStmt.executeUpdate(query);
			return "Successful";
		}
		catch (Exception e) {
			return "Unsuccesful";
		}
		
	}
	
	//-----------------------INSERT-METODER--------------------------------------
	
	public String addApparat(String Navn, String Beskrivelse) {
		try {
			return (String) executeInsertQuery(Queries.INSERT_APPARAT(Navn, Beskrivelse));
		}
		catch (Exception e) {
			return "Unsuccessful";
		}
	}
	
	public String addFriOvelse(String Navn, String Beskrivelse) {
		try {
			executeInsertQuery(Queries.NEW_OVELSE());
			return (String) executeInsertQuery(Queries.INSERT_FRIOVELSE(Navn, Beskrivelse));
		}
		catch (Exception e) {
			return "Unsuccesful";
		}
	}
	
	public String addFastMontert(String Navn, String kg, String sett, String apparatNavn){
		try {
			String apparatid = (String) executeReturnQuery(Queries.GET_APPARAT_ID_BY_NAME(apparatNavn));
			executeInsertQuery(Queries.NEW_OVELSE());
			return (String) executeInsertQuery(Queries.INSERT_FASTMONTERT_OVELSE(Navn,Double.parseDouble(kg), Integer.parseInt(sett), Integer.parseInt(apparatid)));
		}
		catch (Exception e) {
			return "Unsuccessful";
		}
	}
	
	public String addTreningsOkt(String datotid, String varighetMin, String info, String form, String prestasjon) {
		try {


			return (String) executeInsertQuery(Queries.INSERT_TRENINGSOKT(Timestamp.valueOf(datotid),  Integer.parseInt(varighetMin), info, 
					Integer.parseInt(form), Integer.parseInt(prestasjon)));
		}
		catch (Exception e) {
			return "Unsuccesfull";
		}
	}
	
	
	
	public String addKategori(String navn, String beskrivelse) {
		try {
			executeInsertQuery(Queries.INSERT_KATEGORI(navn, beskrivelse));
			return "Successful";
		}
		catch (Exception e) {
			return "Unsuccessful";
		}
	}
	//-------------------------------SHOW-METODER---------------------------------------
	
	
	public String getApparat() {
		try {
			ResultSet rs = (ResultSet) executeReturnQuery(Queries.GET_ALL_APPARAT());
			String streng = "";
			while(rs.next()) {
				streng += rs.getString("Navn") + "\n";
			}
			return streng;
		}
		catch (Exception e) {
			return "Unsuccessful";
		}
	}

	public String getOvelse(){
		try {
			ResultSet rs = (ResultSet) executeReturnQuery(Queries.GET_ALL_OVELSE());
			String streng = "";
			while(rs.next()) {
				streng += rs.getString("Navn")+ "\n";
			}
			return streng;
		}
		catch (Exception e) {
			return "Unsuccessful";
		}
		
	}
	
	public String getTreningsOkt() {//HENTER ALLE TRENINGSØKTER (ØKT-ID, DATO BESKRIVELSE)
		try {
			ResultSet rs = (ResultSet) executeReturnQuery(Queries.GET_ALL_TRENINGSOEKT());
			String streng = "";
			while(rs.next()) {
				streng += "ØktID: "+ rs.getString("OektID") + " DatoTidspunkt: " + rs.getString("DatoTidspunkt") + " Beskrivelse: " + 
			rs.getString("Informasjon") + "\n";
			}
			return streng;
		}
		catch (Exception e) {
			return "Unsuccessful";
		}
		
	}
	
	public String getKategori() { //HENTER ALLE KATEOGRIER (NAVN)
		try {
			ResultSet rs = (ResultSet) executeReturnQuery(Queries.GET_ALL_KATEGORI());
			String streng ="";
			while(rs.next()) {
				streng += "Navn:" + rs.getString("Navn") + " Beskrivelse: " + rs.getString("Beskrivelse") + "\n";
			}
			return streng;
		}
		catch (Exception e) {
			return "Unsuccessful";
		}
	}
	
	
	//-------------------------------SEARCH-METODER-------------------------------------
	
	public String getNTreningsokt(String n) {
		try {
			ResultSet rs = (ResultSet) executeReturnQuery(Queries.GET_N_LAST_TRENINGSOKT(Integer.parseInt(n)));
			String streng ="";
			while(rs.next()) {
				streng += "Øktid: " + rs.getString("OektID") + " Datotidspunkt: " + rs.getString("DatoTidspunkt") + " Varighet(min): " 
			+ rs.getString("Varighetminutter") + " Informasjon: " + rs.getString("Informasjon") + " Form: " + rs.getString("Form") + 
			" Prestasjon: " + rs.getString("Prestasjon") + "\n"; 
			}
			return streng;
		}
		catch (Exception e) {
			return "Unsuccessful";
		}
	}

	public String getOvelseIDFromOvelseNavn(String ovelsenavn) {
		try {
			ResultSet rs = (ResultSet) executeReturnQuery(Queries.GET_OVELSEID_FROM_OVELSENAVN(ovelsenavn));
			String streng ="";
			while(rs.next()) {
				streng += rs.getString("OvelseID");
			}
			return streng;
		} catch (Exception e) {
			e.printStackTrace();
			return "Unsuccessfull";
		}
	}
	

	public String findSammeOvelseKategori(String ovelseNavn) { //ALLE ØVELSER SOM ER I SAMME KATEGORI SOM OVELSENAVN
		try {
			ResultSet rs = (ResultSet) executeReturnQuery(Queries.GET_OVELSE_BY_KATEGORI(getKategoriFromNavn(ovelseNavn)));
			String streng ="";
			while(rs.next()) {
				streng += rs.getString("Navn");
			}
			return streng;
		} catch (Exception e) {
			return "Unsuccessfull";
		}
	}
	
	public String getKategoriFromNavn(String ovelseNavn) {
		try {
			ResultSet rs = (ResultSet) executeReturnQuery(Queries.GET_KATEGORI_FROM_OVELSE(ovelseNavn));
			String streng ="";
			while(rs.next()) {
				streng += rs.getString("Navn");
			}
			return streng;
		} catch (Exception e) {
			return "Unsuccessfull";
		}
	}


	//--------------------------------MAKE-METODER---------------------------------------

	public String ovelseIKategori(String ovelseNavn, String kategoriNavn) { //TILKNYTTER ØVELSE I EN KATEGORI
		try {
			return (String) executeInsertQuery(Queries.CONNECT_OVELSE_KATEGORI(Integer.parseInt(getOvelseIDFromOvelseNavn(ovelseNavn)), kategoriNavn));
		}
		catch (Exception e) {
			return "Unsuccesful";
		}
	}
	

	public String makeTreningOvelse(String OktID, String ovelseNavn) { //LEGGER TIL ØVELSE I ØKT
		try {
			return (String) executeInsertQuery(Queries.CONNECT_TRENINGSOKT_OVELSE(Integer.parseInt(OktID), 
					Integer.parseInt(getOvelseIDFromOvelseNavn(ovelseNavn))));
		}
		catch (Exception e) {
			e.printStackTrace();
			return "Unsuccesful";
		}
	}
}
	// --------------------------------QUERIES IN SQL-FORMAT TEXT CAN BE FOUND IN QUERIES.JAVA---------------//
	
	


