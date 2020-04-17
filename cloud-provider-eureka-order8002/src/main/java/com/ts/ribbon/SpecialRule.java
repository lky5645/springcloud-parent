package com.ts.ribbon;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName SpecialRule
 * @Description 自定义算法
 * @Author 86175
 * @Date 2020/4/10 8:47
 * @Params TODO
 */
@Slf4j
public class SpecialRule extends AbstractLoadBalancerRule {

    //    private static ThreadLocal<Integer> currentCount = new ThreadLocal<>();
    private static Integer currentCount = 0;

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    /*
     *
     * @Author linky
     * @Description 方法描述: 自定义算法 如果访问次数
     * @Date  2020/4/10 8:55
     * @Param [lb, o]
     * @return com.netflix.loadbalancer.Server
     **/
    public Server choose(ILoadBalancer lb, Object o) {
        Server server = null;
        if (lb != null) {
            //获取到可用的服务实例
            List<Server> reachableServers = lb.getReachableServers();
            //获取到所有的服务实例
            List<Server> allServers = lb.getAllServers();
            int upCount = reachableServers.size();
            int serverCount = allServers.size();
            log.info("当前第{}此访问", currentCount);
            if (upCount > 0 && serverCount > 0) {
                //如果当前访问次数currentCount小于服务实例数
                if (currentCount >= upCount) {
                    currentCount = 0; //重新从0开始计算
                }
                server = (Server) allServers.get(currentCount);
                currentCount++;
            }
        }
        log.info("获取到的实例：{}",server.toString());
        return server;
    }


    @Override
    public Server choose(Object o) {
        return this.choose(getLoadBalancer(),o);
    }
}
