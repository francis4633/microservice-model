package com.yunjipin.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

/*FooConfiguration必须是@Configuration，但请注意，它不在主应用程序上下文的@ComponentScan中，否则将由所有@RibbonClients共享。
    如果您使用@ComponentScan（或@SpringBootApplication），则需要采取措施避免包含（例如将其放在一个单独的，不重叠的包中，
   或者指定要在@ComponentScan）
*/
@Configuration   //规避上面所描述的问题有两个方案：1、放在appliction（MoviceRibbonApplication）外面(com.yunjipin.comfig)，如果放在主appliction（MoviceRibbonApplication）包以及子包中，
                //                               则该策略会使用到所有的微服务
                //                            2、自定义一个注解ExcludeFromComponentScan（不包含在componentScan的扫描中），然后在appliction类上添加
               //                                @ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = ExcludeFromComponentScan.class)})，
              //                                 意味着标有ExcludeFromComponentScan注解的类不执行application的策略
@ExcludeFromComponentScan
public class TestConfiguration {
	@Autowired
	IClientConfig config;
	
	@Bean
	@ConditionalOnMissingBean
	public IRule ribbonRule(IClientConfig config) {
		
		return new RandomRule();
	}
}
