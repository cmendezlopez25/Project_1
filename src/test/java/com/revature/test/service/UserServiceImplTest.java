package com.revature.test.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

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

import com.revature.dao.UserDAO;
import com.revature.pojo.User;
import com.revature.service.UserService;
import com.revature.service.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
	private User user;
	private UserServiceImpl userServiceImpl;
	
	@Mock
	private UserDAO userDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		userServiceImpl = new UserServiceImpl();
		
		user = new User("username", "password", "User", "Name", User.Role.EMPLOYEE);
		
		when(userDao.readUser(user.getUsername())).thenReturn(user);
		userServiceImpl.setDao(userDao);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = NullPointerException.class)
	public void loginUserNullPassword() {
		userServiceImpl.loginUser(null, "");
	}

	@Test(expected = NullPointerException.class)
	public void loginUserNullUsername() {
		userServiceImpl.loginUser("", null);
	}

	@Test
	public void loginUserIncorrectUsername() {
		assertEquals("Should return null", null, userServiceImpl.loginUser("wrong", "password"));
	}
	
	@Test
	public void loginUserIncorrectPassword() {
		assertEquals("Should return null", null, userServiceImpl.loginUser("username", "wrong"));
	}
	
	@Test
	public void loginUserCorrectUsernamePassword() {
		assertEquals("Should return user", user, userServiceImpl.loginUser("username", "password"));
	}
}
