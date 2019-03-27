package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

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
        JSONObject object = JSON.parseObject(s);
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(Long.parseLong(object.get("loginTime").toString())));
        String message = object.get("userId").toString() + "-----" + date;
        log.info("SampleConsumer message {}", message);
    }
}
