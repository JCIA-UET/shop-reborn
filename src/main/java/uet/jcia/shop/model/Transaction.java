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
	
	public boolean changeQttOfProduct(List<Product> shoppingCart, int id, int quantity) {
		if(shoppingCart == null) return false;
		
		for (Product p : shoppingCart) {
			if(p.getId() == id) {
				int index = shoppingCart.indexOf(p);
				Product tempP = p;
				tempP.setQuantity(quantity);
				System.out.println(tempP.getQuantity());
				shoppingCart.set(index, tempP);
				return true;
			}
			else continue;
		}
		return false;
	}
	
	public Item findItemById(List<Product> shoppingCart, int id) {
		if(shoppingCart == null) return null;
		for (Product p : shoppingCart) {
			if(p.getId() == id)
				return p;
			else continue;
		}
		return null;
	}
	
	public boolean removeItemById(List<Product> shoppingCart, int id) {
		if(shoppingCart == null) return false;
		boolean flag = false;
		
		for (Product p : shoppingCart) {
			if(p.getId() == id) {
				shoppingCart.remove(p);
				flag = true;
				break;
			}
			else {
				flag = false;
				continue;
			}
		}
		return flag;
	}
	
	public double getTotalCash(List<Product> shoppingCart) {
		if(shoppingCart == null) return 0;
		
		double total = 0;
		for(Product p : shoppingCart) {
			total += (double) (p.getQuantity() * p.getPrice());
		}
		
		return total;
	}
}
