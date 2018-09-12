package com.yunjipin.config.feign;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
