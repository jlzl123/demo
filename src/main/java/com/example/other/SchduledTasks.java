package com.example.other;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchduledTasks {
	Logger log=Logger.getLogger(getClass());
	
	private static final SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss E");
	
	@Scheduled(fixedRate=60000)//上一次开始执行时间点之后60秒再执行
	public void reportCunrrentTIme(){
		System.out.println("现在时间是："+dateFormat.format(new Date()));
		log.info(dateFormat.format(new Date()));
	}

}
