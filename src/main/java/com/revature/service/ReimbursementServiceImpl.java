package com.revature.service;

import java.util.List;

import com.revature.dao.ReimbursementDAO;
import com.revature.dao.ReimbursementDAOImpl;
import com.revature.pojo.Reimbursement;
import com.revature.pojo.User;

public class ReimbursementServiceImpl implements ReimbursementService {
	
	private ReimbursementDAO reimburseDao = new ReimbursementDAOImpl();

	@Override
	public Reimbursement getReimbursementById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createReimbursement(Reimbursement reimburse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateReimbursement(Reimbursement reimburse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean deleteReimbursementById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Reimbursement> getReimbursementsByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> getReimbursementsForSupervisor(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> getAllReimbursements() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setReimbursementDao(ReimbursementDAO reimburseDao) {
		this.reimburseDao = reimburseDao;
	}
}
