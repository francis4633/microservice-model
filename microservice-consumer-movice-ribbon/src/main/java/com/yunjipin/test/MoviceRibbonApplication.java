package com.yunjipin.test;

import java.nio.charset.Charset;
import java.util.Base64;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import com.yunjipin.test.config.ExcludeFromComponentScan;
import com.yunjipin.test.config.TestConfiguration;

@SpringBootApplication
@EnableEurekaClient
//自定义策略TestConfiguration(随机策略),该策略使用到spring.application.name=microservice-provider-user的微服务上
@RibbonClient(name = "microservice-provider-user", configuration = TestConfiguration.class)
//ExcludeFromComponentScan必须与TestConfiguration在同一个包目录下，意味着将ExcludeFromComponentScan包所在的目录下的类不使用上面的策略
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = ExcludeFromComponentScan.class)})
public class MoviceRibbonApplication {

  @Bean
  @LoadBalanced //默认轮询
  public RestTemplate restTemplate() {
    return new RestTemplate();
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

	  
	public static void main(String[] args) {
		SpringApplication.run(MoviceRibbonApplication.class, args);
	}
}
