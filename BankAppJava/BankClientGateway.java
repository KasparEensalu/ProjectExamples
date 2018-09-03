import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class BankClientGateway {
	private static Map<Integer, Optional<Client>> clientsByCode = new HashMap<>();
	private static ArrayList<Optional<Client>> clientList = new ArrayList<Optional<Client>>(); 
	private static final BigDecimal BONUS = new BigDecimal("1100");
	
	public static Optional<Client> returnClientByID(Integer code, int yearOfBirth) {
		if (clientsByCode.containsKey(code)) {
			return clientsByCode.get(code);
		} else {
			Client client;
			Optional <Client> optClient = Optional.empty();
			if(2015-yearOfBirth > 60){
				
			} else {
				client = new Client(code, yearOfBirth);
				optClient = Optional.ofNullable(client);
				clientList.add(optClient);
				BankCard debitCard = new DebitCard();
				client.setDebitCard(debitCard);
				if ( (clientsByCode.size() % 3) == 0) {
					addBonusMoney(client);
				}
				if (code < 100) {
					BankCard creditCard = new CreditCard();
					client.setCreditCard(creditCard);
				}
				clientsByCode.put(code, optClient);
			}
			clientsByCode.put(code, optClient);
			return optClient;
		}
	}
	
	public static void addBonusMoney(Client client) {
		client.getDebitCard().balance = BONUS;
	}
	
	public static ArrayList<Optional <Client>> getClients() {
		return clientList;
	}
}
