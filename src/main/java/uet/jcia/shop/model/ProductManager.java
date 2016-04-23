package uet.jcia.shop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Manager for Product entity
 * @author cuong
 *
 */
public class ProductManager implements ItemManager {
	
	/**
	 * mysql connection session
	 */
	private Connection con = null;
	
	/**
	 * get all product in db
	 * @return list of products
	 */
	@Override
	public List<Item> getAllItems() {
		List<Item> products = new ArrayList<>();
		Item product = null;
		
		try {
			con = dbConnector.createConnection();
			Statement statement = con.createStatement();
			String query = "select * from product";
			ResultSet rs = statement.executeQuery(query);
		
			while (rs.next()) {
				int productId = rs.getInt(1);
				String name = rs.getString(2);
				double price = rs.getDouble(3);
				int quantity = rs.getInt(4);
				int categoryId = rs.getInt(5);
				String description = rs.getString(6);
				String imgLink = rs.getString(7);
				
				product = new Product(productId, name, quantity, price,
						categoryId, description, imgLink);
				products.add(product);
			}
			
			con.close();
			return products;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Product> getAllProductByCategoryId(int categoryId) {
		List<Product> products = new ArrayList<>();
		Product product = null;
		
		try {
			con = dbConnector.createConnection();
			String query = "SELECT * FROM product WHERE categoryId=?";
			
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, categoryId);
			
			ResultSet rs = statement.executeQuery();
		
			while (rs.next()) {
				int productId = rs.getInt(1);
				String name = rs.getString(2);
				double price = rs.getDouble(3);
				int quantity = rs.getInt(4);
				String description = rs.getString(6);
				String imgLink = rs.getString(7);
				
				product = new Product(productId, name, quantity, price,
						categoryId, description, imgLink);
				
				products.add(product);
			}
			
			con.close();
			return products;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * get a product by its id
	 * @param id product's id
	 * @return the product which has that id
	 */
	@Override
	public Item getItemById(int id) {
		try {
			con = dbConnector.createConnection();
			String query = "select * from product " +
							"where productID= ?";
			
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, id);
			
			ResultSet rs = statement.executeQuery();
			Item product = null;
			
			if (rs.next()) {
				int productId = rs.getInt(1);
				String name = rs.getString(2);
				double price = rs.getDouble(3);
				int quantity = rs.getInt(4);
				int categoryId = rs.getInt(5);
				String description = rs.getString(6);
				String imgLink = rs.getString(7);
				
				product = new Product(productId, name, quantity, price,
						categoryId, description, imgLink);
			}
			
			con.close();
			return product;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * remove product by it's id
	 * @param id product's id
	 * @return true if remove successfully and false if otherwise
	 */
	@Override
	public boolean removeItemById(int id) {
		if (getItemById(id) == null) {
			return false;
		}
		
		try {
			con = dbConnector.createConnection();
			String query = "delete from product " +
							"where productID= ?";
			
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, id);
			statement.execute();

			con.close();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * update a product
	 * @param the current id of product
	 * @param newItem the new product will be written over
	 * @return true if update successfully and false if otherwise
	 */
	@Override
	public boolean updateItem(int id, Item newItem) {
		
		if (!(newItem instanceof Product)) {
			return false;
		}
		
		if (getItemById(id) == null) {
			return false;
		}
		
		try {			
			String query =
					"update product " + 
					"set " +
					"productID= ?, name= ?, price= ?, " + 
					"quantity= ?, categoryId= ?, description= ?, imgLink= ?" +
					"where productID= ?";
						
			con = dbConnector.createConnection();
			PreparedStatement statement = con.prepareStatement(query);
			
			Product newProduct = (Product) newItem;
			statement.setInt(1, newProduct.getId());
			statement.setString(2, newProduct.getName());
			statement.setDouble(3, newProduct.getPrice());
			statement.setInt(4, newProduct.getQuantity());
			statement.setInt(5, newProduct.getCategoryId());
			statement.setString(6, newProduct.getDescription());
			statement.setString(7, newProduct.getImageLink());
			statement.setInt(8, id);
			
			statement.execute();
			con.close();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;	
		}
	}

	/**
	 * add new product
	 * @param newItem new product
	 * @return item id if add successfully and 0 if otherwise
	 */
	@Override
	public int addItem(Item newItem) {
		if (!(newItem instanceof Product)) {
			return 0;
		}
		
		int newProductId = newItem.getId();
		if (getItemById(newProductId) != null) {
			return 0;
		}
		
		int id = 0;
		
		try {			
			String query = "insert into product "	+
					"(name, price, quantity, categoryId, description, imgLink) " + 
					"values (?, ?, ?, ?, ?, ?)";
						
			con = dbConnector.createConnection();
			PreparedStatement statement = con.prepareStatement(
					query, Statement.RETURN_GENERATED_KEYS);
			
			Product newProduct = (Product) newItem;
			statement.setString(1, newProduct.getName());
			statement.setDouble(2, newProduct.getPrice());
			statement.setInt(3, newProduct.getQuantity());
			statement.setInt(4, newProduct.getCategoryId());
			statement.setString(5, newProduct.getDescription());	
			statement.setString(6, newProduct.getImageLink());
			statement.executeUpdate();
			
			ResultSet keys = statement.getGeneratedKeys();
			if (keys.next()) {
				id = keys.getInt(1);
			}
			
			con.close();
			return id;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return id;
			
		}
	}
}
