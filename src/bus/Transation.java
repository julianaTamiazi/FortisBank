package bus;

import java.sql.SQLException;
import java.text.*;

import data.SavingDB;
import data.TransationDB;

public class Transation {
private int t_number;
private int sa_number;
private java.util.Date t_date;
private float amount;
private enumTypeTrans type;

public int getT_number() {
	return t_number;
}
public void setT_number(int t_number) {
	this.t_number = t_number;
}

public int getSa_number() {
	return sa_number;
}
public void setSa_number(int sa_number) {
	this.sa_number = sa_number;
}
public java.util.Date getT_date() {
	return t_date;
}
public void setT_date(java.util.Date t_date) {
	this.t_date = t_date;
}
public float getAmount() {
	return amount;
}
public void setAmount(float amount) {
	this.amount = amount;
}
public enumTypeTrans getType() {
	return type;
}
public void setType(enumTypeTrans type) {
	this.type = type;
}

public Transation(int t_number, int sa_number, java.util.Date t_date, float amount, enumTypeTrans type ) {
	
	this.t_number = t_number;
	this.sa_number = sa_number;
	this.t_date = t_date;
	this.amount = amount;
	this.type = type;
	
}
public Transation() {
	
	this.t_number = 0;
	this.sa_number = 0;
	this.t_date = null;
	this.amount = 0;
	this.type = enumTypeTrans.Undefined;
	
}

public static int GetIndex() throws SQLException{
	return TransationDB.GetIndex();
}
@Override
public String toString() {
	return "Transation [t_number=" + t_number + ", sa_number=" + sa_number
			+ ", t_date=" + t_date + ", amount=" + amount + ", type=" + type + "]";
}

public static Transation Display() throws SQLException{
	return TransationDB.Display();
}

public static void Add(Transation tra) throws SQLException{
	TransationDB.Add(tra);
}

public static Float TotalDeposit(int acc) throws SQLException{
	return TransationDB.TotalDeposit(acc);
}

public static Float Totalwithdrawal(int acc) throws SQLException{
	return TransationDB.Totalwithdrawal(acc);
}

}
