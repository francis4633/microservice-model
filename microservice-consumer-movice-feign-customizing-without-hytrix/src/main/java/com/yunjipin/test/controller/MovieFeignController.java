package com.yunjipin.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yunjipin.test.entity.User;
import com.yunjipin.test.feign.UserFeignClient;
import com.yunjipin.test.feign.UserFeignClient2;

@RestController
public class MovieFeignController {

	@Autowired
	private UserFeignClient userFeignClient;
	@Autowired
	private UserFeignClient2 userFeignClient2;
	@GetMapping("/movie/{id}")
	public User findById(@PathVariable Long id) {
		return userFeignClient.findById(id);
// 	   return this.restTemplate.getForObject(this.userServicePath + id, User.class);
	}
	
	@GetMapping("/{serviceName}")
	public String findServiceInfoFromEurekaByServiceName(@PathVariable("serviceName") String serviceName) {
		return this.userFeignClient2.findServiceInfoFromEurekaByServiceName(serviceName);
	}
}
