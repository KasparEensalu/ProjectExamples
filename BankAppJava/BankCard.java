import java.math.BigDecimal;


public class BankCard {
	
	protected BigDecimal balance;

	
	public BigDecimal getBalance() {
		return balance;
	}
	
	public void printBalance() {
		System.out.println("Debit card balance of: "+this.getBalance()+" evros");
	}

	
	public void makePayment(BigDecimal sum) {}
	
	
	public BankCard(){
		BigDecimal balance = new BigDecimal("1000");
		this.balance = balance;
	}
	
	
}
