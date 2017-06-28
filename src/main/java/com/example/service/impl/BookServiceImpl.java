package com.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bean.Book;
import com.example.jpa.BookRepository;
import com.example.service.BookService;

@Service
public class BookServiceImpl implements BookService{
	@Autowired
	private BookRepository bookRepository;

	@Override
	public void testTransactional() {
		// TODO Auto-generated method stub
		bookRepository.save(new Book("c", 100));
		bookRepository.save(new Book("c++",100.1234f));
		bookRepository.save(new Book("python",1123100.12345f));
		bookRepository.save(new Book("node.js",100.12f));
	}

}
