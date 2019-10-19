package com.revature.service;

import java.util.List;

import com.revature.pojo.Reimbursement;
import com.revature.pojo.User;

public interface ReimbursementService {
	public Reimbursement getReimbursementById(int id);
	public void createReimbursement(Reimbursement reimburse);
	public void updateReimbursement(Reimbursement reimburse);
	public boolean deleteReimbursementById(int id);
	public List<Reimbursement> getReimbursementsByUser(User user);
	public List<Reimbursement> getReimbursementsForSupervisor(User user);
	public List<Reimbursement> getAllReimbursements();
	
}
