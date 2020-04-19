package com.ts.provider.controller;

import com.ts.provider.service.MqProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName MqProducerController
 * @Description TODO
 * @Author 86175
 * @Date 2020/4/18 14:15
 * @Params TODO
 */
@RestController
@Slf4j
public class MqProducerController {
    @Resource
    private MqProducerService mqProducerService;

    @RequestMapping("/stream/rabbitmq/sendMsg")
    public String sendMsg(){
        return mqProducerService.producerMsg();
    }

}
