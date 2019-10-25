package com.revature.servlet;

import java.io.IOException;
import java.time.LocalDate;
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
import com.revature.service.UserService;
import com.revature.service.UserServiceImpl;

/**
 * Servlet implementation class NotificationServlet
 */
public class NotificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private NotificationService notifService = new NotificationServiceImpl();
    private UserService userServe = new UserServiceImpl();
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
		List<List<Notification>> allNotification = new ArrayList<List<Notification>>();
		User user = (User)session.getAttribute("user");
		List<Notification> allNotif = notifService.getNotificationsByUser(user);
		List<Notification> newNotif = notifService.getNewNotificationByUser(user);
		allNotification.add(allNotif);
		allNotification.add(newNotif);
		for (int i=0; i<newNotif.size(); i++) {
			notifService.updateNewNotification(newNotif.get(i));
		}
		response.getWriter().write(om.writeValueAsString(allNotification));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		ObjectMapper om = new ObjectMapper();
		String notifJSON = request.getReader().readLine();
		// msg reimbID status
		Notification newNotif = om.readValue(notifJSON, Notification.class);
		// created date
		newNotif.setDateCreated(LocalDate.now());
		// sender
		newNotif.setSender(((User)(request.getSession().getAttribute("user"))));
		// receiver
		newNotif.setReceiver(userServe.getUserByReimbId(newNotif.getReimbursementId()));
		notifService.createNotification(newNotif);
		
	}
}
