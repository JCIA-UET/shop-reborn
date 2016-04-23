
package uet.jcia.shop.model;

public abstract class Item {
	/**
	 * item id
	 */
	protected int id;
	
	public Item() {
		
	}
	
	public Item(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
		
}
