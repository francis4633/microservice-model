package com.yunjipin.config.feign;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import feign.Feign;
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
	 
	/*@Bean
    @Scope("prototype")
    public Feign.Builder feignHystrixBuilder() {
        return Feign.builder();
    }
*/
}
