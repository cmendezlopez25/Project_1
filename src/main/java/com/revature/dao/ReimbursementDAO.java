package com.revature.dao;

import java.util.List;

import com.revature.pojo.Reimbursement;
import com.revature.pojo.User;

public interface ReimbursementDAO {
	public boolean createReimbursement(Reimbursement reimburse, User user);
	public Reimbursement getReimbursementById(int id);
	public boolean updateReimbursement(Reimbursement reimburse);
	public boolean deleteReimbursementById(int id);
	public List<Reimbursement> getAllReimbursements();
	public List<Reimbursement> getAllReimbursementsByUser(User user);
	public List<Reimbursement> getAllReimbursementsForSupervisor(User user);
	
}
