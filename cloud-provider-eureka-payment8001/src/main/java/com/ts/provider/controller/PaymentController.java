package com.ts.provider.controller;

import com.ts.provider.bo.PaymentBo;
import com.ts.provider.service.PaymentService;
import com.ts.provider.util.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.ZonedDateTime;
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
public class PaymentController {

    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPost;
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/save")
    public CommonResult save(@RequestBody PaymentBo paymentBo){
        try {
            log.info("参数:"+paymentBo.toString());
            paymentService.save(paymentBo);
            return new CommonResult(200,"保存成功");
        } catch (Exception e){
            log.error("保存异常:",e);
            return new CommonResult(500,"保存失败");
        }
    }

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

    @PostMapping("/payment/update")
    public CommonResult update(@RequestBody PaymentBo paymentBo){
        try {
            log.info("参数:"+paymentBo.toString());
            paymentService.update(paymentBo);
            return new CommonResult(200,"修改成功");
        } catch (Exception e){
            log.error("修改异常:",e);
            return new CommonResult(500,"修改失败");
        }
    }

    @GetMapping("/payment/getDiscoveryClient")
    public Object getDiscoveryClient() {
        List<String> services = this.discoveryClient.getServices();
        services.stream().forEach(service -> {
            log.info("当前service:" + service);
        });
        List<ServiceInstance> instances = this.discoveryClient.getInstances("PROVIDER-PAYMENT-SERVICE");
        instances.stream().forEach(instance -> {
            log.info("当前instance的serviceId:{},host:{},port:{},url:{}", instance.getServiceId(), instance.getHost(), instance.getPort(), instance.getPort(), instance.getUri());
        });
        return discoveryClient;
    }


    /*
     *
     * @Author linky
     * @Description 方法描述: 验证fegin的调用超时时间，fegin默认调用超时时间是1秒，如果超过1秒，就会报错，
     * @Date  2020/4/13 14:45
     * @Param []
     * @return java.lang.String
     **/
    @GetMapping("/payment/getFeginTimeOut")
    public String getFeginTimeOut(){
        try {
            Thread.sleep(3);
        } catch (Exception e){
            e.printStackTrace();
        }
        return "验证fegin调用超时"+serverPost;
    }
}
