package com.revature.servlet;

import static com.revature.util.LoggerUtil.log;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.revature.pojo.Reimbursement;
import com.revature.pojo.Reimbursement.ReimbursementStatus;
import com.revature.pojo.User;
import com.revature.service.ReimbursementService;
import com.revature.service.ReimbursementServiceImpl;
import com.revature.util.FakeReimbursement;

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

		List<List<Reimbursement>> allReimbursements = new ArrayList<List<Reimbursement>>();
		User user = (User)session.getAttribute("user");
		allReimbursements.add(reimburseService.getReimbursementsByUser(user));
		
		if (user.getRole() != User.Role.EMPLOYEE) {
			allReimbursements.add(reimburseService.getReimbursementsForSupervisor(user));
		}

		response.getWriter().write(om.writeValueAsString(allReimbursements));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper om = new ObjectMapper();
		String reimbursement = request.getReader().readLine();
		log.debug(reimbursement);
		
		FakeReimbursement fake = om.readValue(reimbursement, FakeReimbursement.class);
		Reimbursement reimburse = new Reimbursement();
		reimburse.setOwnerUserName(((User)(request.getSession().getAttribute("user"))).getUsername());
		reimburse.setType(fake.getType());
		reimburse.setAmount(fake.getAmount());
		reimburse.setStatus(fake.getStatus());
		reimburse.setDateCreated(LocalDate.parse(fake.getDateCreated(), DateTimeFormatter.ofPattern("MM-dd-yyyy")));
		reimburse.setDateLastModified(LocalDate.parse(fake.getDateLastModified(), DateTimeFormatter.ofPattern("MM-dd-yyyy")));
		reimburse.setMessage(fake.getMessage());
		reimburse.setPassGrade(fake.getPassGrade());
		reimburse.setDateEvent(LocalDate.parse(fake.getDateEvent(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		
		log.debug(reimburse);
		String attachments = request.getReader().readLine();
		log.debug(attachments);
		List<File> allAttachments = new ArrayList<File>();//om.readValue(attachments, List.class);
		
		//if (allAttachments == null) {
			//allAttachments = new ArrayList<File>();
		//}
		
		reimburseService.createReimbursement(reimburse, allAttachments);
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getReader().readLine();
		int reimbId = Integer.parseInt(request.getReader().readLine());
		Reimbursement reimb = reimburseService.getReimbursementById(reimbId);
		if ("reject".equals(action)) {
			reimb.setStatus(ReimbursementStatus.REJECTED);
		}
		else if ("approval".equals(action)) {
			
		}
		// update reimb
		reimburseService.updateReimbursement(reimb);

	}

	public void setReimburseService(ReimbursementService reimburseService) {
		this.reimburseService = reimburseService;
	}
}
