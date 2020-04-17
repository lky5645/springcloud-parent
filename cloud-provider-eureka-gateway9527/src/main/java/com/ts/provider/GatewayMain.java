package com.ts.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName GatewayMain
 * @Description TODO
 * @Author 86175
 * @Date 2020/4/15 17:33
 * @Params TODO
 */
@SpringBootApplication
@EnableEurekaClient
public class GatewayMain {

    public static void main(String[] args) {
        SpringApplication.run(GatewayMain.class,args);
    }
}
