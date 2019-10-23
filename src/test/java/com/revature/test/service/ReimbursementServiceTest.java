package com.revature.test.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.revature.dao.ReimbursementDAO;
import com.revature.pojo.Reimbursement;
import com.revature.pojo.User;
import com.revature.service.ReimbursementServiceImpl;


@RunWith(MockitoJUnitRunner.class)
public class ReimbursementServiceTest {
	private ReimbursementServiceImpl reimburseService;
	private List<Reimbursement> reimburseList;
	private User fakeUser;
	private User realUser;
	private String folderPath = "./src/test/resources/";
	private List<File> allFiles;
	
	@Mock
	ReimbursementDAO reimburseDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		reimburseService = new ReimbursementServiceImpl();
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

		Reimbursement reimburse = new Reimbursement(1, realUser.getUsername(), Reimbursement.ReimbursementType.CERTIFICATION, 200.00, Reimbursement.ReimbursementStatus.PEND_DS, LocalDate.now(), LocalDate.now());
		reimburseList.add(reimburse);
		reimburse = new Reimbursement(2, realUser.getUsername(), Reimbursement.ReimbursementType.CERTIFICATION_PREPARATION_CLASSES, 200.00, Reimbursement.ReimbursementStatus.PEND_DS, LocalDate.now(), LocalDate.now());
		reimburseList.add(reimburse);
		reimburse = new Reimbursement(3, realUser.getUsername(), Reimbursement.ReimbursementType.OTHER, 200.00, Reimbursement.ReimbursementStatus.PEND_DS, LocalDate.now(), LocalDate.now());
		reimburseList.add(reimburse);
		reimburse = new Reimbursement(4, realUser.getUsername(), Reimbursement.ReimbursementType.SEMINARS, 200.00, Reimbursement.ReimbursementStatus.PEND_DS, LocalDate.now(), LocalDate.now());
		reimburseList.add(reimburse);
		reimburse = new Reimbursement(5, realUser.getUsername(), Reimbursement.ReimbursementType.TECHNICAL_TRAINING, 200.00, Reimbursement.ReimbursementStatus.PEND_DS, LocalDate.now(), LocalDate.now());
		reimburseList.add(reimburse);
		reimburse = new Reimbursement(6, realUser.getUsername(), Reimbursement.ReimbursementType.UNIVERSITY_COURSES, 200.00, Reimbursement.ReimbursementStatus.PEND_DS, LocalDate.now(), LocalDate.now());
		reimburseList.add(reimburse);

		when(reimburseDao.createReimbursement(reimburseList.get(0))).thenReturn(false);
		when(reimburseDao.createReimbursement(reimburseList.get(1))).thenReturn(true);
		when(reimburseDao.getReimbursementById(0)).thenReturn(null);
		when(reimburseDao.getReimbursementById(1)).thenReturn(reimburseList.get(0));
		when(reimburseDao.updateReimbursement(reimburseList.get(0))).thenReturn(false);
		when(reimburseDao.updateReimbursement(reimburseList.get(1))).thenReturn(true);
		when(reimburseDao.deleteReimbursementById(0)).thenReturn(false);
		when(reimburseDao.deleteReimbursementById(1)).thenReturn(true);
		when(reimburseDao.getAllReimbursementsByUser(fakeUser)).thenReturn(null);
		when(reimburseDao.getAllReimbursementsByUser(realUser)).thenReturn(reimburseList);
		when(reimburseDao.getAllReimbursementsForSupervisor(fakeUser)).thenReturn(null);
		when(reimburseDao.getAllReimbursementsForSupervisor(realUser)).thenReturn(reimburseList);
		reimburseService.setReimbursementDao(reimburseDao);
		
		reimburseService.setFolderPath(folderPath);
		
		allFiles = Arrays.asList(new File(folderPath + "1").listFiles());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = NullPointerException.class)
	public void createReimbursementNullReimbursement() {
		reimburseService.createReimbursement(null);
	}
	
	@Test
	public void createReimbursementExisting() {
		assertFalse(reimburseService.createReimbursement(reimburseList.get(0)));
	}
	
	@Test
	public void createReimbursementNew() {
		assertTrue(reimburseService.createReimbursement(reimburseList.get(1)));
	}
	
	@Test
	public void getReimbursementByIdIncorrect() {
		assertEquals("Should be null", null, reimburseService.getReimbursementById(0));
	}
	
	@Test
	public void getReimbursementByIdCorrect() {
		assertEquals("Should return " + reimburseList.get(0), reimburseList.get(0), reimburseService.getReimbursementById(1));
	}
	
	@Test(expected = NullPointerException.class)
	public void updateReimbursementNull() {
		reimburseService.updateReimbursement(null);
	}
	
	@Test
	public void updateReimbursementDoesNotExist() {
		assertFalse(reimburseService.updateReimbursement(reimburseList.get(0)));
	}
	
	@Test
	public void updateReimbursementExists() {
		assertTrue(reimburseService.updateReimbursement(reimburseList.get(1)));
	}
	
	@Test
	public void deleteReimbursementByIdIncorrect() {
		assertFalse(reimburseService.deleteReimbursementById(0));
	}
	
	@Test
	public void deleteReimbursementByIdCorrect() {
		assertTrue(reimburseService.deleteReimbursementById(1));
	}
	
	@Test(expected = NullPointerException.class)
	public void getReimbursementsByUserNull() {
		reimburseService.getReimbursementsByUser(null);
	}
	
	@Test
	public void getReimbursementsByUserDoesNotExist() {
		assertEquals("Should return null", null, reimburseService.getReimbursementsByUser(fakeUser));
	}
	
	@Test
	public void getReimbursementsByUserExists() {
		assertEquals("Should return " + reimburseList.toString(), reimburseList , reimburseService.getReimbursementsByUser(realUser));
	}
	
	@Test(expected = NullPointerException.class)
	public void getReimbursementsForSupervisorNull() {
		reimburseService.getReimbursementsForSupervisor(null);
	}
	
	@Test
	public void getReimbursementsForSupervisorDoesNotExist() {
		assertEquals("Should return null", null, reimburseService.getReimbursementsForSupervisor(fakeUser));
	}
	
	@Test
	public void getReimbursementsForSupervisorExists() {
		assertEquals("Should return " + reimburseList.toString(), reimburseList , reimburseService.getReimbursementsForSupervisor(realUser));
	}
	
	@Test
	public void getAllReimbursements() {
		reimburseService.getAllReimbursements();
		Mockito.verify(reimburseDao).getAllReimbursements();
	}
	
	@Test
	public void getAllAttachmentsByIdDoesNotExist() {
		assertEquals("Should return null", null, reimburseService.getReimbursementAttachmentsById(0));
	}
	
	@Test
	public void getAllAttachmentsByIdExists() {
		assertEquals("Should return " + allFiles.toString(), allFiles, reimburseService.getReimbursementAttachmentsById(1));
	}
}
