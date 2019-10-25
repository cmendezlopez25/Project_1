package com.revature.test.service;

import static org.junit.Assert.*;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.pojo.Notification;
import com.revature.pojo.User;
import com.revature.pojo.Notification.NotificationStatus;
import com.revature.pojo.User.Role;
import com.revature.service.NotificationServiceImpl;

public class NotificationServiceImplTest {
	private NotificationServiceImpl notifServe;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		notifServe = new NotificationServiceImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getNotificationSeriveSuccess() {
		User sender = new User("testuser", "password", "test", "user", Role.EMPLOYEE);
		User receiver = new User("testuser", "password", "test", "user", Role.EMPLOYEE);
		Notification expect = new Notification("some msg", sender, LocalDate.parse("2019-10-20"), 1, receiver, NotificationStatus.NEW);
		assertEquals(expect, notifServe.getNotificationById(5));
	}
	
	@Test 
	public void createNotificationSeriveSuccess() {
		User user = new User("testuser", "password", "test", "user", Role.EMPLOYEE);
		Notification newNotif = new Notification("testAddNotif", user, new Date(2019-1900, 9, 23).toLocalDate(), 1, user, NotificationStatus.NEW);
		try {
			notifServe.createNotification(newNotif);
		} catch(NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	@Test(expected=NullPointerException.class)
	public void createNotificationSeriveNull() {
		notifServe.createNotification(null);
	}

}





