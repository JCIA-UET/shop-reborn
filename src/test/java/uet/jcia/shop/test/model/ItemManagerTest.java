package uet.jcia.shop.test.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uet.jcia.shop.model.*;


public class ItemManagerTest {

	public static void main(String[] args) {
//		CategoryManager cm = new CategoryManager();
		ProductManager pm = new ProductManager();
//		OrderManager om = new OrderManager();
//		
//		Category newItem = null;
//		Product newProduct = null;
//		Order newOrder = null;
//		OrderDetails od = null;
//		List<OrderDetails> list = new ArrayList<>();
//		
//		newItem = new Category("Dien thoai", "Dien thoai category");
//		System.out.println(cm.addItem(newItem));
//		newItem = new Category("May tinh", "May tinh category");
//		System.out.println(cm.addItem(newItem));
//		
//		newProduct = new Product("Iphone", 10, 1000, 7, "iphone des", "img");
//		int pId1 = pm.addItem(newProduct);
//		System.out.println(pId1);
//		
//		newProduct = new Product("Samsung", 10, 1000, 8, "samsung des", "img");
//		int pId2 = pm.addItem(newProduct);
//		System.out.println(pId2);
		
//		newOrder = new Order(1, "KTX NN", new Timestamp(new Date().getTime()), 2000, "note", OrderStatus.PENDING, null);
//		int orderId = om.addItem(newOrder);
//		System.out.println(orderId);
		
//		od = new OrderDetails(1,3, 1);
//		System.out.println(om.addOrderDetailToDB(od));
//		od = new OrderDetails(1, 4,1);
//		System.out.println(om.addOrderDetailToDB(od));
		
//		ItemManager categoryManager = new CategoryManager();
//		Item newCategory = new Category(4, "Phu Kien");
//		System.out.println(categoryManager.setItem(4, newCategory));

//		System.out.println(categoryManager.getItemById(4));
//		System.out.println(categoryManager.removeItemById(4));
//		System.out.println(categoryManager.getAllItems());

		
//		System.out.println(productManager.setItem(3, newProduct));
//		System.out.println(productManager.removeItemById(3));
//		System.out.println(productManager.getAllItems());
		
		System.out.println(pm.getAllProductByCategoryId(1));
	}

}
