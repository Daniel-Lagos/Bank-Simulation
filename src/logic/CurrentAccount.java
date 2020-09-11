package logic;

import java.util.ArrayList;
import java.util.Calendar;

import org.omg.CORBA.RepositoryIdHelper;

public class CurrentAccount extends Account{
	
	private double overdraft;
	private ArrayList<CheckBook> checkBooks;
	
	public CurrentAccount(){
		
	}
	
	public CurrentAccount(String number, double residue, Calendar dateCreation,ETypeAccount type) {
		super(number, residue, dateCreation,type);
		this.overdraft=-200_000;
		checkBooks=new ArrayList<>(); 
	}
	
	public void addCheckBook(String numberFrom, String numberTo){
		checkBooks.add(new CheckBook(numberFrom, numberTo));
	}

	public double getOverdraft() {
		return overdraft;
	}

	public void setOverdraft(double overdraft) {
		this.overdraft = overdraft;
	}

	public ArrayList<CheckBook> getCheckBooks() {
		return (ArrayList<CheckBook>) checkBooks.clone();
	}
	
	
}	
	

