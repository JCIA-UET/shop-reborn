package uet.jcia.shop.model;

/**
 * Category entity
 * @author cuong
 *
 */
public class Category extends Item {
	
	/**
	 * category name
	 */
	private String name;
	
	/**
	 * category description
	 */
	private String description;
	
	public Category() {
		
	}

	public Category(int id, String name, String description) {
		super(id);
		this.name = name;
		this.description = description;
	}

	public Category(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Category [name=" + name + ", description=" + description + ", id=" + id + "]";
	}
	
}
