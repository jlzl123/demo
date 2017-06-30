package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;



@SpringBootApplication
@MapperScan(basePackages="com.example.mapper")
@EnableCaching//开启缓存功能。    会和redisTemplate注册bean注解冲突报错，未解决。
@EnableScheduling//启用定时任务配置
@EnableAsync//开启异步配置
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext ac=SpringApplication.run(DemoApplication.class, args);
	}
}
