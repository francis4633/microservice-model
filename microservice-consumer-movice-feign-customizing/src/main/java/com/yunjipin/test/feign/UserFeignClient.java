package com.yunjipin.test.feign;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.yunjipin.config.feign.FeignConfig;
import com.yunjipin.test.entity.User;

import feign.Param;
import feign.RequestLine;
//FeignConfig为安全密码的配置,FeignConfig与ribbon的一样，不能放在application的包下，否则所有的Feignclient都会使用这个配置
@FeignClient(name = "microservice-provider-user",configuration = FeignConfig.class)
public interface UserFeignClient {
	  //使用feign默认的contract(协议)，差别在于客户端请求的注解改为@RequestLine，参数的注解改为@Param
//	  @RequestMapping(value = "/simple/{id}",method = RequestMethod.GET)
	  @RequestLine("GET /simple/{id}")//https://github.com/OpenFeign/feign
	  public User findById(@Param("id") Long id);
	  
}
