package com.ts.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName OrderApplication
 * @Description : 使用consul作为注册中心
 * @Author 86175
 * @Date 2020/3/27 14:49
 * @Params TODO
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderConsulApplication8005 {

    public static void main(String[] args) {
        SpringApplication.run(OrderConsulApplication8005.class,args);
    }

}
