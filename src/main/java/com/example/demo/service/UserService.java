package com.example.demo.service;

import com.example.demo.dao.UserMapper;
import com.example.demo.domain.User;
import com.example.demo.domain.UserLoginEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
    @Resource
    private UserMapper userMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * login
     */
    public void simpleLogin(User user) throws Exception{
        //登录用户查询
        User callBackUser = userMapper.getUserByUsernameAndPassword(user.getUsername(),user.getPassword());
        if (null == callBackUser){
            throw new Exception("用户不存在");
        }

        //创建token并放入redis
        String simpleToken = UUID.randomUUID().toString().replace("-", "");
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set(callBackUser.getId().toString(), simpleToken, 30, TimeUnit.SECONDS);

        try {
            //用户登录行为记录事件
            rocketMQTemplate.convertAndSend("user-topic-1",
                    UserLoginEvent.init(callBackUser));
        } catch (Exception e) {
            log.error(e.getMessage(), e.getStackTrace());
        }
    }

}
