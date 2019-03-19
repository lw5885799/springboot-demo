package com.example.demo.mq;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author: create by LiWeichen
 * @version: v1.0
 * @description:
 * @date:2019-3-14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserLoginTest {

    @Autowired
    private UserService userService;

    @Test
    public void loginMethodTest(){

        try {
            userService.simpleLogin(new User(null,"aa","123"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
