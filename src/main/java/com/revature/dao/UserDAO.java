package com.revature.dao;

import java.util.List;

import com.revature.pojo.User;

public interface UserDAO {
	public void createUser(User user);
	public User readUser(String username);
	public void updateUser(User user);
	public boolean deleteUser(User user);
	public List<User> readAllUsers();
}
