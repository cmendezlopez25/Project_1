package com.revature.dao;

import java.util.List;

import com.revature.pojo.Notification;
import com.revature.pojo.Notification;
import com.revature.pojo.User;

public interface NotificationDAO {
	public boolean createNotification(Notification notif);
	public Notification getNotification(int id);
	public boolean updateNotification(Notification notif);
	public boolean deleteNotificationById(int id);
	public List<Notification> getAllNotifications();
	public List<Notification> getAllNotificationsByUser(User user);
	public List<Notification> getAllNotificationsForSupervisor(User user);
}
