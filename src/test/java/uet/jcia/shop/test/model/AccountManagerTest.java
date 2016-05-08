package uet.jcia.shop.test.model;

import uet.jcia.shop.model.Account;
import uet.jcia.shop.model.AccountManager;
import uet.jcia.shop.model.AccountType;

public class AccountManagerTest {
	public static void main(String[] args) {
		AccountManager test = new AccountManager();
//		
//		Account acc = new Account("cuong", "cuong", "oc", "1111", "Hanoi", "Nghe An", AccountType.EMPLOYEE);
//		System.out.println(test.addAccount(acc));
		
		System.out.println(test.countItems());
	}
}
