package com.ts.ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MyRule
 * @Description TODO
 * @Author 86175
 * @Date 2020/4/9 20:51
 * @Params TODO
 */
@Configuration
public class MyRuleConfig {
    /*
     *
     * @Author linky
     * @Description 方法描述: eureka自带ribbon负载均衡，采用的是轮询
     *                          进行设置，成随机的方案
     * @Date  2020/4/9 20:51
     * @Param 
     * @return 
     **/
    @Bean
    public IRule randomRule(){
        return new SpecialRule();
    }

}
