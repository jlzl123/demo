package com.example.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	@Id
//	@GeneratedValue(strategy= GenerationType.IDENTITY)
//	@Column(nullable=false)
	private Long id;
//	@Column(nullable=false)
	private String name;
//	@Column(nullable=false)
	private Integer age;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
    
}
