package com.example.dao.JdbcTemplate;

import java.util.List;

import com.example.bean.User;


public interface UserService {

	public void addUser(User user);
	
	public User getUser(String name);
	
	public void deleteUser(String name);
	
	public List<User> getAllUser();
}
