package com.ts.provider.service;

import com.ts.provider.service.impl.PaymentHystrixFallbackServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName PaymentHystrixService
 * @Description TODO
 * @Author 86175
 * @Date 2020/4/13 17:45
 * @Params TODO
 */
@Service
@FeignClient(value = "PROVIDER-HYSTRIX-PAYMENT-SERVICE",fallback = PaymentHystrixFallbackServiceImpl.class)
public interface PaymentHystrixService {

    /*
     *
     * @Author linky
     * @Description 方法描述: 测试访问正常的接口，验证hystrix的功能
     * @Date  2020/4/13 17:10
     * @Param [id]
     * @return java.lang.String
     **/
    @GetMapping("/payment/findInfoOk/{id}")
    public String findInfoOk(@PathVariable("id") Long id);

    /*
     *
     * @Author linky
     * @Description 方法描述: 访问到有异常的接口，验证hystrix的功能
     * @Date  2020/4/13 17:11
     * @Param [id]
     * @return java.lang.String
     **/
    @GetMapping("/payment/findInfoUnusual/{id}")
    public String findInfoUnusual(@PathVariable("id") Long id);


}
