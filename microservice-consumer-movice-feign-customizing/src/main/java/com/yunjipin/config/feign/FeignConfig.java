package com.yunjipin.config.feign;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Contract;
import feign.auth.BasicAuthRequestInterceptor;

@Configuration
public class FeignConfig {
	
	 @Value("${security.user.name}")
	public String user;
	 @Value("${security.user.password}")
	public String password;
	 @Bean
	public BasicAuthRequestInterceptor getBasicAuthRequestInterceptor() {
		return new BasicAuthRequestInterceptor(user,password);
	}
	 //若没有设置contract,则默认使用SpringMvcContract，现在改为使用feign默认的contract协议
	 @Bean
	 public Contract feignContract() {
		 return new feign.Contract.Default();
	 }
}
