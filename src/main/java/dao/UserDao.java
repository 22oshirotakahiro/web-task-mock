package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.User;

public class UserDao {
	
	private Connection connection;
	private static final String SQL_SELECT_ALL = "SELECT * FROM users ORDER BY id";
	private static final String SQL_SELECT_WHERE_LOGIN_ID_AND_PASSWORD = "SELECT * FROM users WHERE login_id = ? AND password = ?";
	
	public UserDao(Connection connection) {
		this.connection = connection;
	}
	
	public List<User> findAll() {
		List<User> list = new ArrayList<User>();

		try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ALL)) {
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				User u = new User(rs.getString("loginId"), rs.getString("password"), rs.getString("name"), rs.getInt("role"));
				list.add(u);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		}

		return list;
	}
	
	public User findByLoginIdAndPass(String loginId, String pass) {
		try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_WHERE_LOGIN_ID_AND_PASSWORD)) {
			stmt.setString(1, loginId);
			stmt.setString(2, pass);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new User(rs.getString("login_id"), rs.getString("password"), rs.getString("name"), rs.getInt("role"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return null;
	}
	
}