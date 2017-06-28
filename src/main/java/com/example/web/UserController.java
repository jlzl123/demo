package com.example.web;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bean.User;
import com.example.exception.MyException;

@RestController
@RequestMapping("/users")
public class UserController {

	static Map<Long, User> users=new ConcurrentHashMap<Long, User>();
	
	@ApiOperation(value="获取用户列表",notes="")
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public List<User> getUserList(){
		List<User> list=new ArrayList<User>(users.values());
		return list;
	}
	
	@ApiOperation(value="创建用户",notes="根据User对象创建用户")
	@ApiImplicitParam(name="user",value="用户详细实体user",required=true,dataType="User")
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String postUser(@RequestBody User user){
		System.out.println("*********************"+user.getName());
		users.put(user.getId(), user);
		return "success";
	}
	
//	@RequestMapping(value="/a",method=RequestMethod.POST)
//	public String postUser(@RequestParam("id")Long id,
//			@RequestParam("name")String name,@RequestParam("age")Integer age){
//		System.out.println("*****************"+id+name+age);
//		User user=new User();
//		user.setId(id);
//		user.setName(name);
//		user.setAge(age);
//		users.put(id, user);
//		return "success";
//	}
	
	@ApiOperation(value="获取用户详细信息",notes="根据url的id来获取用户详细信息")
	//使用@PathVariable注解的参数，要在@ApiImplicitParam中设置paramType = path，不然会对值进行转义放在url上。测试出错
	@ApiImplicitParam(name="id",value="用户",required=true,dataType="Long",paramType="path")
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public User getUser(@PathVariable Long id){
		return users.get(id);
	}
	
	@ApiOperation(value="更新用户详细信息",notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name="id",value="用户ID",required=true,dataType="Long"),
			@ApiImplicitParam(name="user",value="用户详细实体user",required=true,dataType="User")
	})
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String putUser(@PathVariable Long id,@ModelAttribute User user){
		//@PathVariable 用来获得请求url中的动态参数的
		//@ModelAttribute 从Form表单或URL参数中获取（实际上，不做此注释也能拿到user对象）
		User u=users.get(id);
		u.setName(user.getName());
		u.setAge(user.getAge());
		users.put(id, u);
		return "success";
	}
	
    @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String deleteUser(@PathVariable Long id){
		users.remove(id);
		return "success";
	}
}
