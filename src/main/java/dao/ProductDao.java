package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Product;

public class ProductDao {
	
	private Connection connection;
	private static final String SQL_SELECT_ALL = "SELECT product_id, p.name, price, description, category_id, c.name AS category_name FROM products AS p INNER JOIN categories AS c ON p.category_id = c.id ORDER BY ?";
	private static final String SQL_SELECT_LIKE_KEYWORD = "SELECT product_id, p.name, price, description, category_id, c.name AS category_name FROM products AS p INNER JOIN categories AS c ON p.category_id = c.id WHERE p.name LIKE ? OR c.name LIKE ?";
	private static final String SQL_SELECT_WHERE_PRODUCT_ID = "SELECT product_id, p.name, price, description, category_id, c.name AS category_name FROM products AS p INNER JOIN categories AS c ON p.category_id = c.id WHERE product_id = ?";
	private static final String SQL_INSERT = "INSERT INTO products(product_id, name, price, category_id) VALUES(?, ?, ?, ?)";
	private static final String SQL_DELETE = "DELETE FROM products WHERE product_id = ?";
	private static final String SQL_UPDATE = "UPDATE products SET product_id = ?, name = ?, price = ?, category_id = ?, description = ? WHERE product_id = ?";
	
	public ProductDao(Connection connection) {
		this.connection = connection;
	}
	
	public List<Product> findAll() {
		List<Product> list = new ArrayList<Product>();

		try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ALL)) {
			
			stmt.setString(1, "product_id");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Product p = new Product(rs.getInt("product_id"), rs.getString("name"), rs.getInt("price"), rs.getString("description"), rs.getInt("category_id"), rs.getString("category_name"));
				list.add(p);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		}

		return list;
	}
	
	public List<Product> findLikeKeyWord(String keyWord) {
		List<Product> list = new ArrayList<Product>();
		
		try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_LIKE_KEYWORD)) {
			
			stmt.setString(1, "%" + keyWord + "%");
			stmt.setString(2, "%" + keyWord + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Product p = new Product(rs.getInt("product_id"), rs.getString("name"), rs.getInt("price"), rs.getString("description"), rs.getInt("category_id"), rs.getString("category_name"));
				list.add(p);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		}

		return list;
	}
	
	public Product findByProductId(int productId) {
		try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_WHERE_PRODUCT_ID)) {
			stmt.setInt(1, productId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new Product(rs.getInt("product_id"), rs.getString("name"), rs.getInt("price"), rs.getString("description"), rs.getInt("category_id"), rs.getString("category_name"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return null;
	}

	
	public int insert(Product product) {
		try (PreparedStatement stmt = connection.prepareStatement(SQL_INSERT)) {
			stmt.setInt(1, product.getProductId());
			stmt.setString(2, product.getName());
			stmt.setInt(3, product.getPrice());
			stmt.setInt(4, product.getCategoryId());
			
			return stmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
        }

	}
	
	  public int delete(int productId) {
		 try (PreparedStatement stmt = connection.prepareStatement(SQL_DELETE)) { 
			 stmt.setInt(1, productId);
			 return stmt.executeUpdate(); 
			 
		 } catch (SQLException e) {
			throw new RuntimeException(e); 
			
		 } 
		 
	 }
	  
	  public int update(int productId, Product product) {
		  try (PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE)) {
			  stmt.setInt(1,product.getProductId());
			  stmt.setString(2,product.getName());
			  stmt.setInt(3,product.getPrice());
			  stmt.setInt(4,product.getCategoryId());
			  stmt.setString(5,product.getDescription());
			  stmt.setInt(6,productId);
			  
			  return stmt.executeUpdate();
			  
		  } catch (SQLException e) {
			  throw new RuntimeException(e);
			  
		  } 
	  }
	 
	  
	 }