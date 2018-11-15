package com.yunjipin.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunjipin.test.entity.User;
import com.yunjipin.test.repository.UserRepository;

@RestController
public class UserController {

  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private EurekaClient eurekaClient;
  
  @Autowired
  private DiscoveryClient discoveryClient;

  @GetMapping("/eureka-instance")
  public String serviceUrl() {
      InstanceInfo instance = eurekaClient.getNextServerFromEureka("MICROSERVICE-PROVIDER-USER", false);
      return instance.getHomePageUrl();
  }

  @GetMapping("/simple/{id}")
//  @HystrixCommand(fallbackMethod = "fallFindById")//fallFindById方法的返回参数类型以及接收参数的类型必须保持一致
  public User findById(@PathVariable Long id) {
		User user = this.userRepository.findOne(id);
//		String testStr = null;
//		testStr.toString();
		return user;
  }
  
  public User fallFindById(@PathVariable Long id) {
	  System.out.println(id);
	  User user = new User(0L,"失败了");
	  
	  return user;
  }


  @GetMapping("/instance-info")
  public ServiceInstance shoInfo() {
	  ServiceInstance localServiceInstance = this.discoveryClient.getLocalServiceInstance();
	  return localServiceInstance;
  }
  
  @PostMapping("/user")
  public User postUser(@RequestBody User user) {
	  return user;
  }

  
  @GetMapping(value = "/testFeignGetToPost",consumes = MediaType.APPLICATION_JSON_VALUE)
  public User testFeignGetToPost(@RequestBody User user) {
	  return user;
  }
}
