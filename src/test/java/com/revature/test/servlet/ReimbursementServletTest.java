package com.revature.test.servlet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojo.Reimbursement;
import com.revature.pojo.User;
import com.revature.service.ReimbursementService;
import com.revature.servlet.ReimbursementServlet;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReimbursementServletTest {
	private ReimbursementServlet reimburseServlet;
	private List<Reimbursement> reimburseListUser;
	private List<Reimbursement> reimburseListSupervisor;
	private User user;
	private ObjectMapper om;
	
	@Mock
	private ReimbursementService reimburseService;
	
	@Mock
	private PrintWriter writer;
	
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
		reimburseServlet = new ReimbursementServlet();
		reimburseListUser = new ArrayList<Reimbursement>();
		reimburseListSupervisor = new ArrayList<Reimbursement>();
		om = new ObjectMapper();
		
		user = new User();
		user.setUsername("testuser");
		user.setPassword("password");
		user.setFirstName("Test");
		user.setLastName("User");
		user.setRole(User.Role.EMPLOYEE);
		
		Reimbursement reimburse = new Reimbursement();
		reimburse.setId(1);
		reimburse.setType(Reimbursement.ReimbursementType.CERTIFICATION);
		reimburse.setAmount(200.00);
		reimburse.setStatus(Reimbursement.ReimbursementStatus.PEND_DS);
		reimburse.setDateCreated(LocalDate.now());
		reimburse.setDateLastModified(LocalDate.now());
		
		reimburseListUser.add(reimburse);
		
		reimburse = new Reimbursement();
		reimburse.setId(2);
		reimburse.setType(Reimbursement.ReimbursementType.OTHER);
		reimburse.setAmount(300.00);
		reimburse.setStatus(Reimbursement.ReimbursementStatus.PEND_DS);
		reimburse.setDateCreated(LocalDate.now());
		reimburse.setDateLastModified(LocalDate.now());
		
		reimburseListSupervisor.add(reimburse);
		
		when(reimburseService.getReimbursementsByUser(user)).thenReturn(reimburseListUser);
		when(reimburseService.getReimbursementsForSupervisor(user)).thenReturn(reimburseListSupervisor);
		when(resp.getWriter()).thenReturn(writer);
		reimburseServlet.setReimburseService(reimburseService);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void doGetNoSession() {
		try {
			reimburseServlet.doGet(req, resp);
			Mockito.verify(resp).sendRedirect("login.html");
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
			
	}

	@Test
	public void doGetUserAllReimbursements() {
		when(session.getAttribute("user")).thenReturn(user);
		when(req.getSession(false)).thenReturn(session);
		try {
			reimburseServlet.doGet(req, resp);
			Mockito.verify(reimburseService).getReimbursementsByUser(user);
			Mockito.verify(writer).write(om.writeValueAsString(reimburseListUser));
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void doGetUserAllReimbursementsForSupervisor() {
		user.setRole(User.Role.MANAGER);
		when(session.getAttribute("user")).thenReturn(user);
		when(req.getSession(false)).thenReturn(session);
		try {
			reimburseServlet.doGet(req, resp);
			Mockito.verify(reimburseService).getReimbursementsByUser(user);
			Mockito.verify(reimburseService).getReimbursementsForSupervisor(user);
			Mockito.verify(writer).write(om.writeValueAsString(reimburseListSupervisor));
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}
