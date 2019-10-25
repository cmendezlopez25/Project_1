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

import com.revature.servlet.LogoutServlet;

@RunWith(MockitoJUnitRunner.class)
public class LogoutServletTest {
	private LogoutServlet logoutServlet;
	
	@Mock
	private HttpServletRequest req;
	
	@Mock
	private HttpServletResponse resp;
	
	@Mock
	private HttpSession session;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		logoutServlet = new LogoutServlet();
		
		when(req.getSession()).thenReturn(session);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void logoutSuccess() {
		try {
			logoutServlet.doGet(req, resp);
			Mockito.verify(session).invalidate();
			Mockito.verify(resp).sendRedirect("login.html");
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}
