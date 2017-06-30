package com.example.other.mail;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.example.DemoApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=DemoApplication.class)
@WebAppConfiguration
public class SendMailTest {
	/*
	 * Spring Boot的starter模块提供了自动化配置，所以在引入了spring-boot-starter-mail依赖之后，
	 * 会根据配置文件中的内容去创建JavaMailSender实例
	 */
	@Autowired
	public JavaMailSender mailSender;
	@Autowired
	public VelocityEngine velocityEngine;//用于设置模版邮件
	
//	@Test
	public void test(){
		SimpleMailMessage mail=new SimpleMailMessage();
		mail.setFrom("3368436201@qq.com");
		mail.setTo("528797998@qq.com");
		mail.setSubject("主题：简单邮件");
		mail.setText("测试邮件内容");
		
		mailSender.send(mail);
	}

//	@Test
	public void sendAttachmentsMail() throws MessagingException{
		//MimeMessage用来设置复杂一些的邮件内容
		MimeMessage mimeMessage=mailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);
		
		helper.setFrom("3368436201@qq.com");
		helper.setTo("3368436201@qq.com");
		helper.setSubject("主题：附件");
//		helper.setText("有附件的邮件");
		helper.setText("<html><body><img src=\"cid:weixin\" ></body></html>",true);
		
		FileSystemResource file=new FileSystemResource(new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\a.jpg"));
		helper.addAttachment("附件1.jpg", file);//前面为文件名,添加附件
       
		helper.addInline("weixin", file);//addInline函数中资源名称weixin需要与正文中cid:weixin对应起来
		
		mailSender.send(mimeMessage);
	}
	
	@Test
	public void sendTemplateMail() throws MessagingException{
		MimeMessage mimeMessage=mailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(mimeMessage, true);
		
		helper.setFrom("3368436201@qq.com");
		helper.setTo("3368436201@qq.com");
		helper.setSubject("主题：模版邮件");
		
		Map<String, Object> model=new HashMap<String, Object>();
		//通过传入username的参数，在邮件内容中替换了模板中的${username}变量。
		model.put("username", "zzq");
		//mailTemplate.vm模板默认位于resources/templates/目录下
		String text=VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "mailTemplate.vm", 
				"UTF-8", model);
		helper.setText(text,true);//要加true
		
		mailSender.send(mimeMessage);
	}
}
