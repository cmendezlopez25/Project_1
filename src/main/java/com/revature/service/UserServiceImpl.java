package com.revature.service;

import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.pojo.Reimbursement;
import com.revature.pojo.User;

public class UserServiceImpl implements UserService {
	private UserDAO userDao = new UserDAOImpl();
	private ReimbursementService reimbServe = new ReimbursementServiceImpl();

	@Override
	public User loginUser(String username, String password) {
		if (username == null || password == null) {
			throw new NullPointerException();
		}
		
		User user = null;
		
		user = userDao.readUser(username);
		if (user != null) {
			if (!user.getPassword().equals(password)) {
				user = null;
			}
		}
		
		return user;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	public void setDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	@Override
	public User getUserByReimbId(int id) {
		Reimbursement reimb = reimbServe.getReimbursementById(id);
		return userDao.readUser(reimb.getOwnerUserName());
	}
}
