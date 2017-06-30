package com.example.other;

import java.util.concurrent.Future;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.example.DemoApplication;
import com.example.other.async.AsyncCallback;
import com.example.other.async.AsyncTask;
import com.example.other.async.Task;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=DemoApplication.class)
@WebAppConfiguration
public class AsyncTest {
	
	@Autowired
	public Task task;
	@Autowired
	public AsyncTask asyncTask;
	@Autowired
	public AsyncCallback callback;
	
//	@Test
	public void test() throws InterruptedException{
		task.doTaskOne();
		task.doTaskTwo();
		task.doTaskThree();
	}
	
	@Test
	public void asyncTest() throws InterruptedException{
		asyncTask.doTaskOne();
		asyncTask.doTaskTwo();
		asyncTask.doTaskThree();
		Thread.sleep(10000);
	}

//	@Test
	public void callback() throws InterruptedException{
		long start=System.currentTimeMillis();
		Future<String> task1=callback.doTaskOne();
		Future<String> task2=callback.doTaskTwo();
		Future<String> task3=callback.doTaskThree();
		while(true){
			if(task1.isDone()&&task2.isDone()&&task3.isDone()){
				break;
			}
			Thread.sleep(1000);
		}
		long end = System.currentTimeMillis();
		System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
	}
}
