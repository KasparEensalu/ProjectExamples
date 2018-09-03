import java.util.Optional;


public class Client {
	private Integer idCode;
	private BankCard creditCard;
	private BankCard debitCard;
	private int yearOfBirth;
	
	public Client (Integer code, int year){
		idCode = code;
		yearOfBirth = year;
		
	}
	
	
	public Integer getID(){
		return idCode;
	}
	
	public void setCreditCard(BankCard card) {
		creditCard = card;
	}
	
	public BankCard getCreditCardUnsafe() {
		return creditCard;
	}
	
	public Optional <BankCard> getCreditCard() {
		return Optional.ofNullable(creditCard);
	}
	
	public void setDebitCard(BankCard card) {
		debitCard = card;
	}
	
	public BankCard getDebitCard(){
		return debitCard;
	}
	
	public Client(Integer code) {
		idCode = code;		
	}
}
