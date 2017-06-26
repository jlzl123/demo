package com.example;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



import com.example.bean.UserBean;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
public class BeanParamTest {
	
	@Autowired
	private UserBean user;

	@Test
	public void getUserBean(){
		Assert.assertEquals(user.getName(), "张三");
		Assert.assertEquals(user.getPass(), "admin");
	}
}
