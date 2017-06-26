package com.example.aspect;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.aspect.pointcut.UserService;

@Controller
public class TestAspectController {
	
	@Resource
	private UserService userService;

	@RequestMapping("/aspect")
	@ResponseBody
	public String aspect(){
		userService.getUser(1L);
		return "abc";
	}
}
