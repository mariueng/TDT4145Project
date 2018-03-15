package jdbc;
import java.sql.*;

public class Driver {

	public static void main(String[] args) {
		
		try {
			// Connection
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prosjekt?autoReconnect=true&useSSL=false", "root", "root");
			
			//Statement
			Statement myStmt = myConn.createStatement();
			
			//Queries
			ResultSet myRs;
			myRs = myStmt.executeQuery("select * from apparat");
			
			//Process result
			while(myRs.next()) {
				System.out.println("ApparatID: " + myRs.getString("ApparatID") + " Navn: " + myRs.getString("Navn") + 
				" Beskrivelse: " + myRs.getString("Beskrivelse"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	// --------------------------------QUERIES-----------------------------------------------------------//
	
	

}
