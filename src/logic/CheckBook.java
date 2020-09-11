package logic;

public class CheckBook {

	private String numberFrom;
	private String numberTo;
	
	public CheckBook(String numberFrom, String numberTo){
		this.numberFrom=numberFrom;
		this.numberTo=numberTo;
	}

	public String getNumberFrom() {
		return numberFrom;
	}

	public void setNumberFrom(String numberFrom) {
		this.numberFrom = numberFrom;
	}

	public String getNumberTo() {
		return numberTo;
	}

	public void setNumberTo(String numberTo) {
		this.numberTo = numberTo;
	}

	@Override
	public String toString() {
		return "Chequera\n Numero Inicial=" + numberFrom + "\n Numero Final=" + numberTo+".";
	}
	
	
}
