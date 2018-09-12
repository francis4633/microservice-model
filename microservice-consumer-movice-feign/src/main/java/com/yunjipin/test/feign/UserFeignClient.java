package com.yunjipin.test.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yunjipin.config.feign.FeignConfig;
import com.yunjipin.test.entity.User;
//FeignConfig为安全密码的配置,FeignConfig与ribbon的一样，不能放在application的包下，否则所有的Feignclient都会使用这个配置
@FeignClient(name = "microservice-provider-user",configuration = FeignConfig.class)
public interface UserFeignClient {

	  //两个坑，1：@GetMapping不支持；2、@PathVariable得设置value,springMVC默认是不需要加（“id”）
	  @RequestMapping(value = "/simple/{id}",method = RequestMethod.GET)
	  public User findById(@PathVariable("id") Long id);
	  
	  @RequestMapping(value = "/user",method = RequestMethod.POST)
	  public User postUser(@RequestBody User user) ;
	  
	  //第三个坑：该请求不会成功，只要参数是负责对象，即使指定了是get方法，feign依然会议post方法进行请求
//	  @RequestMapping(value = "/testFeignGetToPost",method = RequestMethod.GET)
//	  public User testFeignGetToPost(User user);//即使method中写的是get方法，但只要方法的参数user是一个对象，请求User微服务依然会使用post方法
	  //如果method为get请求需要使用多个参数的方法如下
//	  public User testFeignGetToPost(@RequestParam("id") Long id,@RequestParam("username") String username, @RequestParam("name") String name);

	  //少了一个注解：@RequestBody，既然使用对象传递参数，那传入的参数会默认放在RequesBody中，
	  //所以在接收的地方需要使用@RequestBody来解析，同时，在服务生产端也需要定义content-tye为MediaType.APPLICATION_JSON_VALUE，
	  //参数也是用@RequestBody注释（@RequestBody一般用在content-type为application/json和application/xml中，MediaType.APPLICATION_JSON_VALUE相当于application/json） 最终就是如下定义
	  @RequestMapping(value = "/testFeignGetToPost",method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
	  public User testFeignGetToPost(@RequestBody User user);
}
