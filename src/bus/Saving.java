package bus;

import java.sql.SQLException;

import data.CheckingDB;
import data.SavingDB;

public class Saving extends Account{
	private int s_number;
	private float s_balance;
	private int s_fee;
	
	public int getS_number() {
		return s_number;
	}
	public void setS_number(int s_number) {
		this.s_number = s_number;
	}

	public float getS_balance() {
		return s_balance;
	}
	public void setS_balance(float s_balance) {
		this.s_balance = s_balance;
	}
	public int getS_fee() {
		return s_fee;
	}
	public void setS_fee(int s_fee) {
		this.s_fee = s_fee;
	}
	public Saving(int ac_number, int pin, Date ac_opendate, float balance,
			enumAccType ac_type, int ac_id, int s_number,
			int s_balance, int s_fee) {
		super(ac_number, pin, ac_opendate, balance, ac_type, ac_id);
		this.s_number = s_number;
		this.s_balance = s_balance;
		this.s_fee = s_fee;
	}
	public Saving(int ac_number, int pin, Date ac_opendate, float balance,
			enumAccType ac_type, int ac_id) {
		super(ac_number, pin, ac_opendate, balance, ac_type, ac_id);
		this.s_number = s_number;
		this.s_balance = s_balance;
		this.s_fee = s_fee;
	}
	public Saving() {
		super();
		this.s_number = 0;
		this.s_balance = 0;
		this.s_fee = 0;
	}
	
	public String toString() {
		return "Saving [s_number=" + s_number + " , s_balance=" + s_balance + ", s_fee=" + s_fee + "]";
	}
	
	public static Saving Display() throws SQLException{
		
		return SavingDB.Display();
	}

	public static void Add(Saving saving) throws SQLException{
		SavingDB.Add(saving);	}

	public static void Delete(Saving saving) throws SQLException{
		SavingDB.Delete(saving);
	}

	public static Saving Search(Saving saving) throws SQLException{
		return SavingDB.Search(saving);
	}

public static Saving DisplayAll(int id) throws SQLException{
		
		return SavingDB.DisplayAll(id);
	}
public static int GetIndex() throws SQLException{
	return SavingDB.GetIndex();
}
public static Account CusXAcc(int id) throws SQLException{
	return SavingDB.CusXAcc(id);
}

public static void Update(Saving sav) throws SQLException{
	SavingDB.Update(sav);
}

public static int SavByAcc(int acc) throws SQLException{
	return SavingDB.SavByAcc(acc);
}
}
