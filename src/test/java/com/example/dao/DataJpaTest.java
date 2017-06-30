package com.example.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.example.DemoApplication;
import com.example.bean.Book;
import com.example.jpa.BookRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=DemoApplication.class)
@WebAppConfiguration
public class DataJpaTest {

//	@Autowired
//	private UserRepository userRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private CacheManager cacheManager;
	
	@Before
	public void before(){
		bookRepository.save(new Book("AAAA", 10));
	}
	
	@Test
	public void test(){
//		Assert.assertEquals(bookRepository.findBook("java").getName(),"java");
		
		Book book=bookRepository.findByName("AAAA");
		System.out.println("第一次查询："+book.getPrice());
		
		Book b=bookRepository.findByName("AAAA");
		System.out.println("第二次查询："+b.getPrice());
		
		book.setPrice(200);
		bookRepository.save(book);
		
		Book b3=bookRepository.findByName("AAAA");
		System.out.println("第三次查询："+b3.getPrice());
	}
}
