package com.example.other.async;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncTask extends Task{

	@Async//声明函数为异步调用     @Async所修饰的函数不要定义为static类型，这样异步调用不会生效
	@Override
	public void doTaskOne() throws InterruptedException {
		// TODO Auto-generated method stub
		super.doTaskOne();
	}
	
	@Async
	@Override
	public void doTaskTwo() throws InterruptedException {
		// TODO Auto-generated method stub
		super.doTaskTwo();
	}
	
	@Async
	@Override
	public void doTaskThree() throws InterruptedException {
		// TODO Auto-generated method stub
		super.doTaskThree();
	}
}
