package logic;

import java.util.Calendar;

public class DepositAccount extends Account{
	
	private int minResdiue;
	
	public DepositAccount(){
	}

	public DepositAccount(String number, double residue, Calendar dateCreation,ETypeAccount type) {
		super(number, residue, dateCreation,type);
		this.minResdiue=20_000;

	}

	public int getMinResdiue() {
		return minResdiue;
	}

	public void setMinResdiue(int minResdiue) {
		this.minResdiue = minResdiue;
	}
	
	public double calculateInterest(){
		return residue-=residue*0.035;
	}
	
}