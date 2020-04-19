package com.ts.provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ConfigClientController
 * @Description TODO
 * @Author 86175
 * @Date 2020/4/17 15:12
 * @Params TODO
 */
@RestController
@Slf4j
@RefreshScope  //用于当git上面的配置发生变化时，我们这边能及时接收到,
// 同时还需要手动请求下 curl -X POST "http://localhost:3355/actuator/refresh"这个接口，用于通知git文件以及更改
public class ConfigClientController {

    @Value("${server.port}")
    private String serverPort;
    @Value("${config.info}")
    private String configInfo;

    @RequestMapping("/config/git/getConfigInfo")
    public String getConfigInfo(){
        log.info("读取到配置中心的信息：{},端口号：{}",configInfo,serverPort);
        return "端口号："+serverPort+",配置信息:"+configInfo;
    }
}
