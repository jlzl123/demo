package com.example.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(1)
@Component
public class TestAspect {

	@Pointcut("execution(public * com.example.web..*.*(..))")
	public void anyMethod(){}
	
	@Before("anyMethod()")
	public void doBefo(){
		System.out.println("优秀级大的在切入点前先执行");
	}

	@AfterReturning("anyMethod()")
	public void afterReturn(){
		System.out.println("return之后执行，在After之前");
	}
	
	@After("anyMethod()")
	public void doAfter(){
		System.out.println("优先级大的在切入点后后执行");
	}
}
