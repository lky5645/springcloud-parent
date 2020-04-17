package com.ts.provider.controller;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.ts.provider.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName OrderHystrixController
 * @Description TODO
 * @Author 86175
 * @Date 2020/4/13 17:48
 * @Params TODO
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "globalFallback_method")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    //*************************************************服务降级*************************************************************
    /*
     *
     * @Author linky
     * @Description 方法描述: 测试访问正常的接口，验证hystrix的功能
     * @Date  2020/4/13 17:10
     * @Param [id]
     * @return java.lang.String
     **/
    @GetMapping("/order/hystrix/payment/findInfoOk/{id}")
    public String findInfoOk(@PathVariable("id") Long id){
        return paymentHystrixService.findInfoOk(id);
    }

    /*
     *
     * @Author linky
     * @Description 方法描述:客户端feign调用的时候，如果出现异常或者超时，可以设置自己的保护机制
     *                      方法一：单独为某一个方法做服务兜底处理
     * @Date  2020/4/13 17:11
     * @Param [id]
     * @return java.lang.String
     **/
    @GetMapping("/order/hystrix/payment/findInfoUnusualV1/{id}")
    @HystrixCommand(fallbackMethod = "findInfo_unusualHandelForOrder",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
    })
    public String findInfoUnusualV1(@PathVariable("id") Long id){
        return paymentHystrixService.findInfoUnusual(id);
    }

    public String findInfo_unusualHandelForOrder(@PathVariable("id") Long id){
        return "我是调用服务者，提供服务者出现异常，开启自我保护机制，不再调用服务提供者id:"+id;
    }

    /*
     *
     * @Author linky
     * @Description 方法描述: 配置统一的兜底方法，一个一个的配置兜底方法，过于繁琐，必须加上@HystrixCommand
     *              方法二：全局为所有需要兜底的方法做统一处理
     * @Date  2020/4/15 9:44
     * @Param [id]
     * @return java.lang.String
     **/
    @GetMapping("/order/hystrix/payment/findInfoUnusualV2")
    @HystrixCommand
    public String findInfoUnusualV2(){
        int i = 10/0;//故意报错，进入去全局的兜底方法
        return "服务调用正常";
    }

    //全局的兜底方法，只要没有配置特殊的兜底方法，都会进入到这个方法
    public String globalFallback_method(){
        return "服务调用出现异常，进入全局兜底方法";
    }


    /*
     *
     * @Author linky
     * @Description 方法描述: 方法三：采用另外一种模式做兜底处理，这样做把业务和降级分开了
     * @Date  2020/4/15 10:42
     * @Param [id]
     * @return java.lang.String
     **/
    @GetMapping("/order/hystrix/payment/findInfoUnusualV3/{id}")
    public String findInfoUnusualV3(@PathVariable("id") Long id){
        return paymentHystrixService.findInfoUnusual(id);
    }

    //*************************************************服务熔断*************************************************************

    @GetMapping("/order/hystrix/payment/findInfo_circuit/{id}")
    @HystrixCommand(fallbackMethod = "circuit_method",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"), //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"), //请求次数10次
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//窗口期 10秒
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")  //错误率达到多少后跳闸
    })
    public String findInfo_circuit(@PathVariable("id") Long id){
        return paymentHystrixService.findInfoUnusual(id);
    }

    public String circuit_method(){
        String uuid = IdUtil.simpleUUID();
        return "调用异常，进入服务熔断处理,序列号："+uuid;
    }
}
