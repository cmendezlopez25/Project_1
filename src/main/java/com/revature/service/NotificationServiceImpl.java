package com.revature.service;

import com.revature.dao.NotificationDAO;
import com.revature.dao.NotificationDAOImpl;

public class NotificationServiceImpl implements NotificationService {
	private NotificationDAO notifDao = new NotificationDAOImpl();
	
}
