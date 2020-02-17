package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import bus.Manager;

public class ManagerDB {

	public static Manager Search(Manager manager) throws SQLException{

		
		Connection conn = ConnectionDB.ConnDB();
	    Manager man = new Manager();
		String sqlQuery;
		sqlQuery = "select * from manager where login = " + manager.getLogin();
		Statement myStatement = null;
	    ResultSet myResultSet = null;
	    myStatement = conn.createStatement();
	    myResultSet = myStatement.executeQuery(sqlQuery);
	    
	    while(myResultSet.next())
	    {
	    	man.setLogin(myResultSet.getString(1));
	    }
	    
	    conn.close();
	    return man;
		
	}

}
