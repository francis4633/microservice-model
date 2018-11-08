package com.yunjipin.test.feign;

import org.springframework.stereotype.Component;

import com.yunjipin.test.entity.User;

@Component
public class HystrixClientFallback implements UserFeignClient {

	@Override
	public User findById(Long id) {
		User user = new User();
		user.setId(0L);
		return user;
	}
    
}
