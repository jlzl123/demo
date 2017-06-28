package com.example.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
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
	
	@Test
	public void test(){
//		Assert.assertEquals(userRepository.findByName("zzq").getName(), "zzq");
		System.out.println(bookRepository.findByName("java").getPrice());
//		Assert.assertEquals(bookRepository.findByName("java").getPrice(),10.0f);
//		Assert.assertEquals(10.0f, bookRepository.findByName("java").getPrice(), 1);
		Book book=new Book();
		book.setId(1l);
		book.setName("java");
		book.setPrice(10);
//		Assert.assertEquals(bookRepository.findByName("java").getName(), "java");
		Assert.assertEquals(bookRepository.findBook("java").getName(),"java");
	}
}
