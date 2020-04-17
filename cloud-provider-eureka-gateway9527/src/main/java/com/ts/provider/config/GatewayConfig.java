package com.ts.provider.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName GatewayConfig
 * @Description TODO
 * @Author 86175
 * @Date 2020/4/15 21:48
 * @Params TODO
 */
@Configuration
public class GatewayConfig {

    /*
     *
     * @Author linky
     * @Description 方法描述: 采用java代码的方式配置路由
     * @Date  2020/4/15 21:49
     * @Param [builder]
     * @return org.springframework.cloud.gateway.route.RouteLocator
     **/
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("guonei",
                r -> r.path("/guonei")
                        .uri("http://news.baidu.com/guonei")
        ).build();
        return routes.build();
    }

//    @Bean
//    public RouteLocator routeLocator2(RouteLocatorBuilder builder) {
//        RouteLocatorBuilder.Builder routes = builder.routes();
//        routes.route("payment_routh2",
//                r -> r.path("/payment/*")
//                        .uri("http://localhost:8001/payment/*")
//        ).build();
//        return routes.build();
//    }

}
