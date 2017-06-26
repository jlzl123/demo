package com.example.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
//全局异常处理类
@ControllerAdvice//定义统一的异常处理类
public class GlobalExceptionHandler {

	public static final String DEFAULT_ERROR_VIEW="error";
	
	@ExceptionHandler(value=Exception.class)//用来定义函数针对的异常类型，根据控制器方法抛出的异常类型找到相应的处理方法
	public ModelAndView defaultErrorHandler(HttpServletRequest req,Exception e) throws Exception {
		ModelAndView model=new ModelAndView();
		model.addObject("exception", e);
		model.addObject("url", req.getRequestURL());
		model.setViewName(DEFAULT_ERROR_VIEW);
		return model;
	}
	
	@ExceptionHandler(value=MyException.class)
	public @ResponseBody ErrorInfo<String> jsonErrorHandler(HttpServletRequest req,MyException e) throws Exception{
		ErrorInfo<String> r=new ErrorInfo<String>();
	    r.setMessage(e.getMessage());
	    r.setCode(ErrorInfo.ERROR);
	    r.setData("some data");
	    r.setUrl(req.getRequestURL().toString());
		return r;
	}
}
