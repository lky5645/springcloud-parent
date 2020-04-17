package com.ts.provider;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;

/**
 * @ClassName PaymentHystrixMain
 * @Description TODO
 * @Author 86175
 * @Date 2020/4/13 15:55
 * @Params TODO
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker  //设置hystrix启动类
public class PaymentHystrixMain {

    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrixMain.class,args);
    }

    /*
     *
     * @Author linky
     * @Description 方法描述: 此配置是为了抚慰监控而配置，域抚慰容错本身无关，springcloud升级后的坑，
     *      ServletRegistrationBean 因为springboot的默认路径不是/hystrix.stream，
     *         只要把自己的项目里面配置上下面的servelt就可以了
     * @Date  2020/4/15 15:37
     * @Param []
     * @return org.springframework.boot.web.servlet.ServletRegistrationBean
     **/
    @Bean
    public ServletRegistrationBean getServelt(){
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }

}
