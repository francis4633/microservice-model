package com.yunjipin.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.yunjipin.test.entity.User;

@RestController
public class MovieRibbonController {
  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private HttpHeaders httpHeader;
  
  @Autowired
  private LoadBalancerClient loadBalancerClient;
  
  
  @Value("${user.userServicePath}")
  private String userServicePath;
  
  @GetMapping("/movie/{id}")
  public User findById(@PathVariable Long id) {
	  
	  ServiceInstance serviceInstance = loadBalancerClient.choose("microservice-provider-user");
	  System.out.println("负载均衡策略是："+serviceInstance.getHost()+":"+serviceInstance.getPort()+":"+serviceInstance.getServiceId());
	  
	  // 设置访问头
	  HttpEntity requestEntity = new HttpEntity(httpHeader);
	  //VIP:vitural IP(microservice-provider-user  yml文件中配置的sping.application.name的值) 虚拟ip
	  ResponseEntity<User> responseUser = restTemplate.exchange("http://microservice-provider-user/simple/" + id,HttpMethod.GET, requestEntity, User.class);
	  User user = responseUser.getBody();
	  return user;
//    return this.restTemplate.getForObject(this.userServicePath + id, User.class);
  }
  
  @GetMapping("/test")
  public String test() {
	  ServiceInstance serviceInstance = loadBalancerClient.choose("microservice-provider-user");
	  System.out.println("user随机策略打印："+serviceInstance.getHost()+":"+serviceInstance.getPort()+":"+serviceInstance.getServiceId());
	  
	  
	  ServiceInstance serviceInstance2 = loadBalancerClient.choose("microservice-provider-user2");
	  System.out.println("user2轮询策略打印："+serviceInstance2.getHost()+":"+serviceInstance2.getPort()+":"+serviceInstance2.getServiceId());
	  
	  return "";
  }
}
