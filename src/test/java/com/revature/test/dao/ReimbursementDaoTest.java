package com.revature.test.dao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.postgresql.util.PSQLException;

import com.revature.dao.ReimbursementDAOImpl;
import com.revature.pojo.Reimbursement;
import com.revature.pojo.Reimbursement.ReimbursementStatus;
import com.revature.pojo.User;
import com.revature.util.ConnectionFactory;

@RunWith(MockitoJUnitRunner.class)
public class ReimbursementDaoTest {
	private ReimbursementDAOImpl reimburseDao;
	private List<Reimbursement> reimburseList;

	private User fakeUser;
	private User realUser;

	private String schemaName = "proj1_test";
	private String createSql = "insert into reimbursement(id, type, amount, status, datecreated, datelastmodified, username) values(?, ?, ?, ?, ?, ?, ?)";
	private String readOneSql = "select id, type, amount, status, datecreated, datelastmodified from reimbursement where id=?";
	private String updateSql = "update reimbursement set amount=?, status=?, datelastmodified=? where id=?";
	private String deleteSql = "delete from reimbursement where id=?";
	private String readAllSql = "select id, type, amount, status, datecreated, datelastmodified from reimbursement";
	private String readByUserSql = "";
	private String readForSupervisorSql = "";

	@Mock
	private Connection conn;

	@Spy
	private PreparedStatement createStmt;
	{
		Connection tempConnection = ConnectionFactory.getConnection();
		tempConnection.setSchema(schemaName);
		createStmt = tempConnection.prepareStatement(createSql);
	}

	@Spy
	private PreparedStatement readOneStmt;
	{
		Connection tempConnection = ConnectionFactory.getConnection();
		tempConnection.setSchema(schemaName);
		readOneStmt = tempConnection.prepareStatement(readOneSql);
	}

	@Spy
	private PreparedStatement updateStmt;
	{
		Connection tempConnection = ConnectionFactory.getConnection();
		tempConnection.setSchema(schemaName);
		updateStmt = tempConnection.prepareStatement(updateSql);
	}

	@Spy
	private PreparedStatement deleteStmt;
	{
		Connection tempConnection = ConnectionFactory.getConnection();
		tempConnection.setSchema(schemaName);
		deleteStmt = tempConnection.prepareStatement(deleteSql);
	}

	@Spy
	private PreparedStatement readAllStmt;
	{
		Connection tempConnection = ConnectionFactory.getConnection();
		tempConnection.setSchema(schemaName);
		readAllStmt = tempConnection.prepareStatement(readAllSql);
	}

	@Spy
	private PreparedStatement readByUserStmt;
	{
		Connection tempConnection = ConnectionFactory.getConnection();
		tempConnection.setSchema(schemaName);
		readByUserStmt = tempConnection.prepareStatement(readByUserSql);
	}

	@Spy
	private PreparedStatement readForSupervisorStmt;
	{
		Connection tempConnection = ConnectionFactory.getConnection();
		tempConnection.setSchema(schemaName);
		readForSupervisorStmt = tempConnection.prepareStatement(readForSupervisorSql);
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		reimburseDao = new ReimbursementDAOImpl();
		reimburseList = new ArrayList<Reimbursement>();

		fakeUser = new User();
		fakeUser.setUsername("wrong");
		fakeUser.setPassword("wrong");
		fakeUser.setFirstName("Something");
		fakeUser.setLastName("Wrong");
		fakeUser.setRole(User.Role.EMPLOYEE);

		realUser = new User();
		realUser.setUsername("testuser");
		realUser.setPassword("password");
		realUser.setFirstName("Test");
		realUser.setLastName("User");
		realUser.setRole(User.Role.MANAGER);

		Reimbursement reimburse = new Reimbursement(1, Reimbursement.ReimbursementType.CERTIFICATION, 200.00,
				Reimbursement.ReimbursementStatus.PEND_DS, LocalDate.now(), LocalDate.now());
		reimburseList.add(reimburse);
		reimburse = new Reimbursement(2, Reimbursement.ReimbursementType.CERTIFICATION_PREPARATION_CLASSES, 200.00,
				Reimbursement.ReimbursementStatus.PEND_DS, LocalDate.now(), LocalDate.now());
		reimburseList.add(reimburse);
		reimburse = new Reimbursement(3, Reimbursement.ReimbursementType.OTHER, 200.00,
				Reimbursement.ReimbursementStatus.PEND_DS, LocalDate.now(), LocalDate.now());
		reimburseList.add(reimburse);
		reimburse = new Reimbursement(4, Reimbursement.ReimbursementType.SEMINARS, 200.00,
				Reimbursement.ReimbursementStatus.PEND_DS, LocalDate.now(), LocalDate.now());
		reimburseList.add(reimburse);
		reimburse = new Reimbursement(5, Reimbursement.ReimbursementType.TECHNICAL_TRAINING, 200.00,
				Reimbursement.ReimbursementStatus.PEND_DS, LocalDate.now(), LocalDate.now());
		reimburseList.add(reimburse);
		reimburse = new Reimbursement(6, Reimbursement.ReimbursementType.UNIVERSITY_COURSES, 200.00,
				Reimbursement.ReimbursementStatus.PEND_DS, LocalDate.now(), LocalDate.now());
		reimburseList.add(reimburse);

		when(conn.prepareStatement(createSql)).thenReturn(createStmt);
		when(conn.prepareStatement(readOneSql)).thenReturn(readOneStmt);
		when(conn.prepareStatement(updateSql)).thenReturn(updateStmt);
		when(conn.prepareStatement(deleteSql)).thenReturn(deleteStmt);
		when(conn.prepareStatement(readAllSql)).thenReturn(readAllStmt);
		when(conn.prepareStatement(readByUserSql)).thenReturn(readByUserStmt);
		when(conn.prepareStatement(readForSupervisorSql)).thenReturn(readForSupervisorStmt);

		reimburseDao.setConnection(conn);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = NullPointerException.class)
	public void createReimburseNullReimbursement() {
		reimburseDao.createReimbursement(null, realUser);
	}

