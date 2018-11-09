package com.yunjipin.test.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yunjipin.config.feign.FeignConfig;
import com.yunjipin.test.entity.User;

@FeignClient(name = "microservice-provider-user",configuration = FeignConfig.class,/*fallback = HystrixClientFallback.class,*/fallbackFactory = HystrixClientFallbackFactory.class)
public interface UserFeignClient {

	  @RequestMapping(value = "/simple/{id}",method = RequestMethod.GET)
	  public User findById(@PathVariable("id") Long id);
	 
	  
	  
}

