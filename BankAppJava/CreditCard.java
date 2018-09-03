import java.math.BigDecimal;



public class CreditCard extends BankCard {
	
	public static final BigDecimal LIMIT = new BigDecimal("-500");

	public BigDecimal getBalance() {
		return balance;
	}
	
	public void printBalance() {
		System.out.println("Credit card balance of: "+this.getBalance()+"$");
	}

	@Override
	public void makePayment(BigDecimal sum) {
		if (checkBalance(sum)) {
			balance = balance.subtract(sum);
			System.out.println("Credit card payment of :" + sum.toEngineeringString() + "$");
		} else {
			System.out.println("Could not make payment. Credit limit reached.");
		}
	}
	
	public CreditCard(){
		BigDecimal balance = new BigDecimal("1000");
		this.balance = balance;
	}
	
	public CreditCard(BigDecimal sum){
		this.balance = sum;
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

