package uet.jcia.shop.model;

public class OrderDetails extends Item {
	
	

	/**
	 * order id for this order detail
	 */
	private int orderId;
	
	/**
	 * product id
	 */
	private int productId;
	
	/**
	 * quantiy of product
	 */
	private int quantity;
	
	public OrderDetails() {}

	public OrderDetails(int orderId, int productId, int quantity) {
		super();
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
	}

	public OrderDetails(int id, int orderId, int productId, int quantity) {
		super(id);
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "OrderDetails [orderId=" + orderId + ", productId=" + productId + ", quantity=" + quantity + "]";
	}
}
