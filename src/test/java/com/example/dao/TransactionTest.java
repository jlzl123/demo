package com.example.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.DemoApplication;
import com.example.bean.Book;
import com.example.jpa.BookRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=DemoApplication.class)
@WebAppConfiguration
public class TransactionTest {

	@Autowired
	private BookRepository bookRepository;
	
	@Test
	/*
	 * 事务配置,操作出错执行回滚，通常在service层接口中使用@Transactional来对各个业务逻辑进行事务管理的配置
	 * 可通过参数配置隔离级别和传播行为
	 */
	@Transactional
	public void test(){
		bookRepository.save(new Book("c", 100));
		bookRepository.save(new Book("c++",100.1234f));
		bookRepository.save(new Book("python",1123100.12345f));
		bookRepository.save(new Book("node.js",100.12f));
	}
}
