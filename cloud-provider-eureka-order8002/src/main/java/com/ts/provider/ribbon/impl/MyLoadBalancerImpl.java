package com.ts.provider.ribbon.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.ts.provider.ribbon.MyLoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName MyLoadBalancerImpl
 * @Description 自定义负载均衡，不采用原生的接口
 * @Author 86175
 * @Date 2020/4/10 14:02
 * @Params TODO
 */
@Component
@Slf4j
public class MyLoadBalancerImpl implements MyLoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);


    /*
     *
     * @Author linky
     * @Description 方法描述: 通过自旋锁获取到下一个数字
     * @Date  2020/4/10 14:09
     * @return int
     **/
    private int incrementAndGetModulo() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current > Integer.MAX_VALUE ? 0 : current + 1;
        } while (!this.atomicInteger.compareAndSet(current, next));
        log.info("获取到的下一个next:{}",next);
        return next;
    }

    @Override
    public ServiceInstance getServiceInstance(List<ServiceInstance> list) {
        if (CollectionUtil.isNotEmpty(list)){
            int next = this.incrementAndGetModulo();
            int index = next % list.size();
            ServiceInstance serviceInstance = list.get(index);
            log.info("第{}次访问，获取到的实例为:{}",next,serviceInstance.toString());
            return serviceInstance;
        }
        return null;
    }
}
