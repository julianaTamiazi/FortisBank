package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bus.*;
public class AccountDB {
	
public static Account Display() throws SQLException{
		   
		Connection conn = ConnectionDB.ConnDB();
	    Account acc = new Account();
	    Date dat = new Date();
	    String sqlQuery;
		System.out.println("\nAccount list...\n");
	    sqlQuery = "select * from account";
	    Statement myStatement = null;
	    ResultSet myResultSet = null;
	    myStatement = conn.createStatement();
	    myResultSet = myStatement.executeQuery(sqlQuery);
	    
	    while(myResultSet.next())
	    {
	    System.out.println(myResultSet.getInt(1));
	    	acc.setAccNo(Integer.valueOf(myResultSet.getInt(1)));
	    	acc.setPin(myResultSet.getInt(2));
	    	dat.setDay(myResultSet.getInt(1));
	    	dat.setMonth(myResultSet.getInt(2));
	    	dat.setYear(myResultSet.getInt(3));
	    	acc.setDate(dat);
	    	acc.setBalance(myResultSet.getInt(4));
        	acc.setType(enumAccType.valueOf(myResultSet.getString(5)));
	    	acc.setAc_id(myResultSet.getInt(6));
	    	System.out.println("\nAccount No: " + acc.getAccNo() + " Pin:" + acc.getPin() + " Open Date: " + acc.getDate() +
	    			" Balance: " + acc.getBalance()+ " Type: " + acc.getType() + " Client Id: " + acc.getAc_id());
	    }
	    
	    conn.close();
	   return acc;
	   
	}

public static Account DisplayAll(int id) throws SQLException{
	   
	
    Connection conn = ConnectionDB.ConnDB();
    Account acc = new Account();
   	String sqlQuery;
	System.out.println("\nAccout Informations\n");
    sqlQuery = "select balance from account where ac_id = " + id;
    Statement myStatement = null;
    ResultSet myResultSet = null;
    myStatement = conn.createStatement();
    myResultSet = myStatement.executeQuery(sqlQuery);
    
    while(myResultSet.next())
    {
    
    	acc.setBalance(myResultSet.getInt(1));
    	System.out.println("\nTotal Balance: " + acc.getBalance());
    }
    
    conn.close();
   return acc;
   
}

}
