package bus;

import java.sql.SQLException;

import data.CheckingDB;
import data.CreditDB;

public class Credit extends Account {
private int cr_number;
private float cr_limite;
private float cr_balance;

public int getCr_number() {
	return cr_number;
}
public void setCr_number(int cr_number) {
	this.cr_number = cr_number;
}

public float getCr_limite() {
	return cr_limite;
}
public void setCr_limite(float cr_limite) {
	this.cr_limite = cr_limite;
}
public float getCr_balance() {
	return cr_balance;
}
public void setCr_balance(float cr_balance) {
	this.cr_balance = cr_balance;
}
public Credit(int ac_number, int pin, Date ac_opendate, float balance,
		enumAccType ac_type, int ac_id, int cr_number, 
		float cr_limite, float cr_balance) {
	super(ac_number, pin, ac_opendate, balance, ac_type, ac_id);
	this.cr_number = cr_number;
	this.cr_limite = cr_limite;
	this.cr_balance = cr_balance;
}
public Credit(int ac_number, int pin, Date ac_opendate, float balance,
		enumAccType ac_type, int ac_id) {
	super(ac_number, pin, ac_opendate, balance, ac_type, ac_id);
	this.cr_number = cr_number;
	this.cr_limite = cr_limite;
	this.cr_balance = cr_balance;
}
public Credit() {
	super();
	this.cr_number = 0;
	this.cr_limite = 0;
	this.cr_balance = 0;
}
@Override
public String toString() {
	return "Credit [cr_number=" + cr_number + ", cr_limite=" + cr_limite + ", cr_balance=" + cr_balance + "]";
}

public static Credit Display() throws SQLException{
	return CreditDB.Display();
}

public static void Add(Credit credit) throws SQLException{
	CreditDB.Add(credit);
}

public static void Delete(Credit credit) throws SQLException{
	CreditDB.Add(credit);
}

public static void Update(Credit credit) throws SQLException{
	CreditDB.Add(credit);
}

public static Credit Search() throws SQLException{
	return CreditDB.Display();
}

public static Credit DisplayAll(int id) throws SQLException{
	
	return CreditDB.DisplayAll(id);
}

public static int GetIndex() throws SQLException{
	
	return CreditDB.GetIndex();
}

public static Account CusXAcc(int id) throws SQLException{
	
	return CreditDB.CusXAcc(id);
}
public static int CreByAcc(int acc) throws SQLException{
	return CreditDB.CreByAcc(acc);
}

}