	@Test(expected = NullPointerException.class)
	public void createReimburseNullUser() {
		reimburseDao.createReimbursement(reimburseList.get(1), null);
	}

	@Test
	public void createReimburseExisting() {
		assertFalse(reimburseDao.createReimbursement(reimburseList.get(0), realUser));
	}

	@Test
	public void createReimburseNew() {
		try {
			assertTrue(reimburseDao.createReimbursement(reimburseList.get(1), realUser));
			Mockito.verify(createStmt).setInt(1, reimburseList.get(1).getId());
			Mockito.verify(createStmt).setString(2, reimburseList.get(1).getType().name());
			Mockito.verify(createStmt).setDouble(3, reimburseList.get(1).getAmount());
			Mockito.verify(createStmt).setString(4, reimburseList.get(1).getStatus().name());
			Mockito.verify(createStmt).setDate(5, Date.valueOf(reimburseList.get(1).getDateCreated()));
			Mockito.verify(createStmt).setDate(6, Date.valueOf(reimburseList.get(1).getDateLastModified()));
			Mockito.verify(createStmt).setString(7, realUser.getUsername());
			Mockito.verify(createStmt).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void readReimburseOneDoesNotExist() {
		assertEquals("Should return null", null, reimburseDao.getReimbursementById(-1));
	}

	@Test
	public void readReimburseOneExists() {
		try {
			assertEquals("Should return " + reimburseList.get(0).toString(), reimburseList.get(0),
					reimburseDao.getReimbursementById(1));
			Mockito.verify(readOneStmt).executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = NullPointerException.class)
	public void updateReimburseNull() {
		reimburseDao.updateReimbursement(null);
	}

	@Test
	public void updateReimburseDoesNotExist() {
		Reimbursement reimburse = new Reimbursement(-1, Reimbursement.ReimbursementType.CERTIFICATION, 200.00,
				Reimbursement.ReimbursementStatus.PEND_DS, LocalDate.now(), LocalDate.now());
		assertFalse(reimburseDao.updateReimbursement(reimburse));
	}

	@Test
	public void updateReimburseExists() {
		reimburseList.get(0).setStatus(ReimbursementStatus.AWARDED);
		assertTrue(reimburseDao.updateReimbursement(reimburseList.get(0)));
		try {
			Mockito.verify(updateStmt).setDouble(1, reimburseList.get(0).getAmount());
			Mockito.verify(updateStmt).setString(2, reimburseList.get(0).getStatus().name());
			Mockito.verify(updateStmt).setDate(3, Date.valueOf(LocalDate.now()));
			Mockito.verify(updateStmt).setInt(4, reimburseList.get(0).getId());
			Mockito.verify(updateStmt).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void deleteReimburseDoesNotExist() {
		assertFalse(reimburseDao.deleteReimbursementById(-1));
	}

	@Test
	public void deleteReimburseExists() {
		assertTrue(reimburseDao.deleteReimbursementById(reimburseList.get(0).getId()));
		try {
			Mockito.verify(deleteStmt).setInt(1, reimburseList.get(0).getId());
			Mockito.verify(deleteStmt).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void readReimburseAll() {
		assertEquals("Should equal " + reimburseList.toString(), reimburseList, reimburseDao.getAllReimbursements());
		try {
			Mockito.verify(readAllStmt).executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Test(expected = NullPointerException.class)
	public void readReimburseByUserNull() {
		reimburseDao.getAllReimbursementsByUser(null);
	}

	@Test
	public void readReimburseByUserDoesNotExist() {
		assertEquals("Should return null", null, reimburseDao.getAllReimbursementsByUser(fakeUser));
	}

	@Test
	public void readReimburseByUserExists() {
		assertEquals("Should return " + reimburseList.toString(), reimburseList, reimburseDao.getAllReimbursementsByUser(realUser));
	}

	@Test(expected = NullPointerException.class)
	public void readReimburseForSupervisorNull() {

	}

	@Test
	public void readReimburseForSupervisorDoesNotExist() {

	}

	@Test
	public void readReimburseForSupervisorExists() {

	}

	public ReimbursementDaoTest() throws SQLException {

	}
}
