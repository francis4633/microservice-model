package com.yunjipin.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class MovieController {
  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private HttpHeaders httpHeader;
  
  @Value("${user.userServicePath}")
  private String userServicePath;

  @GetMapping("/movie/{id}")
  public User findById(@PathVariable Long id) {
	  // 设置访问头
	  HttpEntity requestEntity = new HttpEntity(httpHeader);
	  ResponseEntity<User> responseUser = restTemplate.exchange(this.userServicePath + id,HttpMethod.GET, requestEntity, User.class);
	  User user = responseUser.getBody();
	  return user;
//    return this.restTemplate.getForObject(this.userServicePath + id, User.class);
  }
}
