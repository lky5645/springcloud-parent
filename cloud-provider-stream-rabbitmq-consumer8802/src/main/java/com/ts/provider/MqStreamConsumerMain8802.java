package com.ts.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName MqStreamConsumerMain8802
 * @Description TODO
 * @Author 86175
 * @Date 2020/4/18 14:33
 * @Params TODO
 */
@SpringBootApplication
@EnableEurekaClient
public class MqStreamConsumerMain8802 {
    public static void main(String[] args) {
        SpringApplication.run(MqStreamConsumerMain8802.class,args);
    }
}
