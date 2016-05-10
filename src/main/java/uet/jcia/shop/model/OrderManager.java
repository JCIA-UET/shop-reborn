package uet.jcia.shop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderManager implements ItemManager {
	private Connection conn = null;
	private ProductManager pm = new ProductManager();
	
	private final String GET_ORDER_BY_ID_QUERY  			= "SELECT * FROM `order` WHERE orderId=?";
	private final String GET_ORDERS_BY_CTMERID_QUERY		= "SELECT * FROM mydb.`order` WHERE accountId=?";
	private final String GET_ALL_ORDERS_QUERY   			= "SELECT * FROM `order`";
	private final String GET_DETAILS_BY_ORDER_ID_QUERY 		= "SELECT * FROM orderdetail WHERE orderId=?";
	private final String DELETE_ORDER_BY_ID_QUERY    		= "DELETE FROM `order` WHERE orderId=?";
	private final String ADD_NEW_ORDER_QUERY	 			= "INSERT INTO `order` (accountId, receivedAddress, date, total, note, status) VALUES(?,?,?,?,?,?)";
	private final String ADD_NEW_ORDERDETAIL_QUERY			= "INSERT INTO orderdetail (orderId, productId, quantity) VALUES (?,?,?)";
	private final String GET_TOP_SELLING_PRODUCT_QUERY 		= "SELECT product.productID, SUM(orderdetail.Quantity) AS Quantity FROM orderdetail INNER JOIN product ON product.productID = orderdetail.productID GROUP BY orderdetail.productID ORDER BY Quantity DESC LIMIT ?";
	private final String GET_REVENUE_IN_DAY_QUERY 			= "SELECT DATE(orderDate) AS Date, orderdetail.quantity, SUM(orderdetail.quantity * product.price) AS Revenue FROM orderdetail LEFT JOIN product ON product.productID = orderdetail.productID WHERE DATE(orderDate)=? GROUP BY orderId, orderdetail.productID";
	private final String GET_REVENUE_IN_MONTH_QUERY			= "SELECT MONTH(DATE(orderDate)) as Month, orderdetail.quantity, SUM(orderdetail.quantity * product.price) AS Revenue FROM orderdetail LEFT JOIN product ON product.productID = orderdetail.productID WHERE DATE(orderDate) BETWEEN ? AND ? GROUP BY orderId, orderdetail.productID";
	private final String GET_TOTAL_REVENUE_QUERY			= "SELECT orderdetail.quantity, SUM(orderdetail.quantity * product.price) as Revenue FROM orderdetail LEFT JOIN product ON product.productID = orderdetail.productID GROUP BY orderId, orderdetail.productID";
	private final String GET_LAST_ORDER_QUERY 				= "SELECT * FROM `order` ORDER BY orderID DESC LIMIT 1";
	private final String DELETE_ORDER_DETAIL_BY_ORDER_ID	= "DELETE FROM orderDetail WHERE orderId=?";
	/**
	 * get order 
	 * 
	 * @param id           id of order
	 * @return Item		   item
	 */
	@Override
	public Item getItemById(int id) {
		conn = dbConnector.createConnection();
		
		Order item = null;
		
		try {
			PreparedStatement stt = conn.prepareStatement(GET_ORDER_BY_ID_QUERY);
			stt.setInt(1, id);
			
			ResultSet rs = stt.executeQuery();
			rs.next();
			
			int accountId = rs.getInt(2);
			String address = rs.getString(3);
			Timestamp date = rs.getTimestamp(4);
			double total = rs.getDouble(5);
			String note = rs.getString(6);
			String statusStr = rs.getString(7);
			OrderStatus status = OrderStatus.valueOf(statusStr);
			
			List<OrderDetails> list = this.GetDetailsById(id);
			
			item = new Order(id, accountId, address,
					date, total, note, status, list);
			
			//System.out.println("OrderID: " + item.getOrderId() + " CustomerID: " + item.getCustomerId());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { 
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return item;
	}
	
	/**
	 * get all products in an order
	 * 
	 * @return List   list of item in order
	 */
	@Override
	public List<Item> getAllItems() {
		// TODO Auto-generated method stub
		conn = dbConnector.createConnection();
		List<Item> resultList = new ArrayList<>();
		
		try {
			PreparedStatement stt = conn.prepareStatement(GET_ALL_ORDERS_QUERY);
			ResultSet rs = stt.executeQuery();
			while(rs.next()) {
				int orderID = rs.getInt(1);
				int accountId = rs.getInt(2);
				String address = rs.getString(3);
				Timestamp date = rs.getTimestamp(4);
				double total = rs.getDouble(5);
				String note = rs.getString(6);
				String statusStr = rs.getString(7);
				OrderStatus status = OrderStatus.valueOf(statusStr);
				
				List<OrderDetails> list = this.GetDetailsById(orderID);
				
				Order order = new Order(orderID, accountId, address,
						date, total, note, status, list);
				resultList.add(order);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
			
		} finally { 
			try {
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
		
		return resultList;
	}
	/**
	 * get all products in an order
	 * remove order and order detail by id
	 * 
	 * @param id                    id of order want to get details
	 * @return List<OrderDetails>   list of item in order
	 */
	@Override
	public boolean removeItemById(int id) {
		conn = dbConnector.createConnection();
		
		try {
			//phan vua them vao
			String query  = "update product " + 
				"set " +
				"quantity= ? " +
				"where productID= ?";
			List<OrderDetails> or = GetDetailsById(id);
			for(int pos=0;pos<or.size();pos++){
				//tra ve quantity trong kho
				String sql = "select * from product where productID = "+ id;
				Statement statement = conn.createStatement();
				ResultSet rs = statement.executeQuery(sql);
				int quantity= rs.getInt(4);
				
				//update quantity trong kho sau khi add product vao order
				int quantityAdded= or.get(pos).getQuantity();
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setInt(1, quantity+quantityAdded);
				ps.setInt(2, id);
				ps.executeQuery();
			}
			//ket thuc phan them vao
			
			//delete orderdetail
			PreparedStatement ps1 = conn.prepareStatement(DELETE_ORDER_DETAIL_BY_ORDER_ID);
			ps1.setInt(1, id);
			ps1.executeQuery();
			
			//delete order
			PreparedStatement stt = conn.prepareStatement(DELETE_ORDER_BY_ID_QUERY);
			stt.setInt(1, id);			
			stt.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
			
		} finally { 
			try {
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	/**
	 * get all details in an order
	 * 
	 * @param id                    id of order want to see details
	 * @return List<OrderDetails>   list of orderdetail in order
	 */
	public List<OrderDetails> GetDetailsById(int id) {
		conn = dbConnector.createConnection();
		List<OrderDetails> list = new ArrayList<>();
		
		try {
			PreparedStatement stt = conn.prepareStatement(GET_DETAILS_BY_ORDER_ID_QUERY);
			stt.setInt(1,id);
			ResultSet rs = stt.executeQuery();
			while(rs.next()) {
				int orderId = rs.getInt(1);
				int productId = rs.getInt(2);
				int quantity = rs.getInt(3);
				OrderDetails temp = new OrderDetails(productId, orderId, quantity);
				list.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public boolean addOrderDetailToDB(OrderDetails detail) {
		
		int orderid = detail.getOrderId();
		int productid = detail.getProductId();
		
		try {
			conn = dbConnector.createConnection();
			PreparedStatement stt = conn.prepareStatement(ADD_NEW_ORDERDETAIL_QUERY);
			stt.setInt(1, detail.getOrderId());
			stt.setInt(2, detail.getProductId());
			stt.setInt(3, detail.getQuantity());
			stt.executeUpdate();
			
			Product prd = (Product) pm.getItemById(productid);
			double cash = prd.getPrice() * detail.getQuantity();
			Order order = (Order) this.getItemById(orderid);
			conn.close();
			conn = dbConnector.createConnection();
			String sql = "UPDATE `order` SET total = ? where orderId= ?";
			PreparedStatement stt1 = conn.prepareStatement(sql);
			stt1.setDouble(1, cash);
			stt1.setInt(2, orderid);
			stt1.executeUpdate();
			
			conn.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * add a new order into database
	 * @param newItem			order want to add
	 * @return					id of the item which added successfully. Otherwise return -1
	 */
	@Override
	public int addItem(Item newItem) {
		int id = 0;
		if(!(newItem instanceof Order)) return id;
		conn = dbConnector.createConnection();
		
		try {
			PreparedStatement stt = conn.prepareStatement(ADD_NEW_ORDER_QUERY, Statement.RETURN_GENERATED_KEYS);
			
			// Get information of order
			Order currentItem = (Order)newItem;
			int accountId = currentItem.getAccountId();
			String address = currentItem.getReceivedAddress();
			Timestamp date = currentItem.getOrderDate();
			double total = currentItem.getTotal();
			String note = currentItem.getNote();
			OrderStatus status = currentItem.getStatus();
			String szStatus = status.toString();
			
			// Add info to sql query
			stt.setInt(1, accountId);
			stt.setString(2, address);
			stt.setTimestamp(3, date);
			stt.setDouble(4, total);
			stt.setString(5, note);
			stt.setString(6, szStatus);
			
			// Execute query
			stt.executeUpdate();
			
			ResultSet keys = stt.getGeneratedKeys();
			if (keys.next()) {
				id = keys.getInt(1);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	public List<Order> getOrderListByCustomerId(int customerid) {
		conn = dbConnector.createConnection();
		List<Order> orderList = new ArrayList<>();
		Order order = null;
		
		try {
			PreparedStatement stt = conn.prepareStatement(GET_ORDERS_BY_CTMERID_QUERY);
			stt.setInt(1, customerid);
			ResultSet rs = stt.executeQuery();
			
			while(rs.next()) {
				int orderid = rs.getInt(1);
				int accountid = rs.getInt(2);
				String address = rs.getString(3);
				Timestamp date = rs.getTimestamp(4);
				double total = rs.getDouble(5);
				String note = rs.getString(6);
				String statusStr = rs.getString(7);
				OrderStatus status = OrderStatus.valueOf(statusStr);
				List<OrderDetails> details = this.GetDetailsById(orderid);
				
				order = new Order(orderid, accountid, address, date, total, note, status, details);
				System.out.println("Order: " + order);
				orderList.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return orderList;
	}
	
	public Order getLastOrder() {
		conn = dbConnector.createConnection();
		Order order = null;
		List<OrderDetails> details = null;
		
		try {
			PreparedStatement stt = conn.prepareStatement(GET_LAST_ORDER_QUERY);
			ResultSet rs = stt.executeQuery();
			if (rs.next()) {
				int orderId = rs.getInt(1);
				int accountId = rs.getInt(2);
				String address = rs.getString(3);
				Timestamp date = rs.getTimestamp(4);
				double total = rs.getDouble(5);
				String note = rs.getString(6);
				String statusStr = rs.getString(7);
				OrderStatus status = OrderStatus.valueOf(statusStr);
				details = this.GetDetailsById(orderId);
				
				order = new Order(orderId, accountId, address, date, total, note, status, details);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return order;
	}
	
	public int makeOrder(String orderNote, String receivedAddress, Account account, List<Product> products) {
		
		
		int accountId = account.getId();
		Timestamp currentTime = new Timestamp(new Date().getTime());
		double totalBilling = 0;
		
		Order order = new Order(account.getId(), receivedAddress, currentTime, 
				totalBilling, orderNote, OrderStatus.PENDING, null);
		
		int orderId = addItem(order);
		
		for (Product p : products) {
			int pId = p.getId();
			int boughtQuantity = p.getQuantity();
			OrderDetails orderDetails = new OrderDetails(orderId, pId, boughtQuantity);
			addOrderDetailToDB(orderDetails);
		
			// phan vua moi them vao
			try {
				//lay gia tri dang co trong kho
				conn = dbConnector.createConnection();
				String sql = "select * from product where productID = "+ pId;
				Statement statement = conn.createStatement();
				ResultSet rs = statement.executeQuery(sql);
				rs.next();
				int totalQuantity= rs.getInt(4);
				//update quantity trong kho sau khi add product vao order( - quantity trong order) 
				String query  = "update product " + 
						"set " +
						"quantity= ? " +
						"where productID= ?";
				
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setInt(1, totalQuantity - p.getQuantity());
				ps.setInt(2, pId);
				ps.executeUpdate();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//ket thuc phan them vao
		}
		
//		order.setOrderDetails(GetDetailsById(orderId));
		return orderId;
	}
	
	/**
	 * get the list of best-selling products
	 * @param n                    	the number of products want to list
	 * @return List<OrderDetails>   list of products
	 */
	public List<Item> getTopSellingProduct(int n) {
		conn = dbConnector.createConnection();
		List<Item> list = new ArrayList<>();
		ProductManager pm = new ProductManager();
		
		try {
			PreparedStatement stt = conn.prepareStatement(GET_TOP_SELLING_PRODUCT_QUERY);
			stt.setInt(1, n);
			ResultSet rs = stt.executeQuery();
			while(rs.next()) {
				int productID = rs.getInt("productID");
				int quantity = rs.getInt("Quantity");
				Product product = (Product)pm.getItemById(productID);
				product.setQuantity(quantity);
				list.add(product);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * compute revenue in a day
	 * @param date				the day we want to see revenue
	 * @return revenue			revenue of this day
	 */
	public double[] calDailyRevenue(Date date) {
		conn = dbConnector.createConnection();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String szDate = sdf.format(date);
		double[] arr = new double[3];
		
		try {
			PreparedStatement stt = conn.prepareStatement(GET_REVENUE_IN_DAY_QUERY);
			stt.setString(1, szDate);
			
			ResultSet rs = stt.executeQuery();
			while(rs.next()) {
				arr[0]++;
				arr[1] += (double)rs.getInt("quantity");
				arr[2] += rs.getInt("Revenue");
			}
			return arr;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			arr[0] = arr[1] = arr[2] = 0.0;
			return arr;
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * compute revenue in a month
	 * @param month				the month we want to see revenue
	 * @return revenue			revenue of this month
	 */
	
	public double[] calMonthRevenue(int month) {
		int lastDay;
		String szDate;
		double[] arr = new double[3];
		conn = dbConnector.createConnection();
	
		if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) lastDay = 31;
		else if(month == 2) lastDay = 28;
		else lastDay = 30;
		
		try {
			PreparedStatement stt = conn.prepareStatement(GET_REVENUE_IN_MONTH_QUERY);
			szDate = String.format("2016-" + month + "-" + 1);
			stt.setString(1, szDate);
			szDate = String.format("2016-" + month + "-" + lastDay);
			stt.setString(2, szDate);
			
			ResultSet rs = stt.executeQuery();
			while(rs.next()) {
				arr[0]++;
				arr[1] += (double)rs.getInt("quantity");
				arr[2] += rs.getInt("Revenue");
			}
			return arr;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			arr[0] = arr[1] = arr[2] = 0.0;
			return arr;
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * compute total revenue
	 * @param month
	 * @return
	 */
	public double[] calTotalRevenue() {
		conn = dbConnector.createConnection();
		double[] arr = new double[3];
		try {
			PreparedStatement stt = conn.prepareStatement(GET_TOTAL_REVENUE_QUERY);
			ResultSet rs = stt.executeQuery();
			
			while(rs.next()) {
				arr[0]++;
				arr[1] += (double)rs.getInt("quantity");
				arr[2] += rs.getInt("Revenue");
			}
			
			return arr;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			arr[0] = arr[1] = arr[2] = 0.0;
			return arr;
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean updateItem(int id, Item newItem) {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public List<Item> searchItemByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int countItems() {
		conn = dbConnector.createConnection();
		int count = -1;
		
		try {
			PreparedStatement stt = conn.prepareStatement("SELECT COUNT(*) FROM `order`");
			ResultSet rs = stt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}

			conn.close();
			return count;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
			
		}
	}
	
	public List<Account> getTopCustomers(int n) {
		conn = dbConnector.createConnection();
		List<Account> customers = new ArrayList<>();
		try {
			PreparedStatement stt = conn.prepareStatement(
					"select accountid, count(orderId)"
					+ " from `order`"
					+ " group by accountid limit ?");
			stt.setInt(1, n);
			
			ResultSet rs = stt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				int numOfOrders = rs.getInt(2); 
				Account c = new Account(id, numOfOrders);
				customers.add(c);
			}

			conn.close();
			return customers;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
			
		}
	}

}

