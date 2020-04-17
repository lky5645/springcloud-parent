package com.ts.provider.service;

import com.ts.provider.bo.PaymentBo;
import com.ts.provider.util.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName PaymentFeginService
 * @Description 使用fegin调用远程服务
 * @Author 86175
 * @Date 2020/4/13 12:35
 * @Params TODO
 */
@Component
@FeignClient(value = "PROVIDER-PAYMENT-SERVICE")
public interface PaymentFeginService {

    /*
     *
     * @Author linky
     * @Description 方法描述: @GetMapping("/payment/{id}") 这个地址要和服务提供者8001,8003保持一致
     * @Date  2020/4/13 14:29
     * @Param [id]
     * @return com.ts.provider.util.CommonResult<com.ts.provider.bo.PaymentBo>
     **/
    @GetMapping("/payment/{id}")
    public CommonResult<PaymentBo> findById(@PathVariable("id") Long id);

    /*
     *
     * @Author linky
     * @Description 方法描述: 验证fegin的调用超时时间，fegin默认调用超时时间是1秒，如果超过1秒，就会报错，
     * @Date  2020/4/13 14:45
     * @Param []
     * @return java.lang.String
     **/
    @GetMapping("/payment/getFeginTimeOut")
    public String getFeginTimeOut();
}
