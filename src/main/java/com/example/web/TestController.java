package com.example.web;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bean.User;
import com.example.dao.JdbcTemplate.UserService;
import com.example.exception.MyException;
import com.example.mapper.UserMapper;

@Controller
public class TestController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserMapper userMapper;
	
	Logger log=Logger.getLogger(getClass());

	@RequestMapping("/")
	public String Index(ModelMap map){
		map.addAttribute("host", "abcdefg");
		return "index";
	}
	
	@RequestMapping("/exception")
	public String exception() throws Exception{
		throw new Exception("测试异常");
	}
	
	@RequestMapping("/jsonException")
	public String jsonException() throws Exception{
		throw new MyException("异常返回json数据信息");//抛出自定义异常
	}
	
	@RequestMapping("/log")
	@ResponseBody
	public String testLog(){
		log.info("******处理测试日志请求******");
		return "测试日志";
	}
	
	@RequestMapping("/getAC")
	@ResponseBody
	public String getAC(){
		return "test";
	}
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
	@RequestMapping("/Hello")
	public String Hello(){
		return "hello";
	}
	
	@RequestMapping("/hello")
	public String index(ModelMap map){
		map.addAttribute("host", "Hello World!!!");
		return "index";
	}
	
	@RequestMapping(value="/jdbc/getUser",method=RequestMethod.GET)
	public @ResponseBody User getUser(@RequestParam("name")String name){
		System.out.println("*****************"+name);
//		User user=new User();
//		user.setName("abc");
//		user.setAge(123);
//		userService.addUser(user);
		return userService.getUser(name);
	}
	
	@RequestMapping(value="/mapper/getUser",method=RequestMethod.GET)
	public @ResponseBody User get(@RequestParam("name")String name){
		return userMapper.getUser(name);
	}
}
