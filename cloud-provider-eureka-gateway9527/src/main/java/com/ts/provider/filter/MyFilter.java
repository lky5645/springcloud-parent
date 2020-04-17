package com.ts.provider.filter;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * @ClassName MyFilter
 * @Description TODO
 * @Author 86175
 * @Date 2020/4/16 17:18
 * @Params TODO
 */
@Component
@Slf4j
public class MyFilter implements GlobalFilter, Ordered {
    /*
     *
     * @Author linky
     * @Description 方法描述: 自定义过滤器
     * @Date  2020/4/16 17:21
     * @Param [exchange, chain]
     * @return reactor.core.publisher.Mono<java.lang.Void>
     **/
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("*************start in MyFilter***********");
        //判断请求是否包含payment
        String uri =  exchange.getRequest().getURI().getPath();
        if (ObjectUtil.isEmpty(uri) || !uri.contains("payment")) {
            log.warn("请求的url不包含payment");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
