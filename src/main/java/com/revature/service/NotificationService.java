package com.revature.service;

import java.io.File;
import java.util.List;

import com.revature.pojo.Notification;
import com.revature.pojo.User;

public interface NotificationService {
	public boolean createNotification(Notification reimburse);
	public Notification getNotificationById(int id);
	public boolean updateNotification(Notification reimburse);  // dont
	public boolean deleteNotificationById(int id);				// dont
	public List<Notification> getNotificationsByUser(User user);
	public List<Notification> getAllNotifications();			// dont
}
