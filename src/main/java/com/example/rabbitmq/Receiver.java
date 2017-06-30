package com.example.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues="hello")//定义该类对hello队列的监听
public class Receiver {
	
	@RabbitHandler//注解来指定对消息的处理方法。
	public void process(String message){
		System.out.println("Receiver:"+message);
	}

}
