package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	private static Connection myConnection = null;

	public static Connection ConnDB() throws SQLException{
			
		String username = "juliana";
		String password = "tamiazi";
			String service = "localhost";
			String url = "jdbc:oracle:thin:";
			
			myConnection = DriverManager.getConnection(url+username+"/"+password+"@"+service);
		   	return myConnection;
			
			
		}

}
