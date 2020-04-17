package com.ts.provider;

import com.ts.ribbon.MyRuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/*
 *
 * @Author linky
 * @Description 方法描述: ribbon默认采用轮询的方式，如果换成自己定义的方式，
 *              需要告知哪个服务，使用自定义的,同时需要注意一个问题，自定义的myrule不能和启动类同一个包，
 *              也就是启动类不能扫描到我们自定义的myrule，否则所有的调用都会使用我们自定义的
 * @Date  2020/4/9 20:56
 * @Param
 * @return
 **/
@SpringBootApplication
@EnableEurekaClient
//@RibbonClient(name = "PROVIDER-PAYMENT-SERVICE",configuration = MyRuleConfig.class)
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }

}
