package com.ts.provider.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName FeginLevelConfig
 * @Description 设置fegin的日志
 * @Author 86175
 * @Date 2020/4/13 15:22
 * @Params TODO
 */
@Configuration
public class FeginLevelConfig {

    /*
     *
     * @Author linky
     * @Description 方法描述: 把fegin的日志级别设置为full，调用的过程可以显示所有的过程
     * @Date  2020/4/13 15:24
     * @Param []
     * @return feign.Logger.Level
     **/
    @Bean
    public Logger.Level level(){
        return Logger.Level.FULL;
    }
}
