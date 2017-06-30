package com.example.rabbitmq;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 * 消息生产者
 */
@Component
public class Sender {

	@Autowired
	private AmqpTemplate rabbitmqTemplate;//通过注入AmqpTemplate接口的实例来实现消息的发送
	
	public void send(){
		String content="hello"+new Date();
		System.out.println("Sender:"+content);
		rabbitmqTemplate.convertAndSend("hello", content);
	}
}
