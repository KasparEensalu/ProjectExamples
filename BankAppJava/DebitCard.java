import java.math.BigDecimal;


public class DebitCard extends BankCard{
	public static final BigDecimal LIMIT = new BigDecimal("0");
	
	public void makePayment(BigDecimal sum) {
		if (checkBalance(sum)) {
			balance = balance.subtract(sum);
			System.out.println("Debit Card payment of :" + sum.toEngineeringString() + "$");
		} else {
			System.out.println("Could not make payment. Not enough money.");
		}
	}
	
	public DebitCard(){
		BigDecimal balance = new BigDecimal("1000");
		this.balance = balance;
	}
	
	protected boolean checkBalance(BigDecimal sum) {
		int comparator = (this.getBalance().subtract(sum)).compareTo(LIMIT);
		if (comparator == 1 || comparator == 0) {
			return true;
		} else {
			return false;
		}
	}
}
