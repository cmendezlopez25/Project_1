package com.revature.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojo.Reimbursement;
import com.revature.pojo.User;
import com.revature.service.ReimbursementService;
import com.revature.service.ReimbursementServiceImpl;

/**
 * Servlet implementation class ReimbursementServlet
 */
public class ReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReimbursementService reimburseService = new ReimbursementServiceImpl();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("login.html");
			return;
		}

		ObjectMapper om = new ObjectMapper();
		
		User user = (User)session.getAttribute("user");
		List<Reimbursement> listReimburse = reimburseService.getReimbursementsByUser(user);
		response.getWriter().write(om.writeValueAsString(listReimburse));
		
		if (user.getRole() != User.Role.EMPLOYEE) {
			listReimburse = reimburseService.getReimbursementsForSupervisor(user);
			response.getWriter().write(om.writeValueAsString(listReimburse));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ObjectMapper om = new ObjectMapper();
		//Furniture furniture = om.readValue(body, Furniture.class);
	}

	public void setReimburseService(ReimbursementService reimburseService) {
		this.reimburseService = reimburseService;
	}
}
