package com.revature.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojo.Notification;
import com.revature.pojo.Reimbursement;
import com.revature.pojo.User;
import com.revature.service.NotificationService;
import com.revature.service.NotificationServiceImpl;

/**
 * Servlet implementation class NotificationServlet
 */
public class NotificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private NotificationService notifService = new NotificationServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotificationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("login.html");
			return;
		}

		ObjectMapper om = new ObjectMapper();
		// send announcement and notification in one array
		// where all-notif is arr[0] and new-notif is arr[1]
		List<List<Notification>> allReimbursements = new ArrayList<List<Notification>>();
		User user = (User)session.getAttribute("user");
		allReimbursements.add(notifService.getNotificationsByUser(user));
		allReimbursements.add(notifService.getNewUnreadNotificationByUser(user));
		response.getWriter().write(om.writeValueAsString(allReimbursements));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
