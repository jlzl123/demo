package com.example.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.example.bean.User;

@Configuration//标志配置类，相当于beans，与bean配用
public class RedisConfig {

	@Bean//将方法返回值注入到ioc容器中
	JedisConnectionFactory jedisConnectionFactory(){
		return new JedisConnectionFactory();
	}
	
//	@Bean
	public RedisTemplate<String, User> redisTemplate(RedisConnectionFactory factory){
		RedisTemplate<String, User> redisTemplate=new RedisTemplate<String, User>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new RedisObjectSerializer());
		return redisTemplate;
	}
}
