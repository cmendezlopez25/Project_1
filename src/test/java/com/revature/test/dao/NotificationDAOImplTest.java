package com.revature.test.dao;

import static org.junit.Assert.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.NotificationDAOImpl;
import com.revature.dao.UserDAOImpl;
import com.revature.pojo.Notification;
import com.revature.pojo.Notification.NotificationStatus;
import com.revature.pojo.User;
import com.revature.pojo.User.Role;

public class NotificationDAOImplTest {
	private NotificationDAOImpl notifDao = new NotificationDAOImpl();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getNotificationSuccess() {
		User sender = new User("testuser", "password", "test", "user", Role.EMPLOYEE);
		User receiver = new User("testuser", "password", "test", "user", Role.EMPLOYEE);
		Notification expect = new Notification("some msg", sender, LocalDate.parse("2019-10-20"), 1, receiver, NotificationStatus.NEW);
		assertEquals(expect, notifDao.getNotificationById(5));
	}
	
	@Test
	public void getNotificationNull() {
		assertEquals(null, notifDao.getNotificationById(-1));
	}
	
	// may fail due to change in notification DB
	@Test
	public void getAllNotificationByUserSuccess() {
		User user = new User("testuser", "password", "test", "user", Role.EMPLOYEE);
		ArrayList<Notification> expect = new ArrayList<>();
		LocalDate date = new Date(2019-1900, 9, 20).toLocalDate();
		expect.add(new Notification("some msg", user, date, 1, user, NotificationStatus.NEW));
		expect.add(new Notification("msg1", user, date, 1, user, NotificationStatus.UNREAD));
		assertEquals(expect,(ArrayList<Notification>) notifDao.getAllNotificationsByUser(user));
	}
	
	@Test(expected=NullPointerException.class)
	public void getAllNotificationByUserNullUser() {
		notifDao.getAllNotificationsByUser(null);
	}
	
	@Test
	public void getAllNotificationByUserZeroNotif() {
		User user = new User("example", "password", "exapmle", "user", Role.EMPLOYEE);
		assertEquals(new ArrayList<Notification>(), notifDao.getAllNotificationsByUser(user));
	}
	
	@Test
	public void createNotificationSuccess() {
		User user = new User("testuser", "password", "test", "user", Role.EMPLOYEE);
		Notification newNotif = new Notification("testAddNotif", user, new Date(2019-1900, 9, 23).toLocalDate(), 1, user, NotificationStatus.NEW);
		assertTrue(notifDao.createNotification(newNotif));
	}
	
	@Test(expected=NullPointerException.class)
	public void createNotificationNull() {
		notifDao.createNotification(null);
	}
	
	@Test
	public void getNewUnreadNotifSuccess() {
		User user = new User("testuser", "password", "test", "user", Role.EMPLOYEE);
		ArrayList<Notification> expect = new ArrayList<>();
		LocalDate date1 = new Date(2019-1900, 9, 20).toLocalDate();
		LocalDate date2 = new Date(2019-1900, 9, 23).toLocalDate();
		expect.add(new Notification("some msg", user, date1, 1, user, NotificationStatus.NEW));
		expect.add(new Notification("testAddNotif", user, date2, 1, user, NotificationStatus.NEW));
		assertEquals(expect, notifDao.getNewNotificationByUser(user));
	}
}











