package com.revature.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLEngineResult.Status;

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
	public boolean createNotification(Notification notif) {
		if (notif==null) throw new NullPointerException();
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
			System.out.println("Create Notification: Fail to create new Notif no row is added");
		} catch (SQLException e) {
			System.out.println("Create Notification: Fail to create new Notif due to SQLException");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Notification getNotificationById(int id) {
		String query = "SELECT * FROM notification WHERE id = ?";
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet notifRes = stmt.executeQuery();
			if (notifRes.next()) {
				return getSingleNotifByResultSet(notifRes);
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
		// don't do
		return false;
	}

	@Override
	public boolean deleteNotificationById(int id) {
		// TODO Auto-generated method stub
		// Dont do
		return false;
	}

	@Override
	public List<Notification> getAllNotifications() {
		// TODO Auto-generated method stub
		// Dont do
		return null;
	}

	@Override
	public List<Notification> getAllNotificationsByUser(User user) {
		if (user == null) throw new NullPointerException();
		// TODO Auto-generated method stub
		String query = "SELECT * FROM notification WHERE receiver = ?";
		PreparedStatement stmt;
		ArrayList<Notification> notifList = new ArrayList<>();
		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, user.getUsername());
			ResultSet notifRes = stmt.executeQuery();
			while (notifRes.next()) {
				notifList.add(getSingleNotifByResultSet(notifRes));
			}
			return notifList;
		} catch (SQLException e) {
			System.out.println("Retrieve getAllNotification: Fail to get notification from DB");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Notification> getAllNotificationsForSupervisor(User user) {
		// TODO Auto-generated method stub
		// Dont do
		return null;
	}

	@Override
	public List<Notification> getNewUnreadNotificationByUser(User user) throws NullPointerException {
		if (user == null) throw new NullPointerException();
		String query = "SELECT * FROM notification WHERE receiver = ? AND (status = ? or status = ?);";
		ArrayList<Notification> notifList = new ArrayList<Notification>();
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, NotificationStatus.NEW.name());
			stmt.setString(3, NotificationStatus.UNREAD.name());
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				notifList.add(getSingleNotifByResultSet(res));
			}
			return notifList;
		} catch (SQLException e) {
			System.out.println("Get New|Unread Notification: Fail to retrieve notif due to SQLExcpetion");
			e.printStackTrace();
		}
		
		return null;
	}

	// receive the ResultSet and return a single notification at a time
	private Notification getSingleNotifByResultSet(ResultSet notifRes) {
		String query = "";
		PreparedStatement stmt;
		try {
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
						System.out.println("Retrieve SingleNotif from ResultSet: Fail to access user table getting receiver");
						e.printStackTrace();
					}
				}
			} catch (SQLException e) {
				System.out.println("Retrieve SingleNotif from ResultSet: Fail to access user table getting sender");
				e.printStackTrace();
			}
		} catch (SQLException e) {
			System.out.println("Retrieve SingleNotif from ResultSet: Fail to access user table getting sender");
			e.printStackTrace();
		}
		return null;
	}

}











