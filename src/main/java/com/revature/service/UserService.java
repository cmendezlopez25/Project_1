package com.revature.service;

import com.revature.pojo.User;

public interface UserService {
	public User loginUser(String username, String password);
	public void updateUser(User user);
	public boolean deleteUser(User user);
}
