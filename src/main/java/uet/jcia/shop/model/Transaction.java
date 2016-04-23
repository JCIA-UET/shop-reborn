package uet.jcia.shop.model;

import java.util.List;

public class Transaction {
	
	private ProductManager productManager = new ProductManager();
	private OrderManager orderManager = new OrderManager();
	private CategoryManager categoryManager = new CategoryManager();
	private AccountManager accountManager = new AccountManager();
	
	/**
	 * 
	 * @param wishedOrder with given fields: note, receivedAddress
	 * @param account
	 * @param products
	 * @return
	 */
	public int doBuy(String orderNote, String receivedAddress, Account account, List<Product> products) {
		int accountId = account.getId();
		// new customer
		if (accountId == 0) {
			accountId = accountManager.addAccount(account);
			account.setId(accountId);
		}
				
		return orderManager.makeOrder(orderNote, receivedAddress, account, products);
	}
	
	public boolean doCancel(int orderId){
		boolean result = orderManager.removeItemById(orderId);
		return result;
	}
}
