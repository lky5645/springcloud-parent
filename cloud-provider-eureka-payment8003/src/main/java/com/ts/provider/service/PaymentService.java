package com.ts.provider.service;

import com.ts.provider.bo.PaymentBo;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName PaymentService
 * @Description TODO
 * @Author 86175
 * @Date 2020/3/26 14:58
 * @Params TODO
 */
public interface PaymentService {
    public int save(PaymentBo paymentBo);
    public int update(PaymentBo paymentBo);
    public PaymentBo findById(@Param("id") Long id);
    public int delete(@Param("id") Long id);
}
