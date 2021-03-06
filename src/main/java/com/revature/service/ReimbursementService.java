package com.revature.service;

import java.io.File;
import java.util.List;

import com.revature.pojo.Reimbursement;
import com.revature.pojo.User;

public interface ReimbursementService {
	public boolean createReimbursement(Reimbursement reimburse, List<File> attachments);
	public Reimbursement getReimbursementById(int id);
	public boolean updateReimbursement(Reimbursement reimburse);
	public boolean deleteReimbursementById(int id);
	public List<Reimbursement> getReimbursementsByUser(User user);
	public List<Reimbursement> getReimbursementsForSupervisor(User user);
	public List<Reimbursement> getAllReimbursements();
	public boolean addAttachmentsToReimbursementById(int id, List<File> attachments);
	public List<File> getReimbursementAttachmentsById(int id);
	public List<File> getReimbursementAttachmentsByUser(User user);
}
