package com.example.aspect;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect//使用@Aspect注解将一个java类定义为切面类,通过@Pointcut指定切点,然后在执行切点之前和之后会执行@Before的切点前方法和切点后方法
/*
 * @Order(i)注解来标识切面的优先级,i的值越小，优先级越高。
 * 在切入点前的操作，按order的值由小到大执行
 * 在切入点后的操作，按order的值由大到小执行
 */
@Order(5)
@Component
public class WebLogAspect {
	
	private Logger logger=Logger.getLogger(getClass());
	
	ThreadLocal<Long> startTime=new ThreadLocal<Long>();//线程本地变量
	
	//使用@Pointcut定义一个切入点，可以是一个规则表达式，比如下例中某个package下的所有函数，也可以是一个注解等。
	@Pointcut("execution(public * com.example.web..*.*(..))")
	//execution为执行的意思，*代表任意返回值，然后是包名，.*意思是包下面的所有子包。(..)代表各种方法.
	public void webLog(){}
	
	//使用@Before在切入点开始处切入内容
	@Before("webLog()")    //JoinPoint  连接点
	public void doBefore(JoinPoint joinPoint) throws Throwable{
		startTime.set(System.currentTimeMillis());
		
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request=attributes.getRequest();
		
		//记录请求内容
		logger.info("URL: "+request.getRequestURL().toString());
		logger.info("HTTP_METHOD: "+request.getMethod());
		logger.info("IP: "+request.getRemoteAddr());
		logger.info("CLASS_METHOD: "+joinPoint.getSignature().getDeclaringTypeName()+
				"."+joinPoint.getSignature().getName());
		logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));//传入参数
	}
	
	//使用@AfterReturning在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
	@AfterReturning(returning="ret",pointcut="webLog()")
	public void doAfterReturning(Object ret) throws Throwable{
		logger.info("RESPONSE: "+ret);
		logger.info("SPEND TIME: "+(System.currentTimeMillis() - startTime.get()));
	}

}
