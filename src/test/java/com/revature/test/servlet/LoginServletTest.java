package com.revature.test.servlet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.revature.pojo.User;
import com.revature.service.UserService;
import com.revature.servlet.LoginServlet;

@RunWith(MockitoJUnitRunner.class)
public class LoginServletTest {
	private LoginServlet loginServlet;
	private User user;
	
	@Mock
	private HttpSession session;
	
	@Mock
	private UserService userService;
	
	@Mock
	private HttpServletRequest req;
	
	@Mock
	private HttpServletResponse resp;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		loginServlet = new LoginServlet();
		user = new User("username", "password", "User", "Name", User.Role.EMPLOYEE);
		
		when(userService.loginUser(null, null)).thenThrow(NullPointerException.class);
		when(userService.loginUser("wrong", "wrong")).thenReturn(null);
		when(userService.loginUser(user.getUsername(), user.getPassword())).thenReturn(user);
		
		loginServlet.setUserService(userService);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void doPostNullUser() {
		when(req.getParameter("username")).thenReturn(null);
		when(req.getParameter("password")).thenReturn(null);
		try {
			loginServlet.doPost(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}  finally {
			Mockito.verify(resp).setStatus(404);
		}
		
	}

	@Test
	public void doPostIncorrectUser() {
		when(req.getParameter("username")).thenReturn("wrong");
		when(req.getParameter("password")).thenReturn("wrong");
		try {
			loginServlet.doPost(req, resp);
			Mockito.verify(resp).setStatus(404);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void doPostCorrectUser() {
		when(req.getParameter("username")).thenReturn(user.getUsername());
		when(req.getParameter("password")).thenReturn(user.getPassword());
		when(req.getSession(true)).thenReturn(session);
		try {
			loginServlet.doPost(req, resp);
			Mockito.verify(session).setAttribute("user", user);
			Mockito.verify(resp).sendRedirect("home");
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}
