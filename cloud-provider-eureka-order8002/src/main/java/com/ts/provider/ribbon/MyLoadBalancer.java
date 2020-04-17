package com.ts.provider.ribbon;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @ClassName MyRule
 * @Description :自定义负载均衡方法2：
 * @Author 86175
 * @Date 2020/4/10 13:55
 * @Params TODO
 */
public interface MyLoadBalancer {
    ServiceInstance getServiceInstance(List<ServiceInstance> list);
}
