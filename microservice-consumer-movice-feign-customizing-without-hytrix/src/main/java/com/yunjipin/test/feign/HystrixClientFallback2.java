package com.yunjipin.test.feign;

import org.springframework.stereotype.Component;

import com.yunjipin.test.entity.User;

@Component
public class HystrixClientFallback2 implements UserFeignClient2 {

	@Override
	public String findServiceInfoFromEurekaByServiceName(String serviceName) {
		// TODO Auto-generated method stub
		return "失败了";
	}


    
}
