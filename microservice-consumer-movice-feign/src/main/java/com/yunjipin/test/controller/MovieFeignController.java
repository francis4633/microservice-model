package com.yunjipin.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yunjipin.test.entity.User;
import com.yunjipin.test.feign.UserFeignClient;

@RestController
public class MovieFeignController {

	@Autowired
	private UserFeignClient userFeignClient;
	
	@GetMapping("/movie/{id}")
	public User findById(@PathVariable Long id) {
		return userFeignClient.findById(id);
// 	   return this.restTemplate.getForObject(this.userServicePath + id, User.class);
	}
	
	@GetMapping("/feignPost")
	public User testPost(User user) {
		return this.userFeignClient.postUser(user);
	}
	
	@GetMapping("/feignGetToPost")
	public User testFeignGetToPost(User user) {
		return this.userFeignClient.testFeignGetToPost(user);
	}
}
