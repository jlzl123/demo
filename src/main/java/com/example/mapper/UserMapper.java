package com.example.mapper;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

import com.example.bean.User;
import com.example.bean.UserState;

//@Mapper
public interface UserMapper {

	@Insert("INSERT INTO user(name,age) VALUES(#{name},#{age})")
	public void addUser(User user);
	
	@Delete("DELETE FROM user WHERE name=#{value}")
	public void delete(String name);
	
	@Select("SELECT * FROM user WHERE name=#{value}")
	public User getUser(String name);
	
	@Select("SELECT * FROM user")
	public List<User> getAllUser();
	
	@Results({
		@Result(property="id",column="id",javaType=Long.class),
		@Result(property="birthDate",column="birthDate",javaType=Date.class),
		@Result(property="address",column="address",javaType=String.class),
		@Result(property="hobby",column="hobby",javaType=String.class)
	})
	@Select("SELECT * From user,user_state Where user.id=user_state.id")
	public List<UserState> getUserInfo();
}
