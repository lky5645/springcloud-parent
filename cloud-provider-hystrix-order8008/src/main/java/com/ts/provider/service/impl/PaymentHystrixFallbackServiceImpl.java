package com.ts.provider.service.impl;

import com.ts.provider.service.PaymentHystrixService;
import org.springframework.stereotype.Service;

/**
 * @ClassName PaymentHystrixFallbackServiceImpl
 * @Description :统一的兜底方法类
 * @Author 86175
 * @Date 2020/4/15 10:43
 * @Params TODO
 */
@Service
public class PaymentHystrixFallbackServiceImpl implements PaymentHystrixService{
    @Override
    public String findInfoOk(Long id) {
        return "对方系统出现异常，开启自我保护降级措施";
    }

    @Override
    public String findInfoUnusual(Long id) {
        return "对方宕机了，自我保护护降级措施";
    }
}
