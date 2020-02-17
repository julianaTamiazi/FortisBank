package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bus.Account;
import bus.Checking;
import bus.Credit;
import bus.Checking;
import bus.Checking;

public class CheckingDB {
	
	public static Checking Display() throws SQLException{
	Connection conn = ConnectionDB.ConnDB();
	 Checking ch = new Checking();
		String sqlQuery;
		System.out.println("\nChecking list...\n");
	 sqlQuery = "select * from checking";
	 Statement myStatement = null;
	 ResultSet myResultSet = null;
	 myStatement = conn.createStatement();
	 myResultSet = myStatement.executeQuery(sqlQuery);
	 
	 while(myResultSet.next())
	 {
	 
	 	ch.setCh_number(myResultSet.getInt(1));
	 	ch.setCh_balance(myResultSet.getFloat(2));
	    ch.setCh_fee(myResultSet.getString(3).charAt(0));
	 
	 	
	 	System.out.println("\nChecking Number:" + ch.getCh_number() 
	 			+ "Balance" +ch.getCh_balance()+ "Fee"+ch.getCh_fee());
	 }
	 
	 conn.close();
	return ch;

	}

	public static void Add(Checking ch) throws SQLException{

	Connection conn = ConnectionDB.ConnDB();

	String sqlStmt = "insert into checking values (?,?,?,?)";
	PreparedStatement myPreparedStatement = null;
	myPreparedStatement = conn.prepareStatement(sqlStmt);
	myPreparedStatement.setInt(1, ch.getCh_number()); //(coluna, variable)
	myPreparedStatement.setFloat(2, ch.getCh_balance());
	myPreparedStatement.setString(3, String.valueOf(ch.getCh_fee()));

	myPreparedStatement.executeUpdate(); //insert, delete, update
	conn.commit();
	conn.close();
	}

	public static Checking Search(Checking ch) throws SQLException{


	Connection conn = ConnectionDB.ConnDB();
	Checking che = new Checking();

	String sqlQuery;
	sqlQuery = "select * from checking where ch_number = " + ch.getCh_number();
	Statement myStatement = null;
	ResultSet myResultSet = null;
	myStatement = conn.createStatement();
	myResultSet = myStatement.executeQuery(sqlQuery);

	while(myResultSet.next())
	{  	che.setCh_number(myResultSet.getInt(1));
		che.setCh_balance(myResultSet.getFloat(2));
		che.setCh_fee(myResultSet.getString(3).charAt(0));
		
		System.out.println("\nChecking Number:" + ch.getCh_number() 
	 			+"Balance" +ch.getCh_balance()+ "Fee"+ch.getCh_fee());
	}

	conn.close();
	return che;

	}

	public static void Update(Checking che) throws SQLException{

		Connection conn = ConnectionDB.ConnDB();
		String sqlStmt = "update checking Set ch_balance = " +che.getCh_balance()+ " where ch_number= "  + che.getCh_number();
		PreparedStatement myPreparedStatement = null;
		myPreparedStatement = conn.prepareStatement(sqlStmt);  
		myPreparedStatement.executeUpdate(); 
		conn.commit();
		conn.close();
		}
	
	public static Checking DisplayAll(int cid) throws SQLException{
		   
		
	    Connection conn = ConnectionDB.ConnDB(); 
	    Checking che = new Checking();
		//Account acc = new Account();
		String sqlQuery;
		System.out.println("\nAccount Informations\n");
	    sqlQuery = "select ch.ch_number, ch.ac_number, ch.ch_balance, ac.ac_number,ac.ac_id from checking ch,account ac where ch.ac_number = ac.ac_number and ac.ac_id =" +cid ;
	    Statement myStatement = null;
	    ResultSet myResultSet = null;
	    myStatement = conn.createStatement();
	    myResultSet = myStatement.executeQuery(sqlQuery);
	    
	    while(myResultSet.next())
	    {
	    
	    	che.setCh_number(myResultSet.getInt(1));
	    	che.setAccNo(myResultSet.getInt(2));
	    	che.setCh_balance(myResultSet.getInt(3));
	    		System.out.println("\nChecking Number:" + che.getCh_number() + "-"  + che.getAccNo() + " Balance: " + che.getCh_balance());
	    }
	    
	    conn.close();
	   return che;
	   
	}
	
	
	public static Account CusXAcc(int id) throws SQLException{
		//lista??
		Connection conn = ConnectionDB.ConnDB();
	   
	    
	    Checking che= new Checking();
		String sqlQuery;
		sqlQuery = "select ac_number from account where ac_id =" + id;
		Statement myStatement = null;
	    ResultSet myResultSet = null;
	    myStatement = conn.createStatement();
	    myResultSet = myStatement.executeQuery(sqlQuery);
	    
	    while(myResultSet.next())
	    {   
	    	
	    	che.setAccNo(myResultSet.getInt(1));
	    	
	    	  	
	    	
	    }
	    conn.commit();
	    conn.close();
	    return che;
		
	}
	
	public static int CheByAcc(int acc) throws SQLException{
		//lista??
		Connection conn = ConnectionDB.ConnDB();
	   
	    
	    
		String sqlQuery;
		sqlQuery = "select ch_number from checking where ac_number = " + acc;
		Statement myStatement = null;
	    ResultSet myResultSet = null;
	    myStatement = conn.createStatement();
	    myResultSet = myStatement.executeQuery(sqlQuery);
	    
	    while(myResultSet.next())
	    {   
	    	
	    	acc=myResultSet.getInt(1);
	    	
	    	
	    }
	    conn.commit();
	    conn.close();
	    return acc;
		
	}
	
	

}
