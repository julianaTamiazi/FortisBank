package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bus.*;

public class CreditDB {
	
public static Credit Display() throws SQLException{
		   
		
	    Connection conn = ConnectionDB.ConnDB();
	    Credit cre = new Credit();
		String sqlQuery;
		System.out.println("\nCredit list...\n");
	    sqlQuery = "select * from credit";
	    Statement myStatement = null;
	    ResultSet myResultSet = null;
	    myStatement = conn.createStatement();
	    myResultSet = myStatement.executeQuery(sqlQuery);
	    
	    while(myResultSet.next())
	    {
	    
	    	cre.setCr_number(myResultSet.getInt(1));
	    	cre.setCr_limite(myResultSet.getFloat(2));
	    	cre.setCr_balance(myResultSet.getFloat(3));
	    	System.out.println("\nCredit Number:" + cre.getCr_number() 
	     			+ " Limite:" + 
	    			cre.getCr_limite()+"Balance"+cre.getCr_balance());
	    }
	    
	    conn.close();
	   return cre;
	   
	}

public static void Add(Credit credit) throws SQLException{
	
	Connection conn = ConnectionDB.ConnDB();

    String sqlStmt = "insert into credit values (?,?,?,?)";
    PreparedStatement myPreparedStatement = null;
    myPreparedStatement = conn.prepareStatement(sqlStmt);
    myPreparedStatement.setInt(1, credit.getCr_number()); //(coluna, variable)
    myPreparedStatement.setInt(2, credit.getAccNo()); 
    myPreparedStatement.setFloat(3, credit.getCr_balance());
    myPreparedStatement.setFloat(4, credit.getCr_limite());
   
    
    myPreparedStatement.executeUpdate(); //insert, delete, update
    conn.commit();
    conn.close();
}

public static void Delete(Credit credit) throws SQLException{
	
	Connection conn = ConnectionDB.ConnDB();
	String sqlStmt = "delete from credit where cr_number = " + credit.getCr_number();
	PreparedStatement myPreparedStatement = null;
    myPreparedStatement = conn.prepareStatement(sqlStmt);  
    myPreparedStatement.executeUpdate(); 
    conn.commit();
    conn.close();
}

public static Credit Search(Credit credit) throws SQLException{

	
	Connection conn = ConnectionDB.ConnDB();
    Credit cre = new Credit();
	String sqlQuery;
	sqlQuery = "select * from credit where cr_number = " + credit.getCr_number();
	Statement myStatement = null;
    ResultSet myResultSet = null;
    myStatement = conn.createStatement();
    myResultSet = myStatement.executeQuery(sqlQuery);
    
    while(myResultSet.next())
    {
    	cre.setCr_number(myResultSet.getInt(1));
    	cre.setCr_limite(myResultSet.getFloat(3));
    	cre.setCr_balance(myResultSet.getFloat(4));
    	System.out.println("\nCredit Number:" + cre.getCr_number() 
     			+ " Limite:" + 
    			cre.getCr_limite()+"Balance"+cre.getCr_balance());
    }
    
    conn.close();
    return cre;
	
}

public static void Update(Credit cre) throws SQLException{

Connection conn = ConnectionDB.ConnDB();
String sqlStmt = "update credit Set cr_balance = " +cre.getCr_balance()+ " where cr_number= "  + cre.getCr_number();
PreparedStatement myPreparedStatement = null;
myPreparedStatement = conn.prepareStatement(sqlStmt);  
myPreparedStatement.executeUpdate(); 
conn.commit();
conn.close();
}

public static Credit DisplayAll(int cid) throws SQLException{
	   
	
    Connection conn = ConnectionDB.ConnDB();
    Credit cre = new Credit();
	String sqlQuery;
	System.out.println("\nAccount Informations\n");
    sqlQuery = "select cr.cr_number, cr.ac_number, cr.cr_balance,ac.ac_number,ac.ac_id from credit cr,account ac where cr.ac_number = ac.ac_number and ac.ac_id =" +cid ;
    Statement myStatement = null;
    ResultSet myResultSet = null;
    myStatement = conn.createStatement();
    myResultSet = myStatement.executeQuery(sqlQuery);
    
    while(myResultSet.next())
    {
    

    	cre.setCr_number(myResultSet.getInt(1));
    	cre.setAccNo(myResultSet.getInt(2));
    	cre.setCr_balance(myResultSet.getInt(3));
    	System.out.println("\nCredit Number:" + cre.getCr_number() + " "  + cre.getAccNo() + "Balance: " + cre.getCr_balance());
    }
    
    conn.close();
   return cre;
   
}

public static int GetIndex() throws SQLException{

	int nextNumber=0;
	Connection conn = ConnectionDB.ConnDB();
  
	String sqlQuery;
	sqlQuery = "select max(cr_number)as TempNumber from credit ";
	Statement myStatement = null;
    ResultSet myResultSet = null;
    myStatement = conn.createStatement();
    myResultSet = myStatement.executeQuery(sqlQuery);
    
    if(myResultSet.next())
    {
    	nextNumber = myResultSet.getInt(1)+1;
    	
    	
    }
    conn.commit();
    conn.close();
    return nextNumber;
	
}

public static Account CusXAcc(int id) throws SQLException{
	//lista??
	Connection conn = ConnectionDB.ConnDB();
    Account acc= new Account();
    
    Credit cre= new Credit();
	String sqlQuery;
	sqlQuery = "select ac_number from account where ac_id =" + id;
	Statement myStatement = null;
    ResultSet myResultSet = null;
    myStatement = conn.createStatement();
    myResultSet = myStatement.executeQuery(sqlQuery);
    
    while(myResultSet.next())
    {   
    	
    	cre.setAccNo(myResultSet.getInt(1));
    	
    	  	
    	
    }
    conn.commit();
    conn.close();
    return cre;
	
}
public static int CreByAcc(int acc) throws SQLException{
	//lista??
	Connection conn = ConnectionDB.ConnDB();
   
    
    
	String sqlQuery;
	sqlQuery = "select cr_number from credit where ac_number = " + acc;
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
