package uet.jcia.shop.test.model;

import uet.jcia.shop.model.*;

import java.util.*;

/**
 * @author dinht_000
 *
 */
public class OrderManagerTest {

	public static void main(String[] args) {
		
		// Date format
		/*
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String sDate = sdf.format(date);
		System.out.println(sDate);
		*/
		
		
		
		//System.out.println("Get order by id:");
		//orderManager.getItemById(1);
		//System.out.println("Get All Order:");
		//orderManager.getAllItems();
		//System.out.println("Get details:");
		//System.out.println(orderManager.GetDetailsById(1));
		//System.out.println("Remove Order");
		//orderManager.removeItemById(1);
		//System.out.println("Get All Order:");
		//orderManager.getAllItems();
		
		//newOrder = new Order(1);
		//orderManager.addItem(newOrder);
		//System.out.println(orderManager.calDailyRevenue(new Date()));
		//System.out.println(orderManager.calMonthRevenue(4));
		//System.out.println(orderManager.calTotalRevenue());
		//orderManager.getTopSellingProduct(10);
		
//		Order order = new Order();
		OrderManager orM=new OrderManager();
//		Account acc=new Account(null, null, "vu", null, null, null, null);
//		Product product=new Product("toy", 1, 0, 0, null, null);
//		List<Product> e=new ArrayList<>();
//		e.add(product);
//		
//		int re=orM.makeOrder(null, null, acc, e);
//		System.out.println(re);
		
		System.out.println(orM.getTopCustomers(3));
		
		
	}
}
