package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



import bus.*;

public class TransationDB {
	
public static Transation Display() throws SQLException{
		   
		
	    Connection conn = ConnectionDB.ConnDB();
	    Transation tra = new Transation();
		String sqlQuery;
		System.out.println("\nTransaction list...\n");
	    sqlQuery = "select * from transation";
	    Statement myStatement = null;
	    ResultSet myResultSet = null;
	    myStatement = conn.createStatement();
	    myResultSet = myStatement.executeQuery(sqlQuery);
	       
	    
	    while(myResultSet.next())
	    { 
	    
	    	tra.setT_number(myResultSet.getInt(1));
	    	tra.setSa_number(myResultSet.getInt(2));
	    	//tra.setType(myResultSet.getInt(3));
	    	tra.setT_date(myResultSet.getDate(4));
	       	tra.setAmount(myResultSet.getFloat(5));
	       
	    	
	    	System.out.println("\nTransaction Number:" + tra.getT_number() + "SubAccount Number:" + tra.getSa_number()+ " Type:" + tra.getType()+ "Date"+tra.getT_date()
	    			+ "Amount" +tra.getAmount() );
	    }
	    
	    conn.close();
	   return tra;
	   
	}

public static void Add(Transation tra) throws SQLException{
	
	Connection conn = ConnectionDB.ConnDB();

    String sqlStmt = "insert into transation values (?,?,?,?,?)";
    PreparedStatement myPreparedStatement = null;
    myPreparedStatement = conn.prepareStatement(sqlStmt);
    myPreparedStatement.setInt(1, tra.getT_number()); //(coluna, variable)
    myPreparedStatement.setInt(2, tra.getSa_number());
    myPreparedStatement.setString(3, tra.getType().toString());
    
    java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
    myPreparedStatement.setDate(4, sqlDate);
    myPreparedStatement.setFloat(5, tra.getAmount());
    myPreparedStatement.executeUpdate(); //insert, delete, update
    conn.commit();
    conn.close();
}

public static void Delete(Transation tra) throws SQLException{

Connection conn = ConnectionDB.ConnDB();
String sqlStmt = "delete from transation where t_number = " + tra.getT_number();
PreparedStatement myPreparedStatement = null;
myPreparedStatement = conn.prepareStatement(sqlStmt);  
myPreparedStatement.executeUpdate(); 
conn.commit();
conn.close();
}

public static Transation Search(Transation tran) throws SQLException{


Connection conn = ConnectionDB.ConnDB();
Transation tra = new Transation();

String sqlQuery;
sqlQuery = "select * from transation where ac_number = " + tran.getT_number();
Statement myStatement = null;
ResultSet myResultSet = null;
myStatement = conn.createStatement();
myResultSet = myStatement.executeQuery(sqlQuery);

	while(myResultSet.next())
	{ 

		tra.setT_number(myResultSet.getInt(1));
		tra.setSa_number(myResultSet.getInt(2));
		//tra.setType(myResultSet.getString(2));
		tra.setT_date(myResultSet.getDate(4));
		tra.setAmount(myResultSet.getFloat(5));
   
	
		System.out.println("\nTransaction Number:" + tra.getT_number() + "SubAccount Number:" + tra.getSa_number()+ " Type:" + tra.getType()+ "Date"+tra.getT_date()
			+ "Amount" +tra.getAmount() );
	}

conn.close();
return tra;

}

public static void Update(Transation tra) throws SQLException{

Connection conn = ConnectionDB.ConnDB();
String sqlStmt = "update transation " +"Set t_number = "  + tra.getT_number() + "," + " t_type = '" + tra.getType()+ ","+" t_date = '" +tra.getT_date()
		+ "," + "amount = '" + tra.getAmount()+ "'" +" where t_number= "  + tra.getT_number();
PreparedStatement myPreparedStatement = null; 
myPreparedStatement = conn.prepareStatement(sqlStmt);  
myPreparedStatement.executeUpdate(); 
conn.commit();
conn.close();
}


public static int GetIndex() throws SQLException{

	int nextNumber=0;
	Connection conn = ConnectionDB.ConnDB();
  
	String sqlQuery;
	sqlQuery = "select max(t_number)as TempNumber from transation ";
	Statement myStatement = null;
    ResultSet myResultSet = null;
    myStatement = conn.createStatement();
    myResultSet = myStatement.executeQuery(sqlQuery);
    
    if(myResultSet.next())
    {
    	nextNumber = myResultSet.getInt(1)+1;
    	
    	
    }
    
    conn.close();
    return nextNumber;
	
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
