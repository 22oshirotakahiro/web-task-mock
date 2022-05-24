package entity;

public class Product {
	
	private int id;
	private String productId;
	private int categoryId;
	private String name;
	private int price;
	private String imagePath;
	private String description;
	private String createdAt;
	private String updatedAt;
	private Category ctgry;
	
	public Product(String productId, String name, int price, String categoryName) {
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.categoryId = 0;
		
	}
	
	public Product(String productId, String name, int price, int categoryId, String categoryName) {
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.ctgry = new Category(categoryId, categoryName);
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Category getCtgry() {
		return ctgry;
	}

	public void setCtgry(Category ctgry) {
		this.ctgry = ctgry;
	}
	

}