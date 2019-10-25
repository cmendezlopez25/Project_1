package com.revature.test.dao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.revature.dao.UserDAOImpl;
import com.revature.pojo.User;
import com.revature.util.ConnectionFactory;

@RunWith(MockitoJUnitRunner.class)
public class UserDaoTest {
	
	private UserDAOImpl userDao = new UserDAOImpl();
	String schema = "proj1_test";
	String query = "select * from users where username= ?";

	@Mock
	Connection conn;
	
	@Spy
	PreparedStatement stmt;
	{
		try {
			Connection tempConnection = ConnectionFactory.getConnection();
			tempConnection.setSchema(schema);
			stmt = tempConnection.prepareStatement(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		when(conn.prepareStatement(query)).thenReturn(stmt);
		userDao.setConnection(conn);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void readUserCorrectTest() {
		User expect = new User("testuser", "password", "test", "user", User.Role.EMPLOYEE);
		assertEquals(expect, userDao.readUser("testuser"));
		try {
			Mockito.verify(stmt).executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
