package com.revature.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.revature.pojo.Notification;
import com.revature.pojo.Notification.NotificationStatus;
import com.revature.pojo.User;
import com.revature.pojo.User.Role;
import com.revature.util.ConnectionFactory;

public class NotificationDAOImpl implements NotificationDAO {
	private Connection conn = ConnectionFactory.getConnection();
	private String schema = "proj1_release";
	{
		try {
			conn.setSchema(schema);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public boolean creatNotification(Notification notif) {
		String query = "INSERT INTO notification (msg,sender,datecreated,status,receiver,reimbid) " + 
						"VALUES (?,?,?,?,?,?);";
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, notif.getMsg());
			stmt.setString(2, notif.getSender().getUsername());
			stmt.setDate(3, Date.valueOf(notif.getDateCreated()));
			stmt.setString(4, notif.getStatus().name());
			stmt.setString(5, notif.getReceiver().getUsername());
			stmt.setInt(6, notif.getReimbursementId());
			int row = stmt.executeUpdate();
			if (row >= 1) return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Notification getNotification(int id) {
		String query = "SELECT * FROM notification WHERE id = ?";
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet notifRes = stmt.executeQuery();
			if (notifRes.next()) {
				// get msg
				String msg = notifRes.getString("msg");
				// get dateCreated
				LocalDate dateCreated = notifRes.getDate("dateCreated").toLocalDate();
				// get status
				NotificationStatus status = NotificationStatus.valueOf(notifRes.getString("status"));
				// get reimburesment id
				int reimbursementId = notifRes.getInt("reimbid");
				// get sender Object
				User sender = null;
				try {
					String senderUsername = notifRes.getString("sender");
					query = "SELECT * FROM users WHERE username = ?";
					stmt = conn.prepareStatement(query);
					stmt.setString(1, senderUsername);
					ResultSet senderRes = stmt.executeQuery();
					if (senderRes.next()) {
						sender = new User(senderRes.getString("username"),
										senderRes.getString("password"),
										senderRes.getString("firstname"),
										senderRes.getString("lastname"),
										Role.valueOf(senderRes.getString("role")));
						// get receiver
						User receiver = null;
						String receiverUsername = notifRes.getString("receiver");
						query = "SELECT * FROM users WHERE username = ?";
						try {
							stmt = conn.prepareStatement(query);
							stmt.setString(1, receiverUsername);
							ResultSet receiverRes = stmt.executeQuery();
							if (receiverRes.next()) {
								receiver = new User(receiverRes.getString("username"),
										receiverRes.getString("password"),
										receiverRes.getString("firstname"),
										receiverRes.getString("lastname"),
										Role.valueOf(receiverRes.getString("role")));
							}
							// create notification object and return
							return new Notification(msg, sender, dateCreated, reimbursementId, receiver, status);
						} catch (SQLException e) {
							System.out.println("Retrieve NotificationByID: Fail to access user table getting receiver");
							e.printStackTrace();
						}
					}
				} catch (SQLException e) {
					System.out.println("Retrieve NotificationByID: Fail to access user table getting sender");
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			System.out.println("Retrieve NotificationByID: Fail to access notification table");
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public boolean updateNotification(Notification notif) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteNotificationById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Notification> getAllNotifications() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notification> getAllNotificationsByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notification> getAllNotificationsForSupervisor(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
