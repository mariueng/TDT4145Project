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
		System.out.println("Kom hit hvertfall");
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
	
	public String addFastMontert(String Navn, String kg, String sett, String apparatID) {
		try {
			return executeInsertQuery(Queries.INSERT_FASTMONTERT_OVELSE(Navn,Double.parseDouble(kg), Integer.parseInt(sett), Integer.parseInt(apparatID)));
		}
		catch (Exception e) {
			return "Unsuccessful";
		}
	}
	
	public String addTreningsOkt(String datotid, String varighetMin, String info, String form, String prestasjon) {
		try {
			return executeInsertQuery(Queries.INSERT_TRENINGSOKT(convertStringToTimestamp(datotid),  Integer.parseInt(varighetMin), info, 
					Integer.parseInt(form), Integer.parseInt(prestasjon)));
		}
		catch (Exception e) {
			return "Unsuccesfull";
		}
	}
	
	//-------------------------------SEARCH-METODER---------------------------------------
	
	
	public String getApparat(String Navn) {
		try {
			return executeReturnQuery(Queries.GET_APPARAT_BY_NAME(Navn));
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

	

//--------------------HELP-METHODS--------------------------

public static Timestamp convertStringToTimestamp(String str_date) {
    try {
      DateFormat formatter;
      formatter = new SimpleDateFormat("dd/MM/yyyy");
      Date date = (Date) formatter.parse(str_date);
      Timestamp timeStampDate = new Timestamp(date.getTime());

      return timeStampDate;
    } catch (ParseException e) {
      System.out.println("Exception :" + e);
      return null;
    }
  }
}
	
	// --------------------------------QUERIES IN SQL-FORMAT TEXT CAN BE FOUND IN QUERIES.JAVA---------------//
	
	


