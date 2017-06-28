package com.example.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.example.DemoApplication;
import com.example.bean.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=DemoApplication.class)
@WebAppConfiguration
public class RedisTest {

	@Autowired 
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	private RedisTemplate<String, User> redisTemplate;//可以存入对象的模版 
	
	@Test
	public void test(){
		stringRedisTemplate.opsForValue().set("zzq", "ch");
		Assert.assertEquals(stringRedisTemplate.opsForValue().get("zzq"), "ch");
		
		User user=new User();
		user.setId(111L);
		user.setName("zzq");
		user.setAge(20);
		redisTemplate.opsForValue().set("超级吃货", user);
		Assert.assertEquals(redisTemplate.opsForValue().get("超级吃货").getName(),"zzq");
	}
}
