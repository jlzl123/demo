package com.example.statemachine;


import java.util.EnumSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigBuilder;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.transition.Transition;

@Configuration
@EnableStateMachine//启用Spring StateMachine状态机功能
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events>{

	private Logger log=LoggerFactory.getLogger(getClass());
	
	@Override//初始化当前状态机拥有哪些状态
	public void configure(StateMachineStateConfigurer<States, Events> states)
			throws Exception {
		// TODO Auto-generated method stub
		states.withStates()
		      .initial(States.UNPAY)//定义了初始状态
		      .states(EnumSet.allOf(States.class));//指定了使用上一步中定义的所有状态作为该状态机的状态定义。
	}
	
	@Override//初始化当前状态机有哪些状态迁移动作
	public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
			throws Exception {
		// TODO Auto-generated method stub
		transitions
		     .withExternal()//每一个迁移动作，都有来源状态source，目标状态target以及触发事件event
		           .source(States.UNPAY).target(States.WAITING_FOR_RECEIVE)
		           .event(Events.PAY)
		           .and()
		     .withExternal()
		           .source(States.WAITING_FOR_RECEIVE).target(States.DONE)
		           .event(Events.RECEIVE);
		           
		           
	}
	
    @Override
    public void configure(StateMachineConfigurationConfigurer<States, Events> config)
    		throws Exception {
    	// TODO Auto-generated method stub
    	//为当前的状态机指定了状态监听器，也可以以注解的方式配置监听器
//    	config.withConfiguration().listener(listener());
    }
	
	@Bean//状态机监听器,可以单独写一个类，然后注入
	public StateMachineListener<States, Events> listener(){
		StateMachineListenerAdapter<States, Events> listener=
				new StateMachineListenerAdapter<States, Events>(){
			
			@Override
			public void transition(Transition<States, Events> transition) {
				// TODO Auto-generated method stub
				States state=transition.getTarget().getId();
				if(state==States.UNPAY){
					log.info("订单创建，待付款");
					return;
				}
				
				States resourceState=transition.getSource().getId();
				if(resourceState==States.UNPAY&&state==States.WAITING_FOR_RECEIVE){
					log.info("用户完成支付，待收货");
					return;
				}
				
				if(resourceState==States.WAITING_FOR_RECEIVE&&state==States.DONE){
					log.info("用户已收货，订单完成");
					return;
				}
			}     		
		};
		return listener;
	}
}
