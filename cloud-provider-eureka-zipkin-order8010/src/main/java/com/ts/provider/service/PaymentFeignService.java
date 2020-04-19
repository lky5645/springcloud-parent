package com.ts.provider.service;

import com.ts.provider.bo.PaymentBo;
import com.ts.provider.util.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName PaymentFeignService
 * @Description TODO
 * @Author 86175
 * @Date 2020/4/19 13:17
 * @Params TODO
 */
@Component
@FeignClient(value = "PROVIDER-ZIPKIN-PAYMENT-SERVICE")
public interface PaymentFeignService {

    /*
     *
     * @Author linky
     * @Description 方法描述: @GetMapping("/payment/{id}") 这个地址要和服务提供者8009保持一致
     * @Date  2020/4/13 14:29
     * @Param [id]
     * @return com.ts.provider.util.CommonResult<com.ts.provider.bo.PaymentBo>
     **/
    @GetMapping("/payment/{id}")
    public CommonResult<PaymentBo> findById(@PathVariable("id") Long id);

    @GetMapping("/payment/getZipkin")
    public String getZipkin();
}
