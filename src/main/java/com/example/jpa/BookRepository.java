package com.example.jpa;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.bean.Book;
@CacheConfig(cacheNames="book")//配置缓存
public interface BookRepository extends JpaRepository<Book, Long>{
    
	//spring-data-spring通过解析方法名创建查询
	@Cacheable//@Cacheable是用来声明方法是可缓存的
	public Book findByName(String name);
	
	//也可以自己写JPQL语句，注意Book是实体类名
	@Query("from Book b where b.name=:name")
	public Book findBook(@Param("name") String name);
	
	@CachePut(key="#p0.name")//让数据更新操作同步到缓存中
	public Book save(Book book);
}
