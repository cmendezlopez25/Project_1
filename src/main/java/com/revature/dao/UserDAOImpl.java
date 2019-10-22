package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.revature.pojo.User;
import com.revature.util.ConnectionFactory;

public class UserDAOImpl implements UserDAO {
	private Connection conn = ConnectionFactory.getConnection();
	private String schema = "proj1_release";
	{
		try {
			conn.setSchema(schema);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void createUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User readUser(String username) {
		User user = null;
		String query = "select * from users where username= ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUsername(rs.getString("username"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.setPassword(rs.getString("password"));
				String role = rs.getString("role");
				user.setRole(User.Role.valueOf(role));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> readAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
}
