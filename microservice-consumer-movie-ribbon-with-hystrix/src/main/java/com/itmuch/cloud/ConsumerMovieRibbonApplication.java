package com.itmuch.cloud;

import java.nio.charset.Charset;
import java.util.Base64;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class ConsumerMovieRibbonApplication {

  @Bean
//  @LoadBalanced
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  public static void main(String[] args) {
    SpringApplication.run(ConsumerMovieRibbonApplication.class, args);
  }
  
//定义一个Bean修改头信息进行客户端认证
	@Bean
	public HttpHeaders getHeader() {
	    HttpHeaders headers=new HttpHeaders();
	    String auth="user:password123";//认证的原始信息
	    byte[] encodeAuth=Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));//将原始认证信息进行Base64加密
	    String authHeader="Basic "+new String(encodeAuth);//加密后的认证信息要与Basic有个空格
	    headers.set("Authorization", authHeader);
	    return headers;
	}
}
