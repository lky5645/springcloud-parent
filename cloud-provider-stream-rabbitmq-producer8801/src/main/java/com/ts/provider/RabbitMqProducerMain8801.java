package com.ts.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName RabbitMqProducerMain8801
 * @Description TODO
 * @Author 86175
 * @Date 2020/4/18 13:54
 * @Params TODO
 */
@SpringBootApplication
@EnableEurekaClient
public class RabbitMqProducerMain8801 {

    public static void main(String[] args) {
        SpringApplication.run(RabbitMqProducerMain8801.class,args);
    }
}
