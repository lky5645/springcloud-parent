package com.ts.provider.controller;

import com.ts.provider.bo.PaymentBo;
import com.ts.provider.ribbon.MyLoadBalancer;
import com.ts.provider.util.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @ClassName OrderController
 * @Description :作为调用者，采用ribbon + restTemplate 实现
 * @Author 86175
 * @Date 2020/3/27 14:54
 * @Params TODO
 */
@RestController
@Slf4j
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;
    private static final String common_url = "http://PROVIDER-PAYMENT-SERVICE/";

    //方案二，采用此种方法实现负载均衡
    @Resource
    private MyLoadBalancer myLoadBalancer;
    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/order/payment/{id}")
    public CommonResult<PaymentBo> findById(@PathVariable("id") Long id){
        try {
            String url = common_url +"payment/"+id;
            CommonResult payment = restTemplate.getForObject(url,CommonResult.class);
            log.info("查询结果为:{}", payment.toString());
            return payment;
        } catch (Exception e){
            log.error("查询异常:",e);
            return new CommonResult(500,"查询失败");
        }
    }


    @GetMapping("/order/payment/getForEntity/{id}")
    public CommonResult<PaymentBo> getForEntityById(@PathVariable("id") Long id){
        try {
            String url = common_url +"payment/"+id;
            ResponseEntity<CommonResult> payment = restTemplate.getForEntity(url,CommonResult.class);
            log.info("查询结果为:{}", payment.toString());
            if (payment.getStatusCode().is2xxSuccessful()){
                return payment.getBody();
            } else {
                return new CommonResult<>(400,"查询失败");
            }
        } catch (Exception e){
            log.error("查询异常:",e);
            return new CommonResult(500,"查询失败");
        }
    }

    @PostMapping("/order/payment/save")
    public CommonResult save(@RequestBody PaymentBo paymentBo){
        try {
            log.info("参数:"+paymentBo.toString());
            String url = common_url + "payment/save";
            return restTemplate.postForObject(url, paymentBo, CommonResult.class);
        } catch (Exception e){
            log.error("保存异常:",e);
            return new CommonResult(500,"保存失败");
        }
    }

    @GetMapping("/order/payment/getDiscoveryClient")
    public Object getDiscoveryClient(){
        try {
            String url = common_url +"payment/getDiscoveryClient";
            Object payment = restTemplate.getForObject(url,Object.class);
            log.info("查询结果为:{}", payment.toString());
            return payment;
        } catch (Exception e){
            log.error("查询异常:",e);
            return new CommonResult(500,"查询失败");
        }
    }

    /*
     *
     * @Author linky
     * @Description 方法描述: 测试自定义负载均衡
     * @Date  2020/4/10 14:25
     * @Param []
     * @return java.lang.Object
     **/
    @GetMapping("/order/payment/testLoadBalancer")
    public Object testLoadBalancer(){
        try {
            List<ServiceInstance> instances = discoveryClient.getInstances("PROVIDER-PAYMENT-SERVICE");
            ServiceInstance serviceInstance = myLoadBalancer.getServiceInstance(instances);
            URI uri = serviceInstance.getUri();

            String url = common_url +"/payment/getDiscoveryClient";
            Object payment = restTemplate.getForObject(url,Object.class);
            log.info("测试负载均衡查询结果为:{},获取到的数据为：{}", serviceInstance.toString(),payment.toString());
            return payment;
        } catch (Exception e){
            log.error("查询异常:",e);
            return new CommonResult(500,"查询失败");
        }
    }


}
