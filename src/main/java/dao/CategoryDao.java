package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Category;

public class CategoryDao {
	
	private Connection connection;
	private static final String SQL_SELECT_WHERE_ID = "SELECT * FROM categories WHERE id = ?";
	private static final String SQL_SELECT_WHERE_NAME = "SELECT * FROM categories WHERE name = ?";
	
	public CategoryDao(Connection connection) {
		this.connection = connection;
	}
	
	public Category findById(int id) {
		try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_WHERE_ID)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new Category(id, rs.getString("name"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return null;
	}
	
	public Category findByName(String name) {
		try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_WHERE_NAME)) {
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new Category(rs.getInt("id"), name);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return null;
	}
	
}