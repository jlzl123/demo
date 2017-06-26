package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bean.UserBean;

//原来在@Controller中返回json需要@ResponseBody来配合，
//如果直接用@RestController替代@Controller就不需要再配置@ResponseBody，默认返回json格式。这时视图解析器不起作用。
@RestController
public class HelloController {
	@Autowired
	private UserBean user;
	
	@RequestMapping("/getUser")
	public String getUser(){
		return "username:"+user.getName()+"\npassword:"+user.getPass()+user.getPort()+"  "+user.getNumber();
	}
}
