package com.revature.service;

import static com.revature.util.LoggerUtil.log;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.revature.dao.ReimbursementDAO;
import com.revature.dao.ReimbursementDAOImpl;
import com.revature.pojo.Reimbursement;
import com.revature.pojo.User;

public class ReimbursementServiceImpl implements ReimbursementService {
	
	private ReimbursementDAO reimburseDao = new ReimbursementDAOImpl();
	private String folderPath = "../../resources/";

	@Override
	public boolean createReimbursement(Reimbursement reimburse, List<File> attachments) {
		if (reimburse == null || attachments == null) {
			throw new NullPointerException();
		}
		
		if(reimburseDao.createReimbursement(reimburse) == true && addAttachmentsToReimbursementById(reimburse.getId(), attachments)) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public Reimbursement getReimbursementById(int id) {
		return reimburseDao.getReimbursementById(id);
	}

	@Override
	public boolean updateReimbursement(Reimbursement reimburse) {
		if (reimburse == null) {
			throw new NullPointerException();
		}
		
		if (reimburseDao.updateReimbursement(reimburse) == true) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean deleteReimbursementById(int id) {
		return reimburseDao.deleteReimbursementById(id);
	}

	@Override
	public List<Reimbursement> getReimbursementsByUser(User user) {
		if (user == null) {
			throw new NullPointerException();
		}
		
		return reimburseDao.getAllReimbursementsByUser(user);
	}

	@Override
	public List<Reimbursement> getReimbursementsForSupervisor(User user) {
		if (user == null) {
			throw new NullPointerException();
		}
		return reimburseDao.getAllReimbursementsForSupervisor(user);
	}

	@Override
	public List<Reimbursement> getAllReimbursements() {
		return reimburseDao.getAllReimbursements();
	}
	
	public boolean addAttachmentsToReimbursementById(int id, List<File> attachments) {
		if (attachments == null) {
			throw new NullPointerException();
		}
		
		log.debug(folderPath);
		
		String fullPath = folderPath + id + "/";
		File directory = new File(fullPath);
		if (!directory.exists()) {
			directory.mkdir();
		}
		
		for (File file : attachments) {
			String fullFileName = fullPath + file.getName();
			if (file.renameTo(new File(fullFileName)) == false) {
				File existingFile = new File(fullFileName);
				existingFile.delete();
				file.renameTo(new File(fullFileName));
			}
		}
		
		return true;
	}
	
	public List<File> getReimbursementAttachmentsById(int id){
		File directory = new File(folderPath + id);
		if (!directory.exists()) {
			return null;
		}
		return Arrays.asList(directory.listFiles());
	}
	
	public List<File> getReimbursementAttachmentsByUser(User user){
		//TODO
		return null;
	}

	public void setReimbursementDao(ReimbursementDAO reimburseDao) {
		this.reimburseDao = reimburseDao;
	}
	
	public void setFolderPath(String folderPath) {
		this.folderPath = folderPath;
	}
}
