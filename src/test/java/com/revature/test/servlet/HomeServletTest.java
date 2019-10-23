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
import com.revature.pojo.User.Role;
import com.revature.servlet.HomeServlet;

@RunWith(MockitoJUnitRunner.class)
public class HomeServletTest {
	private HomeServlet homeServlet;
	private User user;
	
	@Mock
	private HttpSession session;
	
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
		homeServlet = new HomeServlet();
		user = new User();
		user.setUsername("testuser");
		user.setPassword("password");
		user.setFirstName("Test");
		user.setLastName("User");
		user.setRole(Role.EMPLOYEE);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void doGetNoUser() {
		try {
			homeServlet.doGet(req, resp);
			Mockito.verify(resp).sendRedirect("login.html");
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void doGetEmployee() {
		try {
			when(session.getAttribute("user")).thenReturn(user);
			when(req.getSession(false)).thenReturn(session);
			homeServlet.doGet(req, resp);
			Mockito.verify(resp).sendRedirect("emp_home.html");
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void doGetManager() {
		user.setRole(Role.MANAGER);
		try {
			when(session.getAttribute("user")).thenReturn(user);
			when(req.getSession(false)).thenReturn(session);
			homeServlet.doGet(req, resp);
			Mockito.verify(resp).sendRedirect("mang_home.html");
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}
