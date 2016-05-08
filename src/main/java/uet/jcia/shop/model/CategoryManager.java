package uet.jcia.shop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Manager class for Category
 * This class manipulate directly with database
 * @author cuong
 *
 */
public class CategoryManager implements ItemManager {
	
	/**
	 * mysql Connection session
	 */
	private Connection con = null;
	
	/**
	 * get all categories from db
	 * @return list of categories
	 */
	@Override
	public List<Item> getAllItems() {
		List<Item> categories = new ArrayList<>();
		Item category = null;
		
		try {
			con = dbConnector.createConnection();
			Statement statement = con.createStatement();
			String query = "select * from category";
			ResultSet rs = statement.executeQuery(query);
		
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String des = rs.getString(3);
				
				category = new Category(id, name, des);
				categories.add(category);
			}
			
			con.close();
			return categories;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * get category by categoryId from db
	 * @param id the category id
	 * @return the found category
	 */
	@Override
	public Item getItemById(int id) {
		try {
			con = dbConnector.createConnection();
			String query = "select * from category " +
							"where categoryID= ?";
			
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, id);
			
			ResultSet rs = statement.executeQuery();
			Item category = null;
			
			if (rs.next()) {
				String categoryName = rs.getString(2);
				String des = rs.getString(3);
				category = new Category(id, categoryName, des);
			}
			
			con.close();
			return category;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * remove category by its id from db
	 * @param id the category's id
	 * @return true if successfully remove and false if otherwise
	 */
	@Override
	public boolean removeItemById(int id) {
		
		if (getItemById(id) == null) {
			return false;
		}
		
		try {
			con = dbConnector.createConnection();
			String query = "delete from category " +
							"where categoryID= ?";
			
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
	 * update a category in db
	 * @param id the old id of category
	 * @param newItem new category
	 * @return true if update successfully and false if otherwise
	 */
	@Override
	public boolean updateItem(int id, Item newItem) {
		
		if (!(newItem instanceof Category)) {
			return false;
		}
		
		if (getItemById(id) == null) {
			return false;
		}
		
		try {			
			String query =
					"update category " + 
					"set categoryID= ?, name= ?, description= ? " +
					"where categoryID= ?";
						
			con = dbConnector.createConnection();
			PreparedStatement statement = con.prepareStatement(query);
			
			Category newCategory = (Category) newItem;
			statement.setInt(1, newCategory.getId());
			statement.setString(2, newCategory.getName());
			statement.setString(3, newCategory.getDescription());
			statement.setInt(4, id);	
			
			statement.execute();
			con.close();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
			
		}
	}
	
	/**
	 * add new category in db
	 * @param newItem new category
	 * @return item id if add successfully and 0 if otherwise
	 */
	@Override
	public int addItem(Item newItem) {
		if (!(newItem instanceof Category)) {
			return 0;
		}
		
		int newCategoryId = newItem.getId();
		if (getItemById(newCategoryId) != null) {
			return 0;
		}
		
		int id = 0;
		
		try {			
			String query = "insert into category "	+
					"(name, description) " +
					"values " +
					"(?, ?)";
						
			con = dbConnector.createConnection();
			PreparedStatement statement = con.prepareStatement(
					query, Statement.RETURN_GENERATED_KEYS);
			
			Category newCategory = (Category) newItem;
			statement.setString(1, newCategory.getName());	
			statement.setString(2, newCategory.getDescription());
			statement.executeUpdate();
			
			ResultSet keys = statement.getGeneratedKeys();
			if (keys.next()) {
				id = keys.getInt(1);
			}
			
			con.close();
			return id;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
			
		}

	}

	public String getNameById(int id) {
		try {
			con = dbConnector.createConnection();
			String query = "select name from category " +
							"where categoryID= ?";
			
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, id);
			
			ResultSet rs = statement.executeQuery();
			String name = null;
			
			if (rs.next()) {
				name = rs.getString(1);
			}
			
			con.close();
			return name;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<Item> searchItemByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
