package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration//用@Configuration注解该类，等价 与XML中配置beans；用@Bean标注方法等价于XML中配置bean。
@EnableSwagger2//注解用来启用Swagger2。
public class Swagger2Config {

	@Bean//spring识别出标注 @Bean 的所有方法，执行之，并将方法的返回值 (相当于配置的javabean)注册到 IoC 容器中
	public Docket createRestApi(){
		Docket docket= new Docket(DocumentationType.SWAGGER_2);
		docket.apiInfo(apiInfo());
		//select()函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger来展现
		//采用指定扫描的包路径来定义，Swagger会扫描该包下所有Controller定义的API，并产生文档内容
		docket.select()
		      .apis(RequestHandlerSelectors.basePackage("com.example.web"))
		      .paths(PathSelectors.any())
		      .build();
		return docket;
	}
	
	private ApiInfo apiInfo(){
		ApiInfoBuilder builder=new ApiInfoBuilder();
		//创建api的基本信息
		builder.title("Spring Boot中使用Swagger2构建RESTful APIs");
		builder.description("更多Spring Boot相关文章请关注：http://blog.didispace.com/");
		builder.termsOfServiceUrl("http://blog.didispace.com/");
		builder.contact("admin");
		builder.version("1.0");
		return builder.build();
	}
}
