package logic;

public class Reports {

	double retiro;
	double deposit;
	String report;
	
	public Reports(){
		this.retiro=0;
		this.deposit=0;
		this.report="";
		
		}
	public double getRetiro() {
		return retiro;
	}

	public void setRetiro(double retiro) {
		this.retiro = retiro;
	}

	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}
	
	public String reportRetirement(Account account){
		String message="Reporte:\n";
		message+="Valor Total= "+account.getResidue()+"\n";
		message+="Valor Retirado= " + retiro + "\n Valor Depositado= " + deposit + "\n";
		message+="Valor Total: "+(account.getResidue()-getRetiro());
		this.report=message;
		return message;
	}
	public String reportDeposit(Account account){
		String message="Reporte:\n";
		message+="Valor Total= "+account.getResidue()+"\n";
		message+="Valor Retirado= " + retiro + "\n Valor Depositado= " + deposit + "\n";
		message+="Valor Total: "+(account.getResidue()+getDeposit());
		this.report=message;
		return message;
	}

	@Override
	public String toString() {
		return report;
	}
	
	
	
}
