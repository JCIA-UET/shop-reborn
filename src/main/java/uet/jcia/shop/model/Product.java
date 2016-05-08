package uet.jcia.shop.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Product entity, extends from Item
 * @author cuong
 *
 */
public class Product extends Item {
	
	/**
	 * product name
	 */
	private String name;
	
	/**
	 * quantity of product in repository
	 */
	private int quantity;
	
	/**
	 * unit price
	 */
	private double price;
	
	/**
	 * catgoryId which it belongs to
	 */
	private int categoryId;
	
	/**
	 * description of product
	 */
	private String description;
	
	/**
	 * image link for product
	 */
	private String imageLink;
	
	private String categoryName;
	
	public Product() {
		
	}

	public Product(int id, String name, int quantity, double price, int categoryId, String description, String imageLink) {
		super(id);
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.categoryId = categoryId;
		this.description = description;
		this.imageLink = imageLink;
	}

	public Product(String name, int quantity, double price, int categoryId, String description, String imageLink) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.categoryId = categoryId;
		this.description = description;
		this.imageLink = imageLink;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getimageLink() {
		return imageLink;
	}

	public void setimageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	@Override
	public String toString() {
		return "Product [quantity=" + quantity + ", price=" + price + ", categoryId=" + categoryId + ", description="
				+ description + ", id=" + id + ", name=" + name + "]";
	}	
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
}
