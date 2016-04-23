package uet.jcia.shop.model;

import java.util.List;

/**
 * Manager for Item
 * @author cuong
 *
 */
public interface ItemManager {
	
	/**
	 * connector for processing with database 
	 */
	DBConnector dbConnector = DBConnector.getInstance();
	
	/**
	 * get all items
	 * @return list of items
	 */
	List<Item> getAllItems();
	
	/**
	 * get an item by id
	 * @param id the item's id
	 * @return item which has that id
	 */
	Item getItemById(int id);
	
	/**
	 * remove an item by id
	 * @param id the item's id
	 * @return true if success, false if fail
	 */
	boolean removeItemById(int id);
	
	/**
	 * update an item by id
	 * @param id the item's id
	 * @param newItem the new item to overwrite
	 * @return true if success, false if fail
	 */
	boolean updateItem(int id, Item newItem);
	
	/**
	 * create new item
	 * @param newItem the new item
	 * @return item id if success, -1 if fail
	 */
	int addItem(Item newItem);
}
