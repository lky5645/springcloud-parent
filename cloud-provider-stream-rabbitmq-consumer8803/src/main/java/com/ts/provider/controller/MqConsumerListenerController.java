package com.ts.provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @ClassName MqConsumerService
 * @Description TODO
 * @Author 86175
 * @Date 2020/4/18 14:34
 * @Params TODO
 */
@Slf4j
@Component
@EnableBinding(Sink.class) //代表是消费者
public class MqConsumerListenerController {

    @Value("${server.port}")
    private String serverPort;

    /*
     *
     * @Author linky
     * @Description 方法描述: 消费消息
     *          注意：不能有返回类型，就是必须为void，否则会报错
     * @Date  2020/4/18 14:48
     * @Param [message]
     * @return java.lang.String
     **/
    @StreamListener(Sink.INPUT)
    public void consumerMsg(Message<String> message){
        String msg = message.getPayload();
        log.info("当前端口：{}--消费内容：{}",serverPort,msg);
    }
}
