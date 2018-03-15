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
	
	public ResultSet executeQuery(String query) throws SQLException {
		return this.myStmt.executeQuery(query);
	}
	
	public void test() {
		try {
			this.myStmt.executeUpdate(Queries.INSERT_TRENINGSOKT(new Timestamp(117, 10,12,15,30,0,0), 40, "Fin økt", 4, 8));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			driver.test();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	// --------------------------------QUERIES-----------------------------------------------------------//
	
	

}
