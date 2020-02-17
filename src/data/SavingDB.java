package data;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import bus.Account;
import bus.Checking;
import bus.Saving;
import bus.Date;
import bus.Saving;
import bus.enumAccType;

public class SavingDB {

public static Saving Display() throws SQLException{
		   
	    Connection conn = ConnectionDB.ConnDB();
	    Saving sav = new Saving();
		String sqlQuery;
		System.out.println("\nSaving list...\n");
	    sqlQuery = "select * from saving";
	    Statement myStatement = null;
	    ResultSet myResultSet = null;
	    myStatement = conn.createStatement();
	    myResultSet = myStatement.executeQuery(sqlQuery);
	    
	    while(myResultSet.next())
	    {
	    
	    	sav.setS_number(myResultSet.getInt(1));
	    	sav.setS_balance(myResultSet.getFloat(2));
	    	sav.setS_fee(myResultSet.getInt(3));
	    	System.out.println("\nSaving No:" + sav.getS_number()+ " Balance: " + sav.getS_balance()+ " Fee: " + sav.getS_fee());
	    }
	    
	    conn.close();
	   return sav;
	   
	}

public static void Add(Saving saving) throws SQLException{
	
	Connection conn = ConnectionDB.ConnDB();

    String sqlStmt = "insert into saving values (?,?,?,?)";
    PreparedStatement myPreparedStatement = null;
    myPreparedStatement = conn.prepareStatement(sqlStmt);
    myPreparedStatement.setInt(1, saving.getS_number()); //(coluna, variable)
    myPreparedStatement.setInt(2, saving.getAccNo());
    myPreparedStatement.setFloat(3, saving.getS_balance());
    myPreparedStatement.setInt(4, saving.getS_fee());
    

    
    myPreparedStatement.executeUpdate(); //insert, delete, update
    conn.commit();
    conn.close();
}

public static void Update(Saving sav) throws SQLException{

Connection conn = ConnectionDB.ConnDB();
String sqlStmt = "update saving Set s_balance = " +sav.getS_balance()+ " where s_number= "  + sav.getS_number();
PreparedStatement myPreparedStatement = null;
myPreparedStatement = conn.prepareStatement(sqlStmt);  
myPreparedStatement.executeUpdate(); 
conn.commit();
conn.close();
}

public static void Delete(Saving saving) throws SQLException{
	
	Connection conn = ConnectionDB.ConnDB();
	String sqlStmt = "delete from saving where s_number = " + saving.getS_number();
	PreparedStatement myPreparedStatement = null;
    myPreparedStatement = conn.prepareStatement(sqlStmt);  
    myPreparedStatement.executeUpdate(); 
    conn.commit();
    conn.close();
}

public static Saving Search(Saving saving) throws SQLException{

	
	Connection conn = ConnectionDB.ConnDB();
    Saving sav= new Saving();
	String sqlQuery;
	sqlQuery = "select * from saving where s_number = " + saving.getS_number();
	Statement myStatement = null;
    ResultSet myResultSet = null;
    myStatement = conn.createStatement();
    myResultSet = myStatement.executeQuery(sqlQuery);
    
    while(myResultSet.next())
    {
    
    	sav.setS_number(myResultSet.getInt(1));
    	sav.setS_balance(myResultSet.getFloat(2));
    	sav.setS_fee(myResultSet.getInt(3));
    	System.out.println("\nSaving No:" + sav.getS_number() + " Balance: " + sav.getS_balance()+ " Fee: " + sav.getS_fee());
    }
    
    conn.close();
    return sav;
	
}

public static Saving DisplayAll(int cid) throws SQLException{
	   
	
    Connection conn = ConnectionDB.ConnDB();
    Saving sav = new Saving();
	String sqlQuery;
	System.out.println("\nAccount Informations\n");
    sqlQuery = "select s.s_number, s.ac_number, s.s_balance from saving s,account ac where s.ac_number = ac.ac_number and ac.ac_id =" +cid ;
    Statement myStatement = null;
    ResultSet myResultSet = null;
    myStatement = conn.createStatement();
    myResultSet = myStatement.executeQuery(sqlQuery);
    
    while(myResultSet.next())
    {

    	sav.setS_number(myResultSet.getInt(1));
    	sav.setAccNo(myResultSet.getInt(2));
    	sav.setS_balance(myResultSet.getInt(3));
    	System.out.println("\nSaving Number:" + sav.getS_number() + "-"  + sav.getAccNo() + " Balance: " + sav.getS_balance());
    }
    
    conn.close();
   return sav;
   
}

public static int GetIndex() throws SQLException{

	int nextNumber=0;
	Connection conn = ConnectionDB.ConnDB();
  
	String sqlQuery;
	sqlQuery = "select max(s_number)as TempNumber from saving ";
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
   
    
    Saving sav= new Saving();
	String sqlQuery;
	sqlQuery = "select ac_number from account where ac_id =" + id;
	Statement myStatement = null;
    ResultSet myResultSet = null;
    myStatement = conn.createStatement();
    myResultSet = myStatement.executeQuery(sqlQuery);
    
    while(myResultSet.next())
    {   
    	
    	sav.setAccNo(myResultSet.getInt(1));
    	
    	  	
    	
    }
    conn.commit();
    conn.close();
    return sav;
	
}


public static int SavByAcc(int acc) throws SQLException{
	//lista??
	Connection conn = ConnectionDB.ConnDB();
   
    
    
	String sqlQuery;
	sqlQuery = "select s_number from saving where ac_number = " + acc;
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



public static Float TotalDeposit(int acc) throws SQLException{
	//lista??
	Connection conn = ConnectionDB.ConnDB();
    
    float total=0;
    
	String sqlQuery;
	sqlQuery = "select sum(amount) from transation where t_type = 'Deposit' and sa_number="+acc;
	Statement myStatement = null;
    ResultSet myResultSet = null;
    myStatement = conn.createStatement();
    myResultSet = myStatement.executeQuery(sqlQuery);
    
    while(myResultSet.next())
    {   
    	
    	total =myResultSet.getInt(1);
    	
    	  	
    	
    }
    conn.commit();
    conn.close();
    return total;
	
}

public static Float Totalwithdrawal(int acc) throws SQLException{
	//lista??
	Connection conn = ConnectionDB.ConnDB();
    
    float total=0;
    
	String sqlQuery;
	sqlQuery = "select sum(amount) from transation where t_type = 'Withdraw' and sa_number="+acc;
	Statement myStatement = null;
    ResultSet myResultSet = null;
    myStatement = conn.createStatement();
    myResultSet = myStatement.executeQuery(sqlQuery);
    
    while(myResultSet.next())
    {   
    	
    	total =myResultSet.getInt(1);
    	
    	  	
    	
    }
    conn.commit();
    conn.close();
    return total;
	
}


}
