package com.ts.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName ConfigClinetMain3355
 * @Description config客户端
 * @Author 86175
 * @Date 2020/4/17 15:09
 * @Params TODO
 */
@SpringBootApplication
@EnableEurekaClient
public class ConfigClinetMain3355 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClinetMain3355.class,args);
    }
}

