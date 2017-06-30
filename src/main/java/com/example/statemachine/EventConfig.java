package com.example.statemachine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

@WithStateMachine//以注解的方式配置监听器
public class EventConfig {
	
	private Logger log=LoggerFactory.getLogger(getClass());
	
	@OnTransition(source="UNPAY")
	public void create(){
		log.info("订单创建，待付款");
	}

	@OnTransition(source="UNPAY",target="WAITING_FOR_RECEIVE")
	public void pay(){
		 log.info("用户完成支付，待收货");
	}
	
	@OnTransition(source="WAITING_FOR_RECEIVE",target="DONE")
	public void receive(){
		 log.info("用户已收货，订单完成");
	}
}
