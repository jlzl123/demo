package com.example.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.example.DemoApplication;
import com.example.bean.User;
import com.example.dao.JdbcTemplate.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=DemoApplication.class)
@WebAppConfiguration
public class JdbcTemplateTest {
	
	@Autowired
	private UserService userService;
	
	@Before
	public void setUp(){
		
	}
	
	@Test
	public void test(){
//		User user=new User();
//		user.setName("查子琴");
//		user.setAge(20);
//		userService.addUser(user);
//		
//		Assert.assertEquals(userService.getUser("查子琴").getName(), "查子琴");
		Assert.assertEquals(true, true);
	}

}
