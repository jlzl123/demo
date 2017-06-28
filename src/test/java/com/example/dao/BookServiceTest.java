package com.example.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.example.DemoApplication;
import com.example.service.BookService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=DemoApplication.class)
@WebAppConfiguration
public class BookServiceTest {
	@Autowired
	private BookService bookService;
	
	@Test
	public void test(){
		bookService.testTransactional();
	}
}
