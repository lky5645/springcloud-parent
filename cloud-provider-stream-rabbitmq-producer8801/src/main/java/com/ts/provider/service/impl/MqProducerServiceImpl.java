package com.ts.provider.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ts.provider.service.MqProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;

/**
 * @ClassName MqProducerServiceImpl
 * @Description ：这里不需要@Service注解，因为是和stream到交道，和原来的方式不一样
 * @Author 86175
 * @Date 2020/4/18 13:58
 * @Params TODO
 */
@Slf4j
@EnableBinding(Source.class) //定义消息发送的管道
public class MqProducerServiceImpl implements MqProducerService{

    @Resource
    private MessageChannel output;//定义发送消息的通道

    @Override
    public String producerMsg() {
        String uuid = IdUtil.randomUUID();
        boolean sendFlag = output.send(MessageBuilder.withPayload(uuid).build());
        log.info("发送消息："+uuid);
        if (!sendFlag){
            return "发送失败";
        }
        return "发送成功";
    }
}
