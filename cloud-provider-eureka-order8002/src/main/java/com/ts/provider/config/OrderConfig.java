package com.ts.provider.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName OrderConfig
 * @Description TODO
 * @Author 86175
 * @Date 2020/3/27 14:56
 * @Params TODO
 */
@Configuration
public class OrderConfig {

    //需要用到负载均衡
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
