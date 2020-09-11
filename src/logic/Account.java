package logic;

import java.util.ArrayList;
import java.util.Calendar;

public class Account implements ActionsAcount {
	
	protected String number;
	protected double residue;
	protected Calendar dateCreation;
	protected ETypeAccount type;
	protected ArrayList<Reports> reports;
	
	public Account(){
		
	}

	public Account(String number, double residue, Calendar dateCreation,ETypeAccount type) {
		this.number = number;
		this.residue = residue;
		this.dateCreation = dateCreation;
		this.type=type;
		reports=new ArrayList<>();
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public double getResidue() {
		return residue;
	}

	

	public Calendar getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	@Override
	public boolean retirement(double residue)throws Exception {
		addNewReportRetirement(residue,0);
		this.residue-=residue;
		
		return true;
	}
	
	@Override
	public void deposit(double residue)throws Exception {
		addNewReportDeposit(0,residue);
		this.residue+=residue;
	}
	
	
	public ETypeAccount getType() {
		return type;
	}

	public void setType(ETypeAccount type) {
		this.type = type;
	}

 
	public ArrayList<Reports> getReports() {
		return reports;
	}
	//double residue,double retiro,double deposit
	
	public void addNewReportDeposit(double retiro,double deposit){
		Reports reports=new Reports();
		reports.setRetiro(retiro);
		reports.setDeposit(deposit);
		reports.reportDeposit(this);
		this.reports.add(reports);
	}
	public void addNewReportRetirement(double retiro,double deposit){
		Reports reports=new Reports();
		reports.setRetiro(retiro);
		reports.setDeposit(deposit);
		reports.reportRetirement(this);
		this.reports.add(reports);
	}
	

	@Override
	public String toString() {
		return "El numero de la cuenta es= " + number + "\n El saldo es= " + residue + "\n Creada en la fecha= \t"+dateCreation.getTime()+ "]";
	}
	
}
