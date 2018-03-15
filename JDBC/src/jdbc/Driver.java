package jdbc;
import java.sql.*;

public class Driver {
		
	private Connection myConn;
	private Statement myStmt;
	private ResultSet myRs;
	
	public Driver() throws SQLException {
		myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prosjekt?autoReconnect=true&useSSL=false", "root", "root");
		myStmt = myConn.createStatement();
	}
	
	public ResultSet executeQuery(String query) throws SQLException {
		return this.myStmt.executeQuery(query);
	}

	public static void main(String[] args) {
		
		try {
			Driver driver = new Driver();
			
			//Queries
			ResultSet myRs;
			myRs = driver.executeQuery("select * from apparat");
			
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
