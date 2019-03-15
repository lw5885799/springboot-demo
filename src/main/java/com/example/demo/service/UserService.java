package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.domain.UserLoginEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author: create by LiWeichen
 * @version: v1.0
 * @description:
 * @date:2019-3-14
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class UserService {

    // private static final Logger log = LoggerFactory.getLogger(UserService.class);


    @Resource
    private RocketMQTemplate rocketMQTemplate;

    /**
     * login
     */
    public void simpleLogin(User user){

        try {
            //用户登录行为记录事件
            rocketMQTemplate.convertAndSend("user-topic-1",
                    UserLoginEvent.init(user));
        } catch (Exception e) {
            log.error(e.getMessage(), e.getStackTrace());
        }
    }

}
