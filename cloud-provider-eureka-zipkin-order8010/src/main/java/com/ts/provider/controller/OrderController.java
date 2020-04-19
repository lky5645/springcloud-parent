package com.ts.provider.controller;

import com.ts.provider.bo.PaymentBo;
import com.ts.provider.service.PaymentFeignService;
import com.ts.provider.util.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName OrderController
 * @Description TODO
 * @Author 86175
 * @Date 2020/4/19 13:16
 * @Params TODO
 */
@RestController
@Slf4j
public class OrderController {

    @Resource
    private PaymentFeignService paymentFeignService;
    /*
     *
     * @Author linky
     * @Description 方法描述: 通过调用feginservice，会去找到eureka上面注册的PROVIDER-ZIPKIN-PAYMENT-SERVICE对应的服务
     *                          注意 fegin这里的url地址必须和服务提供者保持一致，也就是和8009保持一致
     * @Date  2020/4/13 13:59
     * @Param [id]
     * @return com.ts.provider.util.CommonResult<com.ts.provider.bo.PaymentBo>
     **/
    @GetMapping("/fegin/order/payment/{id}")
    public CommonResult<PaymentBo> findById(@PathVariable("id") Long id) {
        log.info("8010调用8009 params:{}",id);
        return paymentFeignService.findById(id);
    }

    @GetMapping("/fegin/order/payment/getZipkin")
    public String getZipkin() {
        log.info("8010调用8009 start....");
        return paymentFeignService.getZipkin();
    }
}
