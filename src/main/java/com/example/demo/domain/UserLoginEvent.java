package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: create by LiWeichen
 * @version: v1.0
 * @description: 用户登录事件
 * @date:2019-3-14
 */
@Data
@AllArgsConstructor
public class UserLoginEvent implements Serializable {

    private Integer userId;

    private Long loginTime;

    /**
     * 初始化userLoginEvent对象
     * @param user
     * @return
     * @throws Exception
     */
    public static UserLoginEvent init(User user) throws Exception{
        if (null == user.getId()){
            throw new Exception("UserLoginEvent init fails： userId null");
        }
        return new UserLoginEvent(user.getId(),System.currentTimeMillis());
    }
}
