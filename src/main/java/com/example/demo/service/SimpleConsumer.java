package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author: create by LiWeichen
 * @version: v1.0
 * @description: 简单登录事件消费者
 * @date:2019-3-14
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = "user-topic-1", consumerGroup = "user-group")
public class SimpleConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {
        log.info("SampleConsumer message" + s);
    }
}
