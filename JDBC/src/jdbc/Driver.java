package jdbc;
import java.sql.*;

public class Driver {
		
	private Connection myConn;
	private Statement myStmt;
	private ResultSet myRs;
	
	public Driver() throws SQLException {
		myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prosjekt?autoReconnect=true&useSSL=false", "root", "");
		myStmt = myConn.createStatement();
	}
	
	public String executeReturnQuery(String query){
		try {
			return ""+  this.myStmt.executeQuery(query);
		}
		catch (Exception err) {
			return "Unsuccesful";
		}
	}

	public String executeUpdateQuery(String query) {
		try {
			this.myStmt.executeUpdate(query);
		return "Successful";
		}
		catch (Exception e) {
			return "Unsuccesful";
		}
		
	}
	public String executeInsertQuery(String query){
		try {
			this.myStmt.executeQuery(query);
			return "Successful";
		}
		catch (Exception e) {
			return "Unsuccesful";
		}
		
	}
	
	
	//-----------------------INSERT-METODER--------------------------------------
	
	
	public String addApparat(String Navn, String Beskrivelse) {
		try {
			return executeInsertQuery(Queries.INSERT_APPARAT(Navn, Beskrivelse));
		}
		catch (Exception e) {
			return "Unsuccessful";
		}
	}
	
	public String addFriOvelse(String Navn, String Beskrivelse) {
		try {
			return executeInsertQuery(Queries.INSERT_FRIOVELSE(Navn, Beskrivelse));
		}
		catch (Exception e) {
			return "Unsuccesful";
		}
	}
	
	public String addFastMontert(String Navn, Double kg, int sett, int apparatID) {
		try {
			return executeInsertQuery(Queries.INSERT_FASTMONTERT_OVELSE(Navn, kg, sett, apparatID));
		}
		catch (Exception e) {
			return "Unsuccessful";
		}
	}
	
	public String addTreningsOkt(Timestamp datotid, int varighetMin, String info, int form, int prestasjon) {
		try {
			return executeInsertQuery(Queries.INSERT_TRENINGSOKT(datotid, varighetMin, info, form, prestasjon));
		}
		catch (Exception e) {
			return "Unsuccesfull";
		}
	}
	
	//-------------------------------SEARCH-METODER---------------------------------------
	
	
	public String getApparat(int id) {
		try {
			return executeReturnQuery(Queries.GET_APPARAT_BY_ID(id));
		}
		catch (Exception e) {
			return "Unsuccessful";
		}
	}
	

	/*
	public void test() {
		try {
			this.myStmt.executeUpdate(Queries.INSERT_TRENINGSOKT(new Timestamp(117, 10,12,15,30,0,0), 40, "Fin økt", 4, 8));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/


	/*public static void main(String[] args) {
		
		try {
			Driver driver = new Driver();
			
			//Queries
			ResultSet myRs;
			myRs = driver.executeReturnQuery("select * from apparat");
			
			//Process result
			while(myRs.next()) {
				System.out.println("ApparatID: " + myRs.getString("ApparatID") + " Navn: " + myRs.getString("Navn") + 
				" Beskrivelse: " + myRs.getString("Beskrivelse"));
			}
			driver.test();
		}
		catch(Exception e) {
			e.printStackTrace();
		}*/

	}
	
	// --------------------------------QUERIES IN SQL-FORMAT TEXT CAN BE FOUND IN QUERIES.JAVA---------------//
	
	


