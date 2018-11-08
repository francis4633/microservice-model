package com.yunjipin.config.feign;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import feign.Feign;
import feign.Logger;
import feign.auth.BasicAuthRequestInterceptor;

@Configuration
public class FeignConfig2 {
	
	 @Value("${security.user.name}")
	public String user;
	 @Value("${security.user.password}")
	public String password;
	 @Bean
	public BasicAuthRequestInterceptor getBasicAuthRequestInterceptor() {
		return new BasicAuthRequestInterceptor(user,password);
	}
	 //若没有设置contract,则默认使用SpringMvcContract，现在改为使用feign默认的contract协议
//	 @Bean
//	 public Contract feignContract() {
//		 return new feign.Contract.Default();
//	 }
	 
	/**
	 * Feign.Builder默认的 feignBuilder是HystrixFeign.Builder，是包含了hystrix的
	 * 此处重写了feignBuilder方法，将其改为返回feign本身的builder();
	 * @return
	 */
	@Bean
	@Scope("prototype")
	public Feign.Builder feignBuilder() {
		return Feign.builder();
	}
	 
	 @Bean
     Logger.Level feignLoggerLevel() {
	    return Logger.Level.FULL;
	  }
}
