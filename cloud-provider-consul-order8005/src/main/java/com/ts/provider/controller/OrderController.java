package com.ts.provider.controller;

import com.ts.provider.bo.PaymentBo;
import com.ts.provider.util.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName OrderController
 * @Description TODO
 * @Author 86175
 * @Date 2020/3/27 14:54
 * @Params TODO
 */
@RestController
@Slf4j
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;
    private static final String common_url = "http://provider-consul-payment-service/";

    @GetMapping("/order/payment/{id}")
    public CommonResult<PaymentBo> findById(@PathVariable("id") Long id){
        try {
            String url = common_url +"payment/"+id;
            log.info("url:"+url);
            CommonResult payment = restTemplate.getForObject(url,CommonResult.class);
            log.info("查询结果为:{}", payment.toString());
            return payment;
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


}
