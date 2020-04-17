package com.ts.provider.service.impl;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.ts.provider.bo.PaymentBo;
import com.ts.provider.dao.PaymentDao;
import com.ts.provider.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName PaymentServiceImpl
 * @Description TODO
 * @Author 86175
 * @Date 2020/3/26 14:59
 * @Params TODO
 */
@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    @Override
    public int save(PaymentBo paymentBo) {
        return paymentDao.save(paymentBo);
    }

    @Override
    public int update(PaymentBo paymentBo) {
        return paymentDao.update(paymentBo);
    }

    @Override
    public PaymentBo findById(Long id) {
        return paymentDao.findById(id);
    }

    @Override
    public int delete(Long id) {
        return paymentDao.delete(id);
    }

    //*************************************************hystrix服务降级*************************************************************
    /*
     *
     * @Author linky
     * @Description 方法描述: 演示健康的访问
     * @Date  2020/4/13 16:52
     * @Param [id]
     * @return java.lang.String
     **/
    @Override
    public String findInfo_OK(@Param("id") Long id) {
        return "正常访问，当前线程：" + Thread.currentThread().getName() + "id:" + id;
    }

    /*
     *
     * @Author linky
     * @Description 方法描述:演示异常的访问，比如超时，错误等等
     * @Date  2020/4/13 16:53
     * @Param [id]
     * @return java.lang.String
     *      如果当前服务出现异常，超时等等，就调用fallbackMethod里面的方法，设置超时时间为3秒
     **/
    @HystrixCommand(fallbackMethod = "findInfo_unusualHandel",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    @Override
    public String findInfo_unusual(@Param("id") Long id) {
        try {
//            int a = 10/0;
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("===>id"+id);
        return "访问出现异常：当前线程："+Thread.currentThread().getName()+"id:"+id;
    }

    /*
     *
     * @Author linky
     * @Description 方法描述: 兜底方法
     * @Date  2020/4/13 21:39
     * @Param [id]
     * @return java.lang.String
     **/
    public String findInfo_unusualHandel(@Param("id") Long id){
        return "服务暂时无法使用，调用兜底方法实现功能,id:"+id;
    }

    //*************************************************hystrix服务熔断*************************************************************

    /*
     *
     * @Author linky
     * @Description 方法描述:如下配置，如果打开了断路器，那么在10秒内连续访问10次以上，出现6次以上的访问错误，就会字段断路，
     * 那么哪怕输入正确的数据访问也会失败,需要慢慢的多是几次后，才会逐渐恢复成功
     * @Date  2020/4/15 11:40
     * @Param [id]
     * @return java.lang.String
     **/
    @HystrixCommand(fallbackMethod = "circuit_method",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"), //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"), //请求次数10次
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//窗口期 10秒
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")  //错误率达到多少后跳闸
    })
    public String findInfo_circuit(Long id){
        if (id < 0){
            throw new RuntimeException("id不能小于0");
        }
        String uuid = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"调用成功,当前流水号："+uuid;
    }

    public String circuit_method(Long id){
        String uuid = IdUtil.simpleUUID();
        return "调用异常，进入服务熔断处理,序列号："+uuid;
    }
}
