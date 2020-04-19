package com.ts.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName OrderZipkinMain8010
 * @Description TODO
 * @Author 86175
 * @Date 2020/4/19 13:13
 * @Params TODO
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class OrderZipkinMain8010 {

    public static void main(String[] args) {
        SpringApplication.run(OrderZipkinMain8010.class,args);
    }
}

