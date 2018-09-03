import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.ArrayList;


import org.junit.Test;


public class Main {
	
	private static final BigDecimal PAYMENT_SUM = new BigDecimal("50");
	@Test
	public void test() {
		
		Integer code1 = 50;
		Integer code2 = 110;
		Integer code3 = 1;
		Integer code4 = 2;
		Integer code5 = 3;
		Integer code6 = 101;
		
		ArrayList<Optional <Client>> clientList = new ArrayList<Optional<Client>>();
		
		Optional <Client> optClient1 = BankClientGateway.returnClientByID(code1, 1990);
		Optional <Client> optClient2 = BankClientGateway.returnClientByID(code2, 1985);
		Optional <Client> optClient3 = BankClientGateway.returnClientByID(code3, 1800);
		Optional <Client> optClient4 = BankClientGateway.returnClientByID(code4, 1975);
		Optional <Client> optClient5 = BankClientGateway.returnClientByID(code5, 1991);
		Optional <Client> optClient6 = BankClientGateway.returnClientByID(code6, 1990);
		clientList.add(optClient1);
		clientList.add(optClient2);
		clientList.add(optClient3);
		clientList.add(optClient4);
		clientList.add(optClient5);
		clientList.add(optClient6);
		
		
		for (Optional <Client> optClient : clientList) { 
			optClient.ifPresent(client -> client.getCreditCard().ifPresent(card -> makePayment(card)));
		}	
		
		clientList = BankClientGateway.getClients();
		printAccountBalances(clientList);
		
	}	
	
	public static void makePayment(BankCard card){
		card.makePayment(PAYMENT_SUM);
	}
	
	public static void printAccountBalances(ArrayList <Optional<Client>> list) {
		for (Optional<Client> client : list) {
			System.out.println("Client with id: "+ client.get().getID());
			client.get().getDebitCard().printBalance();
			client.ifPresent(realclient-> realclient.getCreditCard().ifPresent(card-> card.printBalance()));		
		}
	}
}
		
	


