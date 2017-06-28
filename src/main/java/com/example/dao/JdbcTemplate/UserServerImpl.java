package com.example.dao.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.bean.User;
@Service
public class UserServerImpl implements UserService{

	@Autowired
	private JdbcTemplate jdbc;
	
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		jdbc.update("INSERT INTO user(name,age) values(?,?)",user.getName(),user.getAge());
	}

	@Override
	public void deleteUser(String name) {
		// TODO Auto-generated method stub
		jdbc.update("DELETE FROM user WHERE name=?", name);
	}

	@Override
	public User getUser(String name) {
		// TODO Auto-generated method stub
		//queryForObject只返回一个字段
		Integer age=jdbc.queryForObject("SELECT count(2) FROM user WHERE name ='"+name+"'",Integer.class);
		User user=new User();
		user.setName(name);
		user.setAge(age);
		return user;
	}
	
	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		List<User> users=new ArrayList<User>();
		jdbc.queryForList("SELECT * FROM user", users);
		return users;
	}

}
