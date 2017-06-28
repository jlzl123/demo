package com.example.service;

import org.springframework.transaction.annotation.Transactional;

public interface BookService {

	@Transactional//事务配置,操作出错执行回滚，通常在service层接口中使用@Transactional来对各个业务逻辑进行事务管理的配置
	public void testTransactional();
}
