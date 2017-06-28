package com.example;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.example.bean.User;
import com.example.bean.UserState;
import com.example.mapper.UserMapper;

@RunWith(SpringJUnit4ClassRunner.class)//指定使用的单元测试执行类,SpringJUnit4ClassRunner让测试运行于Spring测试环境
@SpringApplicationConfiguration(classes=DemoApplication.class)
@WebAppConfiguration//要加上这个，不然一直报错Error creating bean with name 'webMvcRequestHandlerProvider'
public class UserMapperTest {

	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void test(){
		User user=new User();
		user.setName("阿萨德");
		user.setAge(30);
//		userMapper.addUser(user);
		Assert.assertEquals(userMapper.getUser("zzq").getName(), "zzq");
//		userMapper.delete("李四");
		List<User> users=userMapper.getAllUser();
		for(User u:users){
			System.out.println(u.getId()+" "+u.getName()+" "+u.getAge());
		}
		List<UserState> uss=userMapper.getUserInfo();
		for(UserState us:uss){
			System.out.println(us.getId()+" "+us.getAddress()+" "+us.getHobby()+" "+us.getBirthDate());
		}
	}
}
