package com.yunjipin.test.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunjipin.config.feign.FeignConfig2;
//FeignConfig为安全密码的配置,FeignConfig与ribbon的一样，不能放在application的包下，否则所有的Feignclient都会使用这个配置
@FeignClient(name = "xxx",url = "http://localhost:8761/",configuration = FeignConfig2.class,fallback = HystrixClientFallback2.class)
public interface UserFeignClient2 {
	
	@RequestMapping(value = "/eureka/apps/{serviceName}")
	public String findServiceInfoFromEurekaByServiceName(@PathVariable("serviceName") String serviceName);
	  
}
