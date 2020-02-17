package bus;

import java.sql.SQLException;

import data.ManagerDB;

public class Manager {
	private String login;
	private String password;
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Manager(String login, String password) {
		
		this.login = login;
		this.password = password;
	}
	public Manager() {
		this.login = "";
		this.password = "";
	}
	
	public String toString() {
		return "Manager [login=" + login + ", password=" + password + "]";
	}
	
	public static Manager Search(Manager manager) throws SQLException{
		return ManagerDB.Search(manager);
	}
}
