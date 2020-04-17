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
    //*************************************************Hystrix服务降级*************************************************************

    /*
     *
     * @Author linky
     * @Description 方法描述: 演示健康的访问
     * @Date  2020/4/13 16:52
     * @Param [id]
     * @return java.lang.String
     **/
    String findInfo_OK(@Param("id") Long id);
    /*
     *
     * @Author linky
     * @Description 方法描述:演示异常的访问，比如超时，错误等等
     * @Date  2020/4/13 16:53
     * @Param [id]
     * @return java.lang.String
     **/
    String findInfo_unusual(@Param("id") Long id);

    //*************************************************Hystrix服务熔断*************************************************************
    /*
     *
     * @Author linky
     * @Description 方法描述:演示hystrix的服务熔断
     * @Date  2020/4/15 11:34
     * @Param [id]
     * @return java.lang.String
     **/
    public String findInfo_circuit(Long id);

}
