package com.example.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect//声明这是一个切面bean
@Order(5)//配置优先级
@Component//声明这是一个组件
public class UserServiceAspect {
	
	private Logger log=Logger.getLogger(getClass());
//	@Pointcut("execution(public * com.example.web..*.*(..))")
	//配置切入点,下面的方法名就是切入点的名称
	@Pointcut("execution(public * com.example.aspect.pointcut..*(..))")
	public void UserAspect(){}
	
	@Pointcut("execution(public com.example.bean.User com.example.aspect.pointcut..*(java.lang.Long))")
	public void method(){}
	
	@Before("UserAspect()")
	public void before(JoinPoint joinPoint){
		log.info("UserAspect()切入点的前置通知:"+joinPoint);
	}
	
	@After("UserAspect()")
	public void after(JoinPoint joinPoint){
		log.info("UserAspect()切入点的后置通知:"+joinPoint);
	}
	
	@AfterReturning("UserAspect()")
	public void afterReturn(JoinPoint joinPoint){
		log.info("UserAspect()切入点的返回后通知:"+joinPoint);
	}
	
	@Around("UserAspect()")
	public void around(JoinPoint joinPoint){
		log.info("UserAspect()切入点的环绕通知:"+joinPoint);
		Long startTime=System.currentTimeMillis();
		try {
			((ProceedingJoinPoint)joinPoint).proceed();
			Long endTime=System.currentTimeMillis();
			log.info("around " + joinPoint + "\tUse time : " + (endTime - startTime) + " ms!");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//配置抛出异常后通知,使用在方法aspect()上注册的切入点
	@AfterThrowing(pointcut="UserAspect()",throwing="e")
	public void afterThrowing(JoinPoint joinPoint,Exception e){
		log.info("UserAspect()切入点的抛出异常后通知:"+joinPoint+"\t"+e.getMessage());
	}
	
	@Before("method()&&args(id)")//取得切入点的参数
	public void MethodBefore(JoinPoint joinPoint,Long id){
		log.info(id+"  method()切入点的前置通知:"+joinPoint);
	}
	
}
