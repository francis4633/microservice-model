package com.yunjipin.test.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.yunjipin.test.entity.User;

import feign.hystrix.FallbackFactory;

@Component
public class HystrixClientFallbackFactory implements FallbackFactory<UserFeignClient> {
	
	private static final Logger LOGGER =LoggerFactory.getLogger(HystrixClientFallbackFactory.class);
	@Override
	public UserFeignClient create(Throwable cause) {
		LOGGER.info("fallback; reason was:{} ",  cause.getMessage());
		return new UserFeignClientWithFallbackFactory() {
			@Override
			public User findById(Long id) {
				User user = new User();
				user.setId(-1L);
				return user;
			}
		};
	}

	
    
}
