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
	private static final String SQL_SELECT_ALL = "SELECT product_id, p.name, price, category_id, c.name AS category_name FROM products AS p INNER JOIN categories AS c ON p.category_id = c.id ORDER BY ?";
	private static final String SQL_SELECT_WHERE_LOGIN_ID_AND_PASSWORD = "SELECT * FROM users WHERE login_id = ? AND password = ?";
	
	public ProductDao(Connection connection) {
		this.connection = connection;
	}
	
	public List<Product> findAll(String column) {
		List<Product> list = new ArrayList<Product>();

		try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ALL)) {
			ResultSet rs = stmt.executeQuery();
			stmt.setString(1, column);
			while (rs.next()) {
				Product p = new Product(rs.getString("product_id"), rs.getString("name"), rs.getInt("price"), rs.getInt("category_id"), rs.getString("category_name"));
				list.add(p);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		}

		return list;
	}
	
}