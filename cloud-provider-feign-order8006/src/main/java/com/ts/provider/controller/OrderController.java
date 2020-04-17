package com.ts.provider.controller;

import com.ts.provider.bo.PaymentBo;
import com.ts.provider.service.PaymentFeginService;
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
 * @Date 2020/4/10 15:12
 * @Params TODO
 */
@RestController
@Slf4j
public class OrderController {

    @Resource
    private PaymentFeginService paymentFeginService;

    /*
     *
     * @Author linky
     * @Description 方法描述: 通过调用feginservice，会去找到eureka上面注册的PROVIDER-ORDER-SERVICE对应的服务
     *                          注意 fegin这里的url地址必须和服务提供者保持一致，也就是和8001或8003保持一致
     * @Date  2020/4/13 13:59
     * @Param [id]
     * @return com.ts.provider.util.CommonResult<com.ts.provider.bo.PaymentBo>
     **/
    @GetMapping("/fegin/order/payment/{id}")
    public CommonResult<PaymentBo> findById(@PathVariable("id") Long id) {
        log.info("调用 params:{}",id);
        return paymentFeginService.findById(id);
    }

    /*
     *
     * @Author linky
     * @Description 方法描述: 验证fegin的调用超时时间，fegin默认调用超时时间是1秒，如果超过1秒，就会报错，
     * @Date  2020/4/13 14:45
     * @Param []
     * @return java.lang.String
     **/
    @GetMapping("/fegin/order/payment/getFeginTimeOut")
    public String getFeginTimeOut(){
        return paymentFeginService.getFeginTimeOut();
    }

}
