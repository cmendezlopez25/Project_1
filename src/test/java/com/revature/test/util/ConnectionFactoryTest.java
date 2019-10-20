package com.revature.test.util;

import static org.junit.Assert.*;

import java.sql.*;


import org.junit.Test;

import com.revature.util.ConnectionFactory;

public class ConnectionFactoryTest {

	@Test
	public void connectionTest() {
		String url = "jdbc:postgresql://localhost:5432/postgres?";
		String user = "postgres";
		String password = "wang87067835";
		Statement stmt = null;
		ResultSet res = null;
		String actual = null;
		Connection conn = ConnectionFactory.getConnection();
		String query = "SELECT * FROM users WHERE username = 'testuser'";
		try {
			conn.setSchema("proj1_release");
			stmt = conn.createStatement();
			res = stmt.executeQuery(query);
			if (res.next()) {
				actual = res.getString("username");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		assertEquals("testuser", actual);
	}

}
