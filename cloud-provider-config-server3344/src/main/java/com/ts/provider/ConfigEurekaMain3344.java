package com.ts.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName ConfigEurekaMain
 * @Description TODO
 * @Author 86175
 * @Date 2020/4/17 10:23
 * @Params TODO
 */
@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class ConfigEurekaMain3344 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigEurekaMain3344.class,args);
    }
}
