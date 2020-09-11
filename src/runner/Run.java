package runner;

import java.util.Calendar;

import javax.swing.JOptionPane;

import Exception.ExMinResidue;
import Exception.ExOverdraft;
import Exception.ExValueMin;
import Exception.ExWhiteInput;
import logic.CurrentAccount;
import logic.ETypeAccount;
import logic.ManagementAccount;

public class Run { 
	ManagementAccount mc=new ManagementAccount();
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		new Run().menu();	
	}
	private void menu() throws Exception {
		String[] opciones=new String[]{"1.Crear Cuenta","2.Consultar Cuenta","3.Eliminar Cuenta","4.Depositar","5.Retirar","6.Reportes de Cuentas","7.Añadir cheque","8.Salir"};
		try{
		while(true){
			String opcion=(String) JOptionPane.showInputDialog(null,"Seleccione una opción","OPCIONES",JOptionPane.INFORMATION_MESSAGE,null,opciones, opciones[0]);
			switch(opcion){
			case"1.Crear Cuenta":addAccount();
				break;
			case"2.Consultar Cuenta":consultAccount();
				break;
			case"3.Eliminar Cuenta":deleteAccount();
				break;
			case"4.Depositar":depositeAccount();
				break;
			case"5.Retirar":retirement();
				break;
			case"6.Reportes de Cuentas":reportAccount();
				break;
			case"7.Añadir cheque":addChekBook();
				break;
			case"8.Salir":exit();
				break;
				default:
					exit();
			}
		}
		}catch(ExOverdraft error){
			JOptionPane.showMessageDialog(null,error.getMessage());
			menu();
		}catch(ExMinResidue error){
			JOptionPane.showMessageDialog(null,error.getMessage());
			menu();
		}catch(ExWhiteInput error){
			JOptionPane.showMessageDialog(null,error.getMessage());
			menu();
		}catch(NumberFormatException error){
			JOptionPane.showMessageDialog(null,"Por favor digite un valor númerico válido");
			menu();
		}catch(ExValueMin error){
			JOptionPane.showMessageDialog(null,error.getMessage());
			menu();
		}catch(NullPointerException error){
			exit();
		}
		
	}
	
	private void addAccount() throws  Exception{
		String[] opciones=new String[]{"1.Cuenta Corriente","2.Cuenta Ahorros","3.Volver"};
		String opcion=(String) JOptionPane.showInputDialog(null,"Seleccione una opción","CUENTAS",JOptionPane.INFORMATION_MESSAGE,null,opciones, opciones[0]);
		switch(opcion){
		case"1.Cuenta Corriente":currentAccount();
			break;
		case"2.Cuenta Ahorros":saveAccount();
			break;
		case"3.Volver":
			break;
		}
	}
	
	private void consultAccount() throws  Exception{
		String number=JOptionPane.showInputDialog(null, "Por favor digite el número de la Cuenta que quiere buscar");
		if(mc.findAccount(number)==null){
			JOptionPane.showMessageDialog(null, "No hay ninguna cuenta con el Número "+number,"ERROR",JOptionPane.ERROR_MESSAGE);
	}else{
		if(mc.findAccount(number).getType().equals(ETypeAccount.CURRENT)){
			 CurrentAccount cc=(CurrentAccount) mc.findAccount(number);
			JOptionPane.showMessageDialog(null,mc.findAccount(number));
			if(cc.getCheckBooks().size()>0){
				JOptionPane.showMessageDialog(null,cc.getCheckBooks());
			}
			
		}else{
			JOptionPane.showMessageDialog(null,mc.findAccount(number));
		}
			
	}
		
	}
	private void deleteAccount() throws Exception{
		String number=JOptionPane.showInputDialog(null, "Por favor digite el número de la Cuenta que quiere buscar");
		if(mc.findAccount(number)==null){
			JOptionPane.showMessageDialog(null, "La cuenta "+number+" no existe","ERROR",JOptionPane.ERROR_MESSAGE);
			
		}else{
			mc.deleteAccount(mc.findAccount(mc.findAccount(number)));
			JOptionPane.showMessageDialog(null, "La cuenta ha sido borrada");
		}
		
	}
	private void retirement() throws Exception{
		String number=JOptionPane.showInputDialog(null, "Por favor digite el número de la Cuenta");
		
		if(mc.findAccount(number)==null){
			JOptionPane.showMessageDialog(null, "La cuenta "+number+" no existe","ERROR",JOptionPane.ERROR_MESSAGE);
		}else{
			double residue=Double.parseDouble(JOptionPane.showInputDialog(null, "Digite el monto a retirar"));
			mc.retirement(number, residue);
			JOptionPane.showMessageDialog(null, "Se retiro");
		}
	
	}
	private void depositeAccount() throws  Exception{
		String number=JOptionPane.showInputDialog(null, "Por favor digite el número de la Cuenta");
		if(mc.findAccount(number)==null){
			JOptionPane.showMessageDialog(null, "La cuenta "+number+" no existe","ERROR",JOptionPane.ERROR_MESSAGE);
		}else{
			double residue=Double.parseDouble(JOptionPane.showInputDialog(null, "Digite el monto a retirar"));
			mc.deposity(number, residue);
			JOptionPane.showMessageDialog(null, "Se deposito");
		}
		
	}
	
	private void reportAccount() throws Exception{
		String number=JOptionPane.showInputDialog(null, "Por favor digite el número de la Cuenta");
		if(mc.findAccount(number)==null){
			JOptionPane.showMessageDialog(null, "La cuenta "+number+" no existe,Por lo tanto no se pueden mostrar los reportes","ERROR",JOptionPane.ERROR_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(null,mc.showReports(mc.findAccount(number)).toArray());
			
		}
	}
	
	
	private void addChekBook() throws Exception{
		String number=JOptionPane.showInputDialog(null, "Por favor digite el número de la Cuenta Corriente");
		if(mc.findAccount(number)==null){
			JOptionPane.showMessageDialog(null, "La cuenta "+number+" no existe","ERROR",JOptionPane.ERROR_MESSAGE);
		}else{
			if(mc.findAccount(number).getType().equals(ETypeAccount.CURRENT)){
				String numberFrom=JOptionPane.showInputDialog(null, "Por favor digite el número inicial del cheque");
				String numberTo=JOptionPane.showInputDialog(null, "Por favor digite el número final del cheque");
				mc.addCheckBook(number, numberFrom, numberTo);
			}else{
				JOptionPane.showMessageDialog(null, "La cuenta "+number+" No es una Cuenta Corriente","ERROR",JOptionPane.ERROR_MESSAGE);
			}
		}	
	}
	
	
	
	private void exit(){
		int aux=JOptionPane.showConfirmDialog(null, "¿Desea salir?","SALIR",JOptionPane.YES_NO_OPTION);
		if(aux==JOptionPane.YES_OPTION){
			JOptionPane.showMessageDialog(null, "Usted ha salido exitosamente","SALIR",JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
	}
	
	private void saveAccount() throws  Exception{
		String number=JOptionPane.showInputDialog(null, "Por favor digite el número de la Cuenta de Ahorros");
			if(mc.findAccount(number)==null){
				double residue=Double.parseDouble(JOptionPane.showInputDialog(null, "Por favor digite el Saldo Cuenta de Ahorros"));
				mc.addAccount(number, residue,ETypeAccount.DEPOSIT);
		}else{
			JOptionPane.showMessageDialog(null, "No puedes ingresar este número de cuenta por que ya existe","ERROR",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	private void currentAccount() throws Exception{
		String number=JOptionPane.showInputDialog(null, "Por favor digite el número de la Cuenta Corriente");
		if(mc.findAccount(number)==null){
			double residue=Double.parseDouble(JOptionPane.showInputDialog(null, "Por favor digite el saldo de la Cuenta Corriente"));
			mc.addAccount(number, residue,ETypeAccount.CURRENT);
	}else{
		JOptionPane.showMessageDialog(null, "No puedes ingresar este número de cuenta por que ya existe","ERROR",JOptionPane.ERROR_MESSAGE);
	}
	}
}
