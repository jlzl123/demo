package com.example.aspect.pointcut;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.example.bean.User;
@Service("userService")
public class UserService {

	private Logger  log=Logger.getLogger(getClass());
	
	public List<User> list(){
		return new ArrayList<User>();
	}
	
	public User getUser(Long id){
		log.info("根据id获取用户信息");
		return new User();
	}
	
	public void save(User user){
		log.info("保存用户信息");
	}
	
	public boolean delete(long id){
		log.info("根据id删除用户信息");
		return true;
	}
}
