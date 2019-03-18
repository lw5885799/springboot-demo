package com.example.demo.dao;

import com.example.demo.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author: create by LiWeichen
 * @version: v1.0
 * @description: userMapper 测试类
 * @date:2019-3-18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MybaitsTest {


    @Resource
    private UserMapper userMapper;

    @Test
    public void simpleSelectTest(){

        User user = userMapper.getUserByUsernameAndPassword("testUser", "testPassword");
    }

    @Test
    public void getUserByNameTest(){

        User testUser = userMapper.getUserByUsername("testUser");
    }

    @Test
    public void getUserByIdTest(){
        User user = userMapper.getUserById(1);
    }

    @Test
    public void updateUsernameByIdTest(){

        userMapper.updateUsernameById("testxxx",1);
    }

    @Test
    public void insertUserByUsernameAndPassword(){
        userMapper.insertUserByUsernameAndPassword("username01", "pwd01");
    }

    @Test
    public void deleteUserById(){
        userMapper.deleteUserById(2);
    }
}
