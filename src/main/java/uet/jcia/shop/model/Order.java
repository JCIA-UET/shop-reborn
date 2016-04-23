package uet.jcia.shop.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Order entity
 * @author cuong
 *
 */
public class Order extends Item {
	/**
	 * id of the customer
	 */
	private int accountId;

	/**
	 * received address which customer wish
	 */
	private String receivedAddress;
	
	/**
	 * order date
	 */
	private Timestamp date;
	
	/**
	 * total billing of order
	 */
	private double total;
	
	/**
	 * extra note for order
	 */
	private String note;

	/**
	 * order status: pending or completed
	 */
	private OrderStatus status;
	
	/**
	 * list of order details
	 */
	private List<OrderDetails> orderDetails;

	public Order() {}
	
	public Order(int id, int accountId, String receivedAddress, Timestamp date, 
			double total, String note, OrderStatus status,
			List<OrderDetails> orderDetails) {
		
		super(id);
		this.accountId = accountId;
		this.receivedAddress = receivedAddress;
		this.date = date;
		this.total = total;
		this.note = note;
		this.status = status;
		this.orderDetails = orderDetails;
	}

	public Order(int accountId, String receivedAddress, Timestamp date, double total, String note, OrderStatus status,
			List<OrderDetails> orderDetails) {
		super();
		this.accountId = accountId;
		this.receivedAddress = receivedAddress;
		this.date = date;
		this.total = total;
		this.note = note;
		this.status = status;
		this.orderDetails = orderDetails;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getReceivedAddress() {
		return receivedAddress;
	}

	public void setReceivedAddress(String receivedAddress) {
		this.receivedAddress = receivedAddress;
	}

	

	public Timestamp getOrderDate() {
		return date;
	}

	public void setOrderDate(Timestamp date) {
		this.date = date;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
}