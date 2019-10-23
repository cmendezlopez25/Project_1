package com.revature.service;

import java.util.List;

import com.revature.dao.NotificationDAO;
import com.revature.dao.NotificationDAOImpl;
import com.revature.pojo.Notification;
import com.revature.pojo.User;

public class NotificationServiceImpl implements NotificationService {
	private NotificationDAO notifDao = new NotificationDAOImpl();

	@Override
	public boolean createNotification(Notification notif) throws NullPointerException {
		if (notif == null) {
			throw new NullPointerException();
		}
		return notifDao.createNotification(notif);
	}

	@Override
	public Notification getNotificationById(int id) {
		return notifDao.getNotificationById(id);
	}

	@Override
	public boolean updateNotification(Notification notif) {
		// TODO Auto-generated method stub
		// dont
		return false;
	}

	@Override
	public boolean deleteNotificationById(int id) {
		// TODO Auto-generated method stub
		// dont
		return false;
	}

	@Override
	public List<Notification> getNotificationsByUser(User user) throws NullPointerException {
		if (user == null) {
			throw new NullPointerException();
		}
		return notifDao.getAllNotificationsByUser(user);
	}

	@Override
	public List<Notification> getAllNotifications() {
		// TODO Auto-generated method stub
		// dont
		return null;
	}
	
}
