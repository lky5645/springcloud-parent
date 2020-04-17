package com.ts.provider.controller;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.ts.provider.bo.PaymentBo;
import com.ts.provider.service.PaymentService;
import com.ts.provider.util.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName PaymentController
 * @Description TODO
 * @Author 86175
 * @Date 2020/3/26 15:01
 * @Params TODO
 */
@RestController
@Slf4j
public class PaymentHystrixController {

    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPost;
    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/payment/{id}")
    public CommonResult<PaymentBo> findById(@PathVariable("id") Long id){
        try {
            PaymentBo paymentBo = paymentService.findById(id);
            log.info("查询结果为:{}", paymentBo.toString());
            return new CommonResult(200,"查询成功，server:"+serverPost,paymentBo);
        } catch (Exception e){
            log.error("查询异常:",e);
            return new CommonResult(500,"查询失败,server:"+serverPost);
        }
    }

    //*************************************************服务降级*************************************************************
    /*
     *
     * @Author linky
     * @Description 方法描述: 测试访问正常的接口，验证hystrix的功能
     * @Date  2020/4/13 17:10
     * @Param [id]
     * @return java.lang.String
     **/
    @GetMapping("/payment/findInfoOk/{id}")
    public String findInfoOk(@PathVariable("id") Long id){
        return paymentService.findInfo_OK(id);
    }

    /*
     *
     * @Author linky
     * @Description 方法描述: 访问到有异常的接口，验证hystrix的功能
     * @Date  2020/4/13 17:11
     * @Param [id]
     * @return java.lang.String
     **/
    @GetMapping("/payment/findInfoUnusual/{id}")
    public String findInfoUnusual(@PathVariable("id") Long id){
        return paymentService.findInfo_unusual(id);
    }


    //*************************************************服务熔断*************************************************************

    @GetMapping("/payment/findInfo_circuit/{id}")
    public String findInfo_circuit(@PathVariable("id") Long id){
        return paymentService.findInfo_circuit(id);
    }
}
