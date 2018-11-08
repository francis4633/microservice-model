package com.yunjipin.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
//@SpringCloudApplication
@EnableEurekaClient
@EnableFeignClients
//@EnableHystrix
public class MoviceFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviceFeignApplication.class, args);
	}
}
