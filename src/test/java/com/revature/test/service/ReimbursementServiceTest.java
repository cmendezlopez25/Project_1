package com.revature.test.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
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
import org.mockito.Spy;

import com.revature.dao.ReimbursementDAO;
import com.revature.pojo.Reimbursement;
import com.revature.service.ReimbursementServiceImpl;


@RunWith(MockitoJUnitRunner.class)
public class ReimbursementServiceTest {
	private ReimbursementServiceImpl reimburseService;
	private List<Reimbursement> reimburseList;
	
	@Mock
	ReimbursementDAO reimbursementDao;

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
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
