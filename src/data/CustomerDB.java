package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bus.Customer;

public class CustomerDB {
	
public static Customer Display() throws SQLException{
		   
		
	    Connection conn = ConnectionDB.ConnDB();
	    Customer cus = new Customer();
		String sqlQuery;
		System.out.println("\nCustomer list...\n");
	    sqlQuery = "select * from customer";
	    Statement myStatement = null;
	    ResultSet myResultSet = null;
	    myStatement = conn.createStatement();
	    myResultSet = myStatement.executeQuery(sqlQuery);
	    
	    while(myResultSet.next())
	    {
	    
	    	cus.setId(myResultSet.getInt(1));
	    	cus.setFn(myResultSet.getString(2));
	    	cus.setLn(myResultSet.getString(3));
	    	cus.setPass(myResultSet.getString(4));
	    	System.out.println("\nID:" + cus.getId() + " First Name:" + cus.getFn()+ " Last Name:" + cus.getLn() + " Password:" + cus.getPass());
	    }
	    
	    conn.close();
	   return cus;
	   
	}

public static void Add(Customer customer) throws SQLException{
	
	Connection conn = ConnectionDB.ConnDB();

    String sqlStmt = "insert into customer values (?,?,?,?)";
    PreparedStatement myPreparedStatement = null;
    myPreparedStatement = conn.prepareStatement(sqlStmt);
    myPreparedStatement.setInt(1, customer.getId()); //(coluna, variable)
    myPreparedStatement.setString(2, customer.getFn());
    myPreparedStatement.setString(3, customer.getLn());
    myPreparedStatement.setString(4, customer.getPass());
    myPreparedStatement.executeUpdate(); //insert, delete, update
    conn.commit();
    conn.close();
}

public static void Delete(Customer customer) throws SQLException{
	
	Connection conn = ConnectionDB.ConnDB();
	String sqlStmt = "delete from customer where c_id = " + customer.getId();
	PreparedStatement myPreparedStatement = null;
    myPreparedStatement = conn.prepareStatement(sqlStmt);  
    myPreparedStatement.executeUpdate(); 
    conn.commit();
    conn.close();
}

public static Customer Search(Customer customer) throws SQLException{

	
	Connection conn = ConnectionDB.ConnDB();
    Customer cus = new Customer();
	String sqlQuery;
	sqlQuery = "select * from customer where c_id = " + customer.getId();
	Statement myStatement = null;
    ResultSet myResultSet = null;
    myStatement = conn.createStatement();
    myResultSet = myStatement.executeQuery(sqlQuery);
    
    while(myResultSet.next())
    {
    
    	cus.setId(myResultSet.getInt(1));
    	cus.setFn(myResultSet.getString(2));
    	cus.setLn(myResultSet.getString(3));
    	cus.setPass(myResultSet.getString(4));
    	System.out.println("\nID:" + cus.getId() + " First Name:" + cus.getFn()+ " Last Name:" + cus.getLn() + " Password:" + cus.getPass());
    }
    
    conn.close();
    return cus;
	
}

public static Customer Login(Customer customer) throws SQLException{

	
	Connection conn = ConnectionDB.ConnDB();
    Customer cus = new Customer();
	String sqlQuery;
	sqlQuery = "select * from customer where c_id = " + customer.getId() + "and c_password = " + customer.getPass();
	Statement myStatement = null;
    ResultSet myResultSet = null;
    myStatement = conn.createStatement();
    myResultSet = myStatement.executeQuery(sqlQuery);
  
    if(myResultSet.next()){
    	
    	   	cus.setId(myResultSet.getInt(1));
        	cus.setFn(myResultSet.getString(2));
        	cus.setLn(myResultSet.getString(3));
        	cus.setPass(myResultSet.getString(4));
        	
        	System.out.println("\nID:" + cus.getId() + " First Name:" + cus.getFn()+ " Last Name:" + cus.getLn() + " Password:" + cus.getPass());
    }
    
       
    conn.close();
    return cus;
	
}

public static void Update(Customer customer) throws SQLException{
	
	Connection conn = ConnectionDB.ConnDB();
	String sqlStmt = "update customer set c_first='" + customer.getFn() + "' where c_id= "  + customer.getId();
	PreparedStatement myPreparedStatement = null;
    myPreparedStatement = conn.prepareStatement(sqlStmt);  
    myPreparedStatement.executeUpdate(); 
    conn.commit();
    conn.close();
}


}
