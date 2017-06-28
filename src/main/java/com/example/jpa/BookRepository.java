package com.example.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.bean.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

	//spring-data-spring通过解析方法名创建查询
	public Book findByName(String name);
	
	//也可以自己写JPQL语句，注意Book是实体类名
	@Query("from Book b where b.name=:name")
	public Book findBook(@Param("name") String name);
}
