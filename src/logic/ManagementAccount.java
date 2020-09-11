package logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JOptionPane;

import Exception.ExMinResidue;
import Exception.ExOverdraft;
import Exception.ExValueMin;
import Exception.ExWhiteInput;

public class ManagementAccount {

	private ArrayList<Account> accounts;
	DepositAccount deposit=new DepositAccount();
	
	
	public ManagementAccount(){
		accounts=new ArrayList<>();
	}
	
	public void deposity(String number, double residue) throws Exception{
		validateValue(residue);
		validateInput(number);
		if(findAccount(number)==null){
		}else{
			Reports reportes=new Reports();
			reportes.setDeposit(residue);
			reportes.reportDeposit(findAccount(number));
			findAccount(number).deposit(residue);
		}
	}
	
	private void validateInput(String input)throws Exception{
		if(input.equals("")){
			throw new ExWhiteInput("Por favor ingresa un Dato");
		}
	}
	
	private void validateValue(double residue) throws Exception{
        if(residue<0) {
            throw new ExValueMin("No puedes consignar menos de 0 Pesos Colombianos");
        }
    }
	
	public int calcMonthInterest(DepositAccount deposit) {

        int year=Calendar.getInstance().get(Calendar.YEAR)-deposit.getDateCreation().get(Calendar.YEAR);
        int months=((year*12)+Calendar.getInstance().get(Calendar.MONTH))-deposit.getDateCreation().get(Calendar.MONTH);
        if(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)-deposit.getDateCreation().get(Calendar.DAY_OF_MONTH)<0) {
            months-=1;
        }
        return months;
    }
	
	public boolean retirement(String number, double residue) throws Exception{
        validateInput(number);
        if(findAccount(number)==null){
            return false;
        }else{
            if(findAccount(number).getType().equals(ETypeAccount.CURRENT)){
                CurrentAccount cc=(CurrentAccount) findAccount(number);
                if(cc.getResidue()-residue<=cc.getOverdraft()){
                     throw new ExOverdraft("No se puede retirar porque el valor a retirar es mayor al cupo del sobregiro que tiene en la cuenta"+cc.getOverdraft());
                }else{
                    findAccount(number).retirement(residue);
                }

            }else{
                DepositAccount ca= (DepositAccount) findAccount(number);
                if(findAccount(number).getResidue()-residue<ca.getMinResdiue()){
                     throw new ExMinResidue("No se puede retirar porque el valor a retirar es mayor que el saldo Minímo de la Cuenta "+ca.getMinResdiue());
                }else{
                	
                	if(calcMonthInterest(ca)>=0) {
                        Calendar calen=Calendar.getInstance();
                        calen.set(Calendar.YEAR,Calendar.getInstance().get(Calendar.YEAR));
                        calen.set(Calendar.MONTH,Calendar.getInstance().get(Calendar.MONTH));
                        calen.set(Calendar.DAY_OF_MONTH,Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                        ca.setDateCreation(calen);
                        findAccount(number).retirement(residue);
                        ca.calculateInterest();
                    }else {
                    	findAccount(number).retirement(residue);
                    	
                    }
                    
                }
            } 
        }
        return true;
    }
	public boolean deleteAccount(int i){
		if(i<0){
			return false;
		}else{
			accounts.remove(i);
		}
		return true;
	}
	
	public int findAccount(Account account){
		for(int i=0;i<accounts.size();i++){
			if(accounts.get(i).getNumber().equals(account.getNumber())){
				return i;
			}
		}
		return -1;
	}
	
	public Account findAccount(String number) throws Exception{
		validateInput(number);
		for (Account account : accounts) {
			if(account.getNumber().equals(number)){
				return account;
			}
		}
		return null;
	}
	
	public boolean addAccount(String number, double residue, ETypeAccount typeAccount) throws Exception{
		validateValue(residue);
		validateInput(number);
		Calendar fecha1=Calendar.getInstance();
		if(findAccount(number)==null){
			if(typeAccount==ETypeAccount.CURRENT){
				fecha1.set(Calendar.YEAR, 2019);
				fecha1.set(Calendar.DAY_OF_MONTH, 11);
				fecha1.set(Calendar.MONTH, 5);

				accounts.add(new CurrentAccount(number, residue,fecha1,ETypeAccount.CURRENT)); 
				return true;
			}else if(typeAccount==ETypeAccount.DEPOSIT){
				fecha1.set(Calendar.YEAR, 2019);
				fecha1.set(Calendar.DAY_OF_MONTH, 11);
				fecha1.set(Calendar.MONTH, 5);
				accounts.add(new DepositAccount(number, residue,fecha1,ETypeAccount.DEPOSIT)); 
				return true;
			}
		}
		return false;
	}

	public ArrayList<Account> getAccounts() {
		return (ArrayList<Account>) accounts.clone();
	}
	
	public boolean addCheckBook(String number, String numberFrom, String numberTo) throws Exception{
		validateInput(number);
		validateInput(numberFrom);
		validateInput(numberTo);
		if(findAccount(number)==null){
			return false;
		}else{
			if(findAccount(number).getType().equals(ETypeAccount.CURRENT)){
                CurrentAccount cc=(CurrentAccount) findAccount(number);
                cc.addCheckBook(numberFrom, numberTo);
			}
		}
		return true;
	}
	
	public ArrayList<Reports> showReports(Account account){
		
		return account.getReports();
	}
	
}
