package com.example.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="name",length=20,nullable=false,unique=true)
	private String name;
	@Column(name="price",length=10,nullable=false,unique=false)
	private float price;
	
	public Book() {
		// TODO Auto-generated constructor stub
	}
	
	public Book(String name,float price){
		this.name=name;
		this.price=price;
	}
	
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
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}		
}
